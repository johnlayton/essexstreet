#!/bin/bash

STACK_NAME=MicronautAppStack
API_URL="$(aws --no-cli-pager cloudformation describe-stacks \
  --stack-name $STACK_NAME --query 'Stacks[0].Outputs[?OutputKey==`MnTestApiUrl`].OutputValue' \
  --output text)"
RESPONSE="$(curl --silent --header "Content-Type: application/json" \
  --request POST --data '{"name":"Name", "secret":"1234234534564567"}' $API_URL)"
EXPECTED_RESPONSE='{"message":"Hello World"}'
if [ "$RESPONSE" != "$EXPECTED_RESPONSE" ]; then echo $RESPONSE && exit 1; fi
echo "success"
exit $EXIT_STATUS
