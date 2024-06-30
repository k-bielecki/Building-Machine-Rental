FROM openjdk:21

ARG JAR_FILE=target/*.jar

ENV SPRING_PROFILES_ACTIVE=docker

WORKDIR /app

COPY ${JAR_FILE} app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","app.jar"]