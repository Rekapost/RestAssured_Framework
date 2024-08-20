FROM adoptopenjdk/openjdk11:latest
COPY target/*.jar /usr/src/app/app.jar
WORKDIR /usr/src/app
ENTRYPOINT ["java", "-jar", "app.jar"]