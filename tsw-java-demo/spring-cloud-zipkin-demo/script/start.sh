#!/bin/bash

db_host=""
db_port="3306"
db_username="root"
db_password=""

redis_host=""
redis_port="6379"
redis_password=""

zipkin_url=""

kafka_servers=""
kafka_username=""
kafka_password=""

consul_host=""
consul_port="8500"

jvm_opt="-Xms128m -Xmx256m -XX:MaxMetaspaceSize=128m"
db_opt="--db.host=$db_host --db.port=$db_port --db.username=$db_username --db.password=$db_password"
redis_opt="--redis.host=$redis_host --redis.port=$redis_port --redis.password=$redis_password"
kafka_opt="--kafka.servers=$kafka_servers --kafka.username=$kafka_username --kafka.password=$kafka_password"
consul_opt="--consul.host=$consul_host --consul.port=$consul_port"
zipkin_opt="--zipkin.url=$zipkin_url"

line="-----------------------------------------------------"

echo "$line"
echo "Start TSW Zipkin Java Demo "
echo "$line"

arguments_validate="n"

# demo
package=""

if [ "$#" -ge 1 ]; then
  arguments_validate="y"
  package=$1
fi

while [ "$arguments_validate" != "y" ]; do

  echo "Please Input Right Arguments"
  echo ""

  # Package
  echo -n "> Which demo ? [ all | order | account | inventory | logistics | email ] : "
  read package

  if [ "$package" != "all" ] && [ "$package" != "order" ] && [ "$package" != "account" ] && [ "$package" != "inventory" ] && [ "$package" != "logistics" ] && [ "$package" != "email" ]; then
    continue
  fi
  # Confirm Arguments:
  echo "$line"
  echo "Start $package demo? (y/n): "
  read arguments_validate

done

if [ "$package" == "all" ] || [ "$package" == "order" ]; then
  echo "Start order"
  nohup java $jvm_opt -jar spring-cloud-zipkin-order-1.0.jar $consul_opt $db_opt $zipkin_opt >/dev/null &
fi

if [ "$package" == "all" ] || [ "$package" == "account" ]; then
  echo "Start account"
  nohup java $jvm_opt -jar spring-cloud-zipkin-account-1.0.jar $consul_opt $db_opt $zipkin_opt >/dev/null &
fi

if [ "$package" == "all" ] || [ "$package" == "inventory" ]; then
  echo "Start inventory"
  nohup java $jvm_opt -jar spring-cloud-zipkin-inventory-1.0.jar $consul_opt $db_opt $zipkin_opt >/dev/null &
fi

if [ "$package" == "all" ] || [ "$package" == "logistics" ]; then
  echo "Start logistics"
  nohup java $jvm_opt -jar spring-cloud-zipkin-logistics-1.0.jar $consul_opt $redis_opt $kafka_opt $zipkin_opt >/dev/null &
fi

if [ "$package" == "all" ] || [ "$package" == "email" ]; then
  echo "Start email"
  nohup java $jvm_opt -jar spring-cloud-zipkin-email-1.0.jar $consul_opt $redis_opt $kafka_opt $zipkin_opt >/dev/null &
fi

echo "$line"
echo "Start complete"
echo "$line"
