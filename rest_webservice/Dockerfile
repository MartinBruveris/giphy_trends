# syntax=docker/dockerfile:1
FROM openjdk:11-jre-slim-buster
ARG JAR_FILE=/restWebserver/target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","app.jar"]