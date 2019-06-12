#!/usr/bin/env bash

set -e

ENVIRONMENT_NAME="${SPRING_PROFILES_ACTIVE:-"development"}"
echo "ENVIRONMENT_NAME: ${ENVIRONMENT_NAME}"

exec java -Xms380m \
  -Dserver.port=9292 \
  -Djava.security.egd=file:/dev/./urandom \
  -Dspring.datasource.url=${DATABASE_URL} \
  -jar /app/risk-analysis-1.0-SNAPSHOT.jar
