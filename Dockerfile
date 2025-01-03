FROM openjdk:21-jdk-slim AS build

RUN apt-get update && apt-get install -y maven

COPY . /app/

WORKDIR /app/backend

RUN mvn -DoutputFile=target/mvn-dependency-list.log -B -DskipTests clean dependency:list install
