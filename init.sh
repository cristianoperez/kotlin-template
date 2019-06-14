#!/usr/bin/env bash

set -e

ENVIRONMENT_NAME="${SPRING_PROFILES_ACTIVE:-"development"}"
JVM_OPS="${JVM_OPS:-""}"
NEWRELIC_APP_NAME="${NEWRELIC_APP_NAME:-"kotlin_spring_sample"}"
echo "ENVIRONMENT_NAME: ${ENVIRONMENT_NAME}"

exec java ${JVM_OPS} -Djava.security.egd=file:/dev/./urandom \
    -javaagent:/app/newrelic.jar \
    -Duser.Timezone=America/Sao_Paulo \
    -Dnewrelic.config.license_key=${NEWRELIC_LICENSE_KEY} \
    -Dnewrelic.config.app_name=${NEWRELIC_APP_NAME} \
    -Dnewrelic.config.distributed_tracing.enabled=true \
    -Dspring.profiles.active=${ENVIRONMENT_NAME} \
    -jar /app/kotlin-spring-sample-0.0.1-SNAPSHOT.jar
