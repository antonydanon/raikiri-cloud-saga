FROM eclipse-temurin:21-jdk-alpine AS builder
WORKDIR /app

COPY gradlew .
COPY gradlew.bat .
COPY build.gradle .
COPY settings.gradle .
COPY gradle gradle/
COPY src src/

RUN chmod +x /app/gradlew
RUN ./gradlew clean bootJar --no-daemon

FROM eclipse-temurin:21-jre-alpine AS runtime
WORKDIR /app

COPY --from=builder /app/build/libs/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
