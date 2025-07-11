# Stage 1: Build with Maven 3.8.9 and Java 17
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app

# Copy the entire multi-module project
COPY . .

# Build all modules and package the final JAR
RUN mvn clean install && ls -l com.sportygroup.feed-service/target/sporty-group-feed.jar

# Stage 2: Run with JDK 17
FROM eclipse-temurin:17-jdk-jammy
WORKDIR /service

# Copy the JAR from the build stage
COPY --from=build /app/com.sportygroup.feed-service/target/sporty-group-feed.jar ./sporty-group-feed.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "sporty-group-feed.jar"]