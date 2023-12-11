# Use a base image with OpenJDK
FROM openjdk:8-jre-slim

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file into the container at /app
COPY target/grocery-app.jar /app

# Expose the port that your application will run on
EXPOSE 8080

# Specify the command to run your application
CMD ["java", "-jar", "grocery-app.jar"]
