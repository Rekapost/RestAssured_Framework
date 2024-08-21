# Use a base image with JDK installed
FROM maven:3.8.6-openjdk-11

# Set the working directory in the container
WORKDIR /usr/src/app

# Copy the pom.xml and download dependencies (for caching)
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy the entire project into the container
COPY . .

# Build the project and run tests
RUN mvn clean test
