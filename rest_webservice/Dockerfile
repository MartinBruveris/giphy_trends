FROM maven:3.6.3-openjdk-11 as build
WORKDIR /app
COPY /restWebserver /app
RUN mvn clean package


FROM openjdk:11-jre-slim-buster
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]