#!/usr/bin/env bash

source scripts/env_config.sh
echo "Clean Tomcat Logs"
ssh 57399fda0c1e66013700000b@shop-venkatvp.rhcloud.com "rm ./app-root/data/apache-tomcat-8.0.33/logs/*"
echo "Shutting down Tomcat"
ssh 57399fda0c1e66013700000b@shop-venkatvp.rhcloud.com "./app-root/data/apache-tomcat-8.0.33/bin/shutdown.sh"
echo "Starting Tomcat"
ssh 57399fda0c1e66013700000b@shop-venkatvp.rhcloud.com "./app-root/data/apache-tomcat-8.0.33/bin/startup.sh"
echo "Done"