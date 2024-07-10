# Etapa 1: Construcción de la aplicación con Maven
FROM maven:3.8.4-openjdk-17-slim AS builder
WORKDIR /app
COPY donatello/pom.xml ./
RUN mvn dependency:go-offline

COPY donatello/src ./src
RUN mvn package -DskipTests

# Etapa 2: Preparación de la imagen de producción
FROM openjdk:17-jdk-slim AS runner
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar
CMD ["java", "-jar", "app.jar"]