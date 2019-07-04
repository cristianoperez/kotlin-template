#!/usr/bin/env bash

set -e

ENVIRONMENT_NAME="${SPRING_PROFILES_ACTIVE:-"development"}"
JVM_OPS="${JVM_OPS:-""}"
NEWRELIC_APP_NAME="${NEWRELIC_APP_NAME:-"kotlin_spring_sample"}"
echo "ENVIRONMENT_NAME: ${ENVIRONMENT_NAME}"

COMMAND=${1:-"web"}
echo $COMMAND

case "$COMMAND" in
  migrate|web)
    exec java ${JVM_OPS} -Djava.security.egd=file:/dev/./urandom \
      -javaagent:/app/newrelic.jar \ # NOTE: NewRelic won't work unless -javaagent is the first argument
      -Duser.Timezone=America/Sao_Paulo \
      -Dnewrelic.config.license_key=${NEWRELIC_LICENSE_KEY} \
      -Dnewrelic.config.app_name=${NEWRELIC_APP_NAME} \
      -Dnewrelic.config.distributed_tracing.enabled=true \
      -Dspring.profiles.active=${ENVIRONMENT_NAME} \
      -Dspring.datasource.url=${DATABASE_URL} \
      -jar /app/kotlin-spring-sample-*.jar \
      $COMMAND
    ;;
  *)
    exec sh -c "$*"
    ;;
esac
