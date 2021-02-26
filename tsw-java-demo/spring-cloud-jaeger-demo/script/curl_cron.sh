#!/bin/sh

# interval
step=1

for (( i = 0; i < 60; i=(i+step) )); do
    curl --location --request POST 'localhost:19110/order/create' --header 'Content-Type: application/json' -d '{"productId": 1,"qty": 1,"accountId": 1}'
    echo ""
    sleep $step
done