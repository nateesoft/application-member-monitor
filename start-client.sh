pm2 delete -s server-monitor
cd ./monitor-server/
pm2 start ecosystem.config.js
