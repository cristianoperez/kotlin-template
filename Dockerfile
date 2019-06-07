FROM gradle:latest as builder

USER root

WORKDIR /app

ADD build.gradle /app
ADD gradle.properties /app
ADD settings.gradle /app

RUN gradle dependencies

RUN curl -O "http://download.newrelic.com/newrelic/java-agent/newrelic-agent/current/newrelic-java.zip" && \
    unzip newrelic-java.zip

COPY . /app
RUN gradle build -x test

FROM openjdk:8u212-jre-slim

WORKDIR /app

COPY docker/init.sh .
COPY --from=builder /app/build/libs/PROJECT_NAMEE-*.jar /app/
COPY --from=builder /app/newrelic/newrelic.jar /app/
COPY --from=builder /app/newrelic/newrelic.yml /app/

EXPOSE 8080

ENTRYPOINT ["./init.sh"]
