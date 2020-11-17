const createError = require('http-errors');
const express = require('express');
const path = require('path');
const cookieParser = require('cookie-parser');
const morgan = require('morgan');
const cors = require('cors');

const logger = require('./logger');
const config = require('./config');

const indexRouter = require('./routes/index');
const memberRouter = require('./routes/member.route');
const redeemRouter = require('./routes/redeem.route');

const app = express();

const options = {
  apiServiceMember: config.apiServiceMember,
  apiServiceRedeem: config.apiServiceRedeem,
  apiServiceDB: config.apiServiceDB,
  apiServiceAuth: config.apiServiceAuth,
}

const setupLogger = (req, res, next) => {
  logger.info(`${req.method} ${req.path} ${res.statusCode}`);
  next();
}

app.use(cors());
app.use(setupLogger);

// view engine setup
app.set('views', path.join(__dirname, 'views'));
app.set('view engine', 'ejs');

app.use(morgan('dev'));
app.use(express.json());
app.use(express.urlencoded({ extended: false }));
app.use(cookieParser());
app.use(express.static(path.join(__dirname, 'public')));

app.use('/', indexRouter);
app.use('/api/member', memberRouter(options));
app.use('/api/redeem', redeemRouter(options));

// catch 404 and forward to error handler
app.use(function(req, res, next) {
  next(createError(404));
});

// error handler
app.use(function(err, req, res, next) {
  // set locals, only providing error in development
  res.locals.message = err.message;
  res.locals.error = req.app.get('env') === 'development' ? err : {};

  // render the error page
  res.status(err.status || 500);
  res.render('error');
});

module.exports = app;
