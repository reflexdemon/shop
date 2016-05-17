#!/usr/bin/env bash

ssh 57399fda0c1e66013700000b@shop-venkatvp.rhcloud.com "./app-root/data/apache-tomcat-8.0.33/bin/shutdown.sh"
ssh 57399fda0c1e66013700000b@shop-venkatvp.rhcloud.com "./app-root/data/apache-tomcat-8.0.33/bin/startup.sh"