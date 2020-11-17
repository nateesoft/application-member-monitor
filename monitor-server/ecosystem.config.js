module.exports = {
  apps : [
    {
      name: 'monitor-server',
      script: 'bin/www',
      instances: 1,
      autorestart: true,
      watch: false,
      max_memory_restart: '1G',
    },
  ],
};
