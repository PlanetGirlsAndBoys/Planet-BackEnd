# Estágio de build
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Estágio de execução
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

# Configurações de segurança
RUN addgroup --system javauser && adduser --system --no-create-home --ingroup javauser javauser
USER javauser

# Configurações de JVM
ENV JAVA_OPTS="-Xms512m -Xmx512m -XX:+UseContainerSupport -XX:MaxRAMPercentage=75.0"

# Expor a porta
EXPOSE 8080

# Comando para executar a aplicação
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"] 