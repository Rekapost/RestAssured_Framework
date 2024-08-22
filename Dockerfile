
#FROM maven:3.8.4-openjdk-11 AS builder
#WORKDIR /usr/src/app
#COPY pom.xml .
#RUN mvn dependency:go-offline
#COPY src /usr/src/app/src
#RUN mvn clean package

#FROM openjdk:11-jre
#COPY --from=builder /usr/src/app/target/RestAssuredFramework-0.0.1-SNAPSHOT.jar /app.jar
#ENTRYPOINT ["java", "-jar", "/app.jar"]


FROM maven:3.8.4-openjdk-11
COPY . /usr/src/app
WORKDIR /usr/src/app
RUN mvn clean package
CMD ["java", "-jar", "target/RestAssuredFramework-0.0.1-SNAPSHOT.jar"]

#FROM maven:3.8.4-openjdk-11
#COPY . /usr/src/app
#WORKDIR /usr/src/app
#RUN mvn clean package
#CMD ["java", "-jar", "target/my-app.jar"]


# Use a base image with JDK installed
#FROM maven:3.8.6-openjdk-17
# Set the working directory in the container
#WORKDIR /usr/src/app
# Copy the pom.xml and download dependencies (for caching)
#COPY pom.xml .
#RUN mvn dependency:go-offline -B
# Copy the entire project into the container
#COPY . .
# Build the project and run tests
#RUN mvn clean test -X

