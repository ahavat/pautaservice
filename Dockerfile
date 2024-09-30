FROM openjdk:21-jdk

WORKDIR /app

COPY target/pautaservice.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
