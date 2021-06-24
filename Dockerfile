FROM gradle:7.1.0-jdk8
WORKDIR '/app'
COPY . .
RUN gradle clean build

FROM openjdk:8
WORKDIR '/app'
COPY --from=0 /app/build/libs/docker-demo-app-*-SNAPSHOT.jar docker-demo-app.jar 
ENTRYPOINT ["java", "-jar", "docker-demo-app.jar"]