FROM gradle:5.4.1-alpine as builder

USER root

ENV APP_DIR /app
WORKDIR $APP_DIR

COPY build.gradle.kts $APP_DIR/
COPY settings.gradle.kts $APP_DIR/

RUN gradle dependencies

RUN apk add --no-cache curl && \
    curl -O "http://download.newrelic.com/newrelic/java-agent/newrelic-agent/current/newrelic-java.zip" && \
    unzip newrelic-java.zip

COPY . $APP_DIR

RUN gradle build -x test

USER guest

# -----------------------------------------------------------------------------	

FROM openjdk:13-slim-buster

WORKDIR /app

RUN apt-get update && apt-get install -y --no-install-recommends \
    tini && \
    rm -rf /var/lib/apt/lists/*

COPY --from=builder /app/init.sh /app
COPY --from=builder /app/build/libs/kotlin-spring-sample-*.jar /app/
COPY --from=builder /app/newrelic/newrelic.jar /app/
COPY --from=builder /app/newrelic/newrelic.yml /app/

EXPOSE 8080

ENTRYPOINT ["tini", "-s", "--", "sh", "init.sh"]
