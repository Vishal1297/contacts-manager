#!/bin/bash

app-image="ivishalyadav/spring-boot-test"
kubectl="minikube kubectl --"

# Remove image
# shellcheck disable=SC2046
sudo docker rmi $(docker images "${app-image}" -a -q)

sudo docker images

# Delete app deployment
${kubectl} delete deployment spring-test-app

# Delete app service
${kubectl} delete service spring-test-service

# Delete mysql deployment
${kubectl} delete deployment mysql

# Delete mysql service
${kubectl} delete service mysql