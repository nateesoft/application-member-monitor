# Packing application

- dist
  => start.bat
  => member-monitor Setup x.x.x.exe|dmg
  => monitor-server (*all)
  => monitor-ui
    => build
    => node_modules
    => server
        => index.js
        => node_modules
        => package-lock.json
        => package.json

## Edit Config

- monitor-server/mysql-connect/config.js

## Step Run

- install exe or dmg
- exec start.bat
