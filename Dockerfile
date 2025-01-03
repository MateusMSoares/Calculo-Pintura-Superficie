FROM openjdk:21-jdk-slim AS build

COPY . /app/

WORKDIR /app/backend

RUN mvn -DoutputFile=target/mvn-dependency-list.log -B -DskipTests clean dependency:list install
