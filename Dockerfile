# --------------------------------------------------------------------------------
# Layer: builder
# Install dependencies and builds a JAR with the application
FROM gradle:jdk10-slim as builder

# Prevents volume permission mismtach (Gradle image user is 'gradle')
USER root

# Creates app directory into gradle home
ENV APP_DIR /app
RUN mkdir $APP_DIR
WORKDIR $APP_DIR

COPY ./build.gradle.kts ./settings.gradle.kts $APP_DIR/

RUN gradle dependencies

RUN apt-get update; apt-get install -y curl
RUN curl -O "http://download.newrelic.com/newrelic/java-agent/newrelic-agent/current/newrelic-java.zip" && \
    unzip newrelic-java.zip


COPY . $APP_DIR

# Build project
RUN gradle build -x test

# -----------------------------------------------------------------------------
# Layer: production
# Runs the application as if in a production environment
FROM openjdk:10-jre-slim as production

# Add Tini
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
