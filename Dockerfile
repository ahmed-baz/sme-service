FROM openjdk:17-alpine
LABEL maintainer="ahmismail.c@stc.com.sa"
WORKDIR /usr/local/bin/
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} sme-service-1.0.jar
EXPOSE 9999
ENTRYPOINT ["java","-jar","-Dspring-boot.run.profiles=prod","sme-service-1.0.jar"]
