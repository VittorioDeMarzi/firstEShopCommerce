FROM eclipse-temurin:22
WORKDIR /app
COPY target/eCommerce-0.0.1-SNAPSHOT.jar ./eCommerce.jar
EXPOSE 8080
CMD [ "java", "-jar", "eCommerce.jar"]