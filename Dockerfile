### STAGE 1: Build ###
FROM maven:3.6.3-openjdk-11 AS build
WORKDIR /usr/src/app
COPY . .

RUN mvn package -q
# RUN mvn package
# RUN mvn package -X

### STAGE 2: Run ###
FROM openjdk:11.0.8-jre-buster
ARG JAR_FILE=target/*.jar
COPY --from=build /usr/src/app/${JAR_FILE} app.jar

ENTRYPOINT ["java","-Dserver.port=80","-jar","/app.jar"]