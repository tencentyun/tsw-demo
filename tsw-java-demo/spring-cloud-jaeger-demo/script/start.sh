#!/bin/bash

db_host=""
db_port="3306"
db_username="root"
db_password=""

redis_host=""
redis_port="6379"
redis_password=""

jaeger_host=""
jaeger_port="6832"
jaeger_collector=""

kafka_servers=""
kafka_username=""
kafka_password=""

consul_host=""
consul_port="8500"

tsw_token=""

jvm_opt="-Xms128m -Xmx256m -XX:MaxMetaspaceSize=128m"
db_opt="--db.host=$db_host --db.port=$db_port --db.username=$db_username --db.password=$db_password"
redis_opt="--redis.host=$redis_host --redis.port=$redis_port --redis.password=$redis_password"
kafka_opt="--kafka.servers=$kafka_servers --kafka.username=$kafka_username --kafka.password=$kafka_password"
consul_opt="--consul.host=$consul_host --consul.port=$consul_port"
jaeger_opt="--jaeger.host=$jaeger_host --jaeger.port=$jaeger_port"
jaeger_agent_opt="--reporter.grpc.host-port=$jaeger_collector ----agent.tags=Authentication=$tsw_token"

line="-----------------------------------------------------"

echo "$line"
echo "Start TSW Jaeger Java Demo "
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
  echo -n "> Which demo ? [ all | order | account | inventory | logistics | email | jaeger_agent ] : "
  read package

  if [ "$package" != "all" ] && [ "$package" != "order" ] && [ "$package" != "account" ] && [ "$package" != "inventory" ] && [ "$package" != "logistics" ] && [ "$package" != "email" ] && [ "$package" != "jaeger_agent" ]; then
    continue
  fi
  # Confirm Arguments:
  echo "$line"
  echo "Start $package demo? (y/n): "
  read arguments_validate

done

if [ "$package" == "all" ] || [ "$package" == "jaeger_agent" ]; then
  echo "Start jaeger agent"
  nohup ./jaeger-agent $jaeger_agent_opt >/dev/null &
fi

if [ "$package" == "all" ] || [ "$package" == "order" ]; then
  echo "Start order"
  nohup java "$jvm_opt" -jar spring-cloud-jaeger-order-1.0.jar "$consul_opt" "$db_opt" "$jaeger_opt" >/dev/null &
fi

if [ "$package" == "all" ] || [ "$package" == "account" ]; then
  echo "Start account"
  nohup java "$jvm_opt" -jar spring-cloud-jaeger-account-1.0.jar "$consul_opt" "$db_opt" "$jaeger_opt" >/dev/null &
fi

if [ "$package" == "all" ] || [ "$package" == "inventory" ]; then
  echo "Start inventory"
  nohup java "$jvm_opt" -jar spring-cloud-jaeger-inventory-1.0.jar "$consul_opt" "$db_opt" "$jaeger_opt" >/dev/null &
fi

if [ "$package" == "all" ] || [ "$package" == "logistics" ]; then
  echo "Start logistics"
  nohup java "$jvm_opt" -jar spring-cloud-jaeger-logistics-1.0.jar "$consul_opt" "$redis_opt" "$kafka_opt" "$jaeger_opt" >/dev/null &
fi

if [ "$package" == "all" ] || [ "$package" == "email" ]; then
  echo "Start email"
  nohup java "$jvm_opt" -jar spring-cloud-jaeger-email-1.0.jar "$consul_opt" "$redis_opt" "$kafka_opt" "$jaeger_opt" >/dev/null &
fi

echo "$line"
echo "Start complete"
echo "$line"
