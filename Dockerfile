FROM eclipse-temurin:17-jdk

EXPOSE 8083

ADD target/order-service-docker.jar order-service-docker.jar

ENTRYPOINT ["java","-jar","/order-service-docker.jar"]