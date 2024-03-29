#!/bin/bash

app-image="ivishalyadav/contacts-manager"
kubectl="minikube kubectl --"

docker images

# Remove previous image with tag repo/image-name
# shellcheck disable=SC2046
docker rmi $(docker images "${app-image}" -a -q)

# Create image with tag repo/image-name
docker build -t "${app-image}" .

docker images

# Create mysql deployment
${kubectl} apply -f mysql-deployment.yaml

# Create app deployment
${kubectl} apply -f deployment.yaml

${kubectl} get deployments

${kubectl} get services
