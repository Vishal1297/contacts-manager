#!/bin/bash

# Remove image
# shellcheck disable=SC2046
sudo docker rmi $(docker images 'spring-boot-test' -a -q)

sudo docker images

kubectl="minikube kubectl --"

# Delete deployment
$kubectl delete deployment spring-test-app

# Delete service
$kubectl delete service spring-test-service