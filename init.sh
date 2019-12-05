#!/usr/bin/env bash

set -e

JVM_OPS="${JVM_OPS:-""}"
NEWRELIC_APP_NAME="${NEWRELIC_APP_NAME:-"kotlin_spring_sample"}"
echo "ENVIRONMENT_NAME: ${ENVIRONMENT_NAME}"

exec java $JVM_OPS -Djava.security.egd=file:/dev/./urandom \
  -javaagent:/app/newrelic.jar \
  -Duser.Timezone=America/Sao_Paulo \
  -Dnewrelic.config.license_key=$NEWRELIC_TOKEN \
  -Dnewrelic.config.app_name=$NEWRELIC_APP_NAME \
  -Dnewrelic.config.distributed_tracing.enabled=true \
  -jar /app/kotlin-spring-sample-*.jar