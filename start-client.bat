call pm2 delete -s server-monitor
cd ./monitor-server/
call pm2 start ecosystem.config.js
