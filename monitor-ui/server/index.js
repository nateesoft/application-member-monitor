const express = require("express")
const path = require("path")
const app = express()

app.use(express.static(path.join(__dirname, "/../build")))
app.use(express.json())

app.get("*", function (req, res) {
  res.sendFile(path.join(__dirname, "/../build", "index.html"))
})

app.listen(process.env.PORT || 3333, ()=>{
    console.log('Server running at port 3333')
})
