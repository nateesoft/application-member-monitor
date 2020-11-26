pm2 delete -s server-monitor ui-monitor
cd ./monitor-server/
pm2 start ecosystem.config.js
