FROM maven:3.9.9-eclipse-temurin-17 AS build

ARG SERVICE
WORKDIR /workspace
COPY . .
RUN mvn -f ${SERVICE}/pom.xml -DskipTests package

FROM eclipse-temurin:17-jre

WORKDIR /app
ARG SERVICE
COPY --from=build /workspace/${SERVICE}/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
