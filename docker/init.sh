#!/usr/bin/env bash

set -e

java ${JVM} -javaagent:/app/newrelic.jar -Duser.Timezone=America/Sao_Paulo -Dnewrelic.config.license_key=${NEWRELIC_LICENSE_KEY} -Dnewrelic.config.app_name=${NEWRELIC_APP_NAME} -Dnewrelic.config.distributed_tracing.enabled=true -jar PROJECT_NAMEE-*.jar
