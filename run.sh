#!/usr/bin/env bash

set -e

if [[ "$1" -eq "clean" ]]; then
  mvn clean
fi

mvn package -DskipTests
(cd CardManagementSystem && docker build . --rm -t cardmanagementsystem:1.0.0)
(cd NotificationService && docker build . --rm -t notificationservice:1.0.0)
docker-compose up -d --remove-orphans
