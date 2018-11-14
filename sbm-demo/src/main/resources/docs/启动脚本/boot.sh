#!/bin/bash

export JAVA_HOME=/usr/local/jdk
export CLASS_PATH=.:
export PATH=$JAVA_HOME/bin:$PATH

function help(){
	echo -e "\033[0;31m ---help--- \033[0m"
	echo -e "\033[0;31m Usage: \033[0m  \033[0;34m sh  $0  {start|stop|restart|status}  {*.jar} \033[0m"
	echo -e "\033[0;31m Example: \033[0m\033[0;33m sh  $0  start example-test.jar \033[0m"
}

if [ ${#@} -ne 0 ] && [ "$1" == "--h" ]; then
  help;
  exit 0;
fi;

opr_name=$1
ser_name=$2
 
#echo $opr_name $ser_name


if [ "$opr_name" = "" ];
then
    echo -e "\033[0;31m 未输入操作名 \033[0m  \033[0;34m {start|stop|restart|status} \033[0m"
    echo -e "\033[0;33m Command 'sh  $0  --h' for help \033[0m"
    exit 1
fi

if [ "$ser_name" = "" ];
then
    echo -e "\033[0;31m 未输入应用名 \033[0m  \033[0;34m {*.jar} \033[0m"
    echo -e "\033[0;33m Command 'sh  $0  --h' for help \033[0m"
    exit 1
fi

function start() {
    echo "Start $ser_name"
	count=`ps -ef |grep java|grep $ser_name|grep -v grep|wc -l`
	if [ $count != 0 ];then
		echo "$ser_name is running..."
	else
		nohup java -jar $ser_name > /dev/null 2>&1 &
		echo "Start $ser_name success..."
	fi
}
 
function stop(){
	echo "Stop $ser_name"
	count=`ps -ef |grep java|grep $ser_name|grep -v grep|wc -l`
 
	if [ $count != 0 ];then
		boot_id=`ps -ef |grep java|grep $ser_name|grep -v grep|awk '{print $2}'`
		kill -9 $boot_id
		
		echo "$ser_name is stoped..."
	fi
}
 
function restart(){
	stop
	sleep 2
	start
}
 
function status(){
    count=`ps -ef |grep java|grep $ser_name|grep -v grep|wc -l`
    if [ $count != 0 ];then
        echo "$ser_name is running..."
    else
        echo "$ser_name is not running..."
    fi
}
 
case $1 in
start)
	start;;
stop)
	stop;;
restart)
	restart;;
status)
	status;;
*)
	help;;
esac