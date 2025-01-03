FROM maven:3.9.0-eclipse-temurin-21 AS build

COPY . /app/

WORKDIR /app/backend

RUN mvn clean package -DskipTests

# Comando de depuração para verificar o conteúdo do diretório target
RUN ls -l /app/backend/target/

FROM openjdk:21-jdk-slim

COPY --from=build /app/backend/target/calculo-0.0.1.jar /app/backend.jar

EXPOSE 8080

CMD ["java", "-jar", "/app/backend.jar"]
