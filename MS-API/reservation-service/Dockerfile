FROM maven:3.9.5-amazoncorretto-17 AS build
COPY . /app
WORKDIR /app
RUN mvn clean package -DskipTests -U

FROM openjdk:17-alpine
VOLUME /tmp
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]