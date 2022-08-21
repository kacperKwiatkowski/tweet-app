FROM openjdk:17-slim

WORKDIR '/app'

COPY ./target/tweetApp-0.0.1-SNAPSHOT.jar ./

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=prod", "tweetApp-0.0.1-SNAPSHOT.jar"]