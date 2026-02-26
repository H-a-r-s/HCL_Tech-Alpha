FROM eclipse-temurin:21-jdk as build
WORKDIR /app
COPY . .
RUN ./mvnw -q -DskipTests package || mvn -q -DskipTests package

FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/target/product-service-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8082
ENTRYPOINT ["java","-jar","app.jar"]