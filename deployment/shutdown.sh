#!/bin/bash

app-image="ivishalyadav/contacts-manager"
kubectl="minikube kubectl --"

# Remove image
# shellcheck disable=SC2046
docker rmi -f $(docker images "${app-image}" -a -q)

docker images

# Delete app deployment
${kubectl} delete deployment contacts-manager-app

# Delete app service
${kubectl} delete service contacts-manager-service

# Delete mysql deployment
${kubectl} delete deployment mysql

# Delete mysql service
${kubectl} delete service mysql