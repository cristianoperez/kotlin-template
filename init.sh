#!/usr/bin/env bash

set -e


ENVIRONMENT_NAME="${SPRING_PROFILES_ACTIVE:-"development"}"
JVM_OPS="${JVM_OPS:-""}"
echo "ENVIRONMENT_NAME: ${ENVIRONMENT_NAME}"

exec java ${JVM_OPS} -Djava.security.egd=file:/dev/./urandom \
    -javaagent:/app/newrelic.jar \
    -Duser.Timezone=America/Sao_Paulo \
    -Dnewrelic.config.license_key=${NEWRELIC_LICENSE_KEY} \
    -Dnewrelic.config.app_name=${NEWRELIC_APP_NAME} \
    -Dnewrelic.config.distributed_tracing.enabled=true \
    -Dspring.datasource.url=${DATABASE_URL} \
    -jar /app/kotlin-spring-sample-1.0-SNAPSHOT.jar
