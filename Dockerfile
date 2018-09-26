FROM openjdk:8-jdk-alpine

LABEL maintainer="admin@vistarmedia.com"

VOLUME /tmp

EXPOSE 8080

ARG JAR_FILE=target/state-server-0.0.1-SNAPSHOT.jar

ADD ${JAR_FILE} state-server.jar

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/state-server.jar"]