FROM maven:3.8.3-openjdk-17-slim AS build

RUN mkdir /project

COPY . /project

WORKDIR /project

RUN mvn -DskipTests=true clean package

FROM openjdk:17-oracle

RUN mkdir /app

COPY --from=build /project/target/tweetApp-0.0.1-SNAPSHOT.jar /app/tweetApp-0.0.1-SNAPSHOT.jar

EXPOSE 8080

CMD java $JAVA_OPTS -jar /app/tweetApp-0.0.1-SNAPSHOT.jar