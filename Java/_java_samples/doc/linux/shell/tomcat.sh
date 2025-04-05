#添加tomcat自启动服务，
# 1.需要把本文件放入/etc/init.d
# 2.授权：chmod +x /etc/init.d/tomcat
# 3.开机自启动：sudo chkconfig tomcat on
#!/bin/bash
# chkconfig: - 85 15
# description: Tomcat Server basic start/shutdown script
# processname: tomcat


JAVA_HOME=/usr/java/jdk1.8.0_91
export JAVA_HOME

TOMCAT_HOME=/usr/local/webserver/apache-tomcat-8.5.15
START_TOMCAT=$TOMCAT_HOME/bin/startup.sh
STOP_TOMCAT=$TOMCAT_HOME/bin/shutdown.sh

start() {
echo -n "Starting tomcat: "
cd $TOMCAT_HOME
${START_TOMCAT}
echo "done."
}

stop() {
echo -n "Shutting down tomcat: "
cd $TOMCAT_HOME
${STOP_TOMCAT}
echo "done."
}

case "$1" in
start)
start
;;
stop)
stop
;;
restart)
stop
sleep 10
start
;;
*)
echo "Usage: $0 {start|stop|restart}"
esac
exit 0
