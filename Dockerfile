FROM openjdk:8-jdk-alpine

ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} transaction.jar
ENTRYPOINT ["java","-jar","/transaction.jar"]