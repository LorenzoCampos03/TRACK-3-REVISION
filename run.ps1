# Script para ejecutar la aplicación Spring Boot
# Configura JAVA_HOME automáticamente y ejecuta Maven

$env:JAVA_HOME = "C:\Program Files\Java\jdk-17"
Write-Host "JAVA_HOME configurado a: $env:JAVA_HOME" -ForegroundColor Green
Write-Host "Ejecutando Spring Boot en puerto 8090..." -ForegroundColor Cyan
.\mvnw spring-boot:run
