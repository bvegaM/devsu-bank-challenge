FROM openjdk:17
EXPOSE 8081
COPY build/libs/client-service-0.0.1-SNAPSHOT.jar client-service.jar
ENTRYPOINT ["java", "-jar", "/client-service.jar"]