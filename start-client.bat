call git pull origin main
call pm2 delete -s server-monitor ui-monitor
cd ./monitor-server/
call pm2 start ecosystem.config.js
cd ../monitor-ui/server
call pm2 start ecosystem.config.js
