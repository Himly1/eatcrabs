#! /bin/sh
nohup java -jar /data/polyFinance-eatcrabs/backstage-web/instanceA/backstage-web.jar >/dev/null 2>&1 &

echo $! > /data/polyFinance-eatcrabs/backstage-web/instanceA/backstage-web.pid