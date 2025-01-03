FROM maven:3.8.4-openjdk-21 AS build

COPY . /app/

WORKDIR /app/backend

RUN mvn -DoutputFile=target/mvn-dependency-list.log -B -DskipTests clean dependency:list install
