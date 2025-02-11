FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY target/order-manager-0.0.1-SNAPSHOT.jar /app/order-manager.jar

EXPOSE 8080

CMD ["java", "-jar", "/app/order-manager.jar"]