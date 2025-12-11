# Stage 1: build
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /workspace
COPY pom.xml .
RUN mvn -B -q dependency:go-offline

COPY src ./src
RUN mvn -B -q package -DskipTests

# Stage 2: runtime
FROM eclipse-temurin:17-jre
ARG JAR_FILE=/workspace/target/demo-container-app-0.0.1-SNAPSHOT.jar
COPY --from=build ${JAR_FILE} /app/app.jar

RUN addgroup --system appgroup && adduser --system appuser --ingroup appgroup
USER appuser

EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/app.jar"]

