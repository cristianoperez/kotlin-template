FROM gradle:5.4 as builder

USER root

ENV APP_DIR /app
WORKDIR $APP_DIR

COPY build.gradle.kts $APP_DIR/
COPy settings.gradle.kts $APP_DIR/

RUN gradle dependencies

RUN curl -O "http://download.newrelic.com/newrelic/java-agent/newrelic-agent/current/newrelic-java.zip" && \
    unzip newrelic-java.zip

COPY . $APP_DIR

RUN gradle build -x test

FROM openjdk:10-jre-slim as production

ADD https://github.com/krallin/tini/releases/download/v0.18.0/tini /tini
RUN chmod +x /tini

ENTRYPOINT ["/tini", "--"]

WORKDIR /app

COPY --from=builder /app/init.sh /app
COPY --from=builder /app/build/libs/kotlin-spring-sample-*.jar /app/
COPY --from=builder /app/newrelic/newrelic.jar /app/
COPY --from=builder /app/newrelic/newrelic.yml /app/

EXPOSE 8080

CMD ["sh", "init.sh"]
