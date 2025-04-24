# Dockerfile (Vue 3 & Spring Boot in one container)

# --- Frontend Build Stage ---
FROM node:21-alpine AS frontend-builder
WORKDIR /app/frontend
COPY frontend/package*.json ./
RUN npm install
COPY frontend/ .
RUN npm run build

 # --- Backend Build Stage ---
FROM maven:3.9.4-eclipse-temurin-21 AS backend-builder
WORKDIR /app/backend
COPY backend/pom.xml ./
RUN mvn dependency:go-offline -B
COPY backend/ .
RUN mvn clean package -DskipTests

 # --- Final Container ---
FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app

 # Copy backend jar
COPY --from=backend-builder /app/backend/target/*.jar app.jar

 # Copy frontend build into Spring Boot static resources
COPY --from=frontend-builder /app/frontend/dist/ /app/static/

EXPOSE 8085
ENTRYPOINT ["java", "-jar", "app.jar"]