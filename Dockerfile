FROM maven:3.9.9-eclipse-temurin-17-alpine AS builder 
WORKDIR /app 
COPY pom.xml . 
COPY src ./src 
RUN mvn -q -DskipTests clean package

FROM eclipse-temurin:17-jre-alpine 
WORKDIR /app 
COPY --from=builder /app/target/*.jar app.jar 
ENV SERVER_PORT=8080 
EXPOSE ${SERVER_PORT} 
ENTRYPOINT ["sh", "-c", "java -jar app.jar"] 