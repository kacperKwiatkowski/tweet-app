FROM maven:3.8.3-openjdk-17-slim as build

WORKDIR app

RUN mvn package verify clean --fail-never

COPY ./src ./src

RUN mvn -Dmaven.test.skip=true package

FROM openjdk:17-slim

WORKDIR '/app'

COPY ./target/tweetApp-0.0.1-SNAPSHOT.jar ./

EXPOSE 8001

ENTRYPOINT ["java", "-jar", "tweetApp-0.0.1-SNAPSHOT.jar"]