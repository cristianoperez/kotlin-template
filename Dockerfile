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

COPY . $APP_DIR

# Build project
RUN gradle build
RUN cp $APP_DIR/build/libs/risk-analysis-1.0-SNAPSHOT.jar $APP_DIR/risk-analysis-1.0-SNAPSHOT.jar

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
COPY --from=builder /app/risk-analysis-1.0-SNAPSHOT.jar /app/risk-analysis-1.0-SNAPSHOT.jar

EXPOSE 9292

CMD ["sh", "init.sh"]
