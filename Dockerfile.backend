# Etapa de build
FROM openjdk:21-jdk-slim AS build

# Copia o código-fonte para o container
COPY . /app/

# Definir o diretório de trabalho
WORKDIR /app/backend

# Instalar o Maven
RUN apt-get update && apt-get install -y maven

# Compila o projeto usando Maven
RUN mvn clean install

# Etapa de execução (runtime)
FROM openjdk:21-jdk-slim

# Copia o artefato compilado (qualquer .jar gerado) da etapa de build
COPY --from=build /app/backend/target/*.jar /app/backend.jar

# Define o diretório de trabalho para a execução
WORKDIR /app

# Executa a aplicação usando o comando Java
CMD ["java", "-jar", "/app/backend.jar"]
