#!/usr/bin/env bash
# created by richard.purba@accenture.com

counter=1

#PLEASE provide here the port number of the process you need to terminate.
PORT_NUMBER=8888

# number 10 is just the maximum iteration you want, please increase this to increase the time of iteration
while [ $counter -le 10 ]
do  
    #process running or not running: random
    running=$((1+ RANDOM % 2))
    if [ $running -eq 1 ]; then
        echo "suspending A network open at port $(echo $PORT_NUMBER)"
        pid=$(lsof -Pi :$PORT_NUMBER -sTCP:LISTEN -t)
        kill -TSTP $pid
        sleep 20 #waiting 20s for the failure to return... 
    elif [ $running -eq 2 ]; then 
        echo "continuing A network open at port $(echo $PORT_NUMBER)"
        pid=$(lsof -Pi :$PORT_NUMBER -sTCP:LISTEN -t)
        kill -CONT $pid
        sleep 20 #waiting 20s to bring back the process
    else
        echo " unidentified..."
    fi    
    ((counter++))
done
echo "failure scenario is completed...."