FROM openjdk:11.0.4-jre
VOLUME /tmp
COPY build/libs/*.jar /app.jar
EXPOSE 8080 5005
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]