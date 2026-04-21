FROM eclipse-temurin:21-jdk

WORKDIR /app

RUN mkdir -p /app/logs

COPY target/*.jar app.jar

EXPOSE 8085

ENTRYPOINT ["java","-jar","app.jar"]
