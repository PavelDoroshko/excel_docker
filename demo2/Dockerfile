FROM openjdk:11.0.9-jre-slim as runtime
MAINTAINER doroshko:demo2
COPY target/demo2-0.0.1-SNAPSHOT.jar demo2-0.0.1-SNAPSHOT.jar
CMD exec java -jar /demo2-0.0.1-SNAPSHOT.jar
RUN apt-get update; apt-get install -y fontconfig libfreetype6