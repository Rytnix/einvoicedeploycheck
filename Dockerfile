# Stage 1: Build the application with Gradle
FROM gradle:7.2.0-jdk11 AS builder

# Set the working directory inside the container
WORKDIR /app

# Copy the Gradle files and download the dependencies
COPY build.gradle .
COPY settings.gradle .
COPY gradlew .
COPY gradle ./gradle

# Copy the source code and build the application
COPY src ./src
RUN  gradle build

# Stage 2: Create the final image with the built JAR
FROM openjdk:11-jre-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the built JAR from the previous stage
COPY --from=builder /app/build/libs/einvoiceService-0.0.1-SNAPSHOT.jar app.jar

# Expose the port that your Spring Boot application listens to
EXPOSE 8080

# Command to run your Spring Boot application when the container starts
CMD ["java", "-jar", "app.jar"]

