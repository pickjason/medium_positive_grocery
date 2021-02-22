#!/bin/sh
   source  ~/.bash_profile
   export JAVA_HOME=$JAVA_HOME
   echo ${JAVA_HOME}

  APP=medium_positive_grocery
  APP_JAR=${APP}"-0.0.1-SNAPSHOT.jar"
  ENV_SPRING_ACTIVE="--spring.profiles.active=dev"
  LOG_DIR=$PWD/start-log
  JVM_LOG_DIR=$PWD/jvm-log
  JVM_DUMP_DIR=$PWD/jvm-dump

  command=$1

# 启动
function start(){
  # 日志文件是否存在 不存创建
  if [ ! -d "${LOG_DIR}" ];then
    mkdir "${LOG_DIR}"
  fi
  if [ ! -d "${JVM_LOG_DIR}" ];then
    mkdir "${JVM_LOG_DIR}"
  fi
  if [ ! -d "${JVM_DUMP_DIR}" ];then
    mkdir "${JVM_DUMP_DIR}"
  fi
  rm -f $APP.pid
  #${JAVA_HOME}/bin/jar uvf $APP_JAR jdbc.properties
  nohup ${JAVA_HOME}/bin/java -server -Xmx512m -Xms512m -XX:NewRatio=4 -XX:SurvivorRatio=8 -XX:PermSize=48 -XX:MaxPermSize=64m -Xss256k -XX:ThreadStackSize=128k -XX:-ReduceInitialCardMarks -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:+PrintHeapAtGC -Xloggc:$JVM_LOG_DIR/$APP-GC.log -XX:+DisableExplicitGC -XX:+UseConcMarkSweepGC -XX:+CMSClassUnloadingEnabled -XX:ParallelCMSThreads=4 -XX:+CMSParallelRemarkEnabled -XX:+UseCMSCompactAtFullCollection -XX:CMSInitiatingOccupancyFraction=50 -XX:CMSFullGCsBeforeCompaction=2 -XX:+UseCompressedOops -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=$JVM_DUMP_DIR/$APP-heapDump.hprof -jar $APP_JAR $ENV_SPRING_ACTIVE >> /dev/null 2>$APP-error.log &
  echo $! > $APP.pid
  check
}

# 停止
function stop(){
  tpid=`ps -ef | grep $APP_JAR | grep -v grep | grep -v kill | awk '{print $2}'`
  if [ ${tpid} ];then
    echo "Stop Process"
    kill -15 $tpid
  fi

  sleep 5

  tpid=`ps -ef | grep $APP_JAR | grep -v grep | grep -v kill | awk '{print $2}'`
  if [ ${tpid} ];then
    echo "Stop Process"
    kill -15 $tpid
  else
    echo "Stop SUCCESS"
  fi

  sleep 5

  tpid=`ps -ef | grep $APP_JAR | grep -v grep | grep -v kill | awk '{print $2}'`
  if [ ${tpid} ];then
    echo "Stop FAILD"
  fi
}

# 检查
function check(){
  tpid=`ps -ef | grep $APP_JAR | grep -v grep | grep -v kill | awk '{print $2}'`
  if [ ${tpid} ]; then
    echo "APP is running"
  else
    echo "APP is Not running"
  fi
}

# 强制kill进程
function forcekill(){
  tpid=`ps -ef | grep $APP_JAR | grep -v grep | grep -v kill | awk '{print $2}'`
  if [ ${tpid} ];then
    echo "Kill Process"
    kill -9 $tpid
  fi
}

# 输出进程号
function showtpid(){
  tpid=`ps -ef | grep $APP_JAR | grep -v grep | grep -v kill | awk '{print $2}'`
  if [ ${tpid} ];then
    echo 'Process '$APP_JAR' tpid is '$tpid
  else
    echo 'Process '$APP_JAR' is not running.'
  fi
}

# 输出进程号
function showtpid(){
    tpid=`ps -ef|grep $APP_JAR|grep -v grep|grep -v kill|awk '{print $2}'`
    if [ ${tpid} ]; then
        echo 'process '$APP_JAR' tpid is '$tpid
    else
        echo 'process '$APP_JAR' is not running.'
    fi
}

if [ "${command}" ==  "start" ]; then
    start

elif [ "${command}" ==  "stop" ]; then
     stop

elif [ "${command}" ==  "check" ]; then
     check

elif [ "${command}" ==  "status" ]; then
     check

elif [ "${command}" ==  "kill" ]; then
     forcekill

elif [ "${command}" == "tpid" ];then
     showtpid

else
    echo "Unknow argument....[start|stop|check|status|kill|tpid]"
fi