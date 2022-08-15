#!/bin/bash

app-image="ivishalyadav/spring-boot-test"
kubectl="minikube kubectl --"

sudo docker images

# Remove previous image with tag repo/image-name
# shellcheck disable=SC2046
sudo docker rmi $(docker images "${app-image}" -a -q)

# Create image with tag repo/image-name
sudo docker build -t "${app-image}" .

sudo docker images

# Create mysql deployment
${kubectl} apply -f mysql-deployment.yaml

# Create app deployment
${kubectl} apply -f deployment.yaml

${kubectl} get deployments

${kubectl} get services
