FROM openjdk:17
EXPOSE 8082
COPY build/libs/product-service-0.0.1-SNAPSHOT.jar product-service.jar
ENTRYPOINT ["java", "-jar", "/product-service.jar"]