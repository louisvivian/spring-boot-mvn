FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD /package/spring-boot-mvn-1.0.1.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]