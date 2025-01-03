FROM openjdk:21-jdk-slim AS build

RUN apt-get update && apt-get install -y maven

COPY . /app/

WORKDIR /app/backend

FROM openjdk:21-jdk-slim

COPY --from=build /app/backend/target/calculo-0.0.1.jar /app/backend.jar

EXPOSE 8080

CMD ["java", "-jar", "/app/backend.jar"]