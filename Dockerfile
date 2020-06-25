FROM openjdk:8-jdk-alpine
ARG APP
ARG VERSION
ARG JAR_FILE
COPY $JAR_FILE $APP-$VERSION/service.jar
WORKDIR $APP-$VERSION
CMD ["/bin/sh", "-c", "java -jar service.jar"]