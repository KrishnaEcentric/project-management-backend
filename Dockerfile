# Use an official OpenJDK runtime as a parent image
FROM openjdk:21
# Set the working directory inside the container
WORKDIR /app
# Copy the jar file produced by the Maven build
COPY target/*.jar app.jar
# Expose the port the application runs on
EXPOSE 8084
# Run the jar file
ENTRYPOINT ["java", "-jar", "app.jar"]
