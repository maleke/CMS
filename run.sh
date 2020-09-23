#!/usr/bin/env bash

set -e

if [[ "$1" -eq "clean" ]]; then
  mvn clean
fi

mvn package -DskipTests
docker build . --rm -t CardManagement:1.0.0
docker build . --rm -t NotificationService:1.0.0
docker-compose up -d --remove-orphans
