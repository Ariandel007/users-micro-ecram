FROM eclipse-temurin:11.0.14.1_1-jdk-alpine
COPY build/libs/*.jar /app/users-micro-ecram.jar
WORKDIR /app
# ENTRYPOINT ["java", "-jar","-Dspring.profiles.active=dockerdev", "users-micro-ecram.jar"]
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=dockerdev", "users-micro-ecram.jar"]
#docker build -t ecram/users-micro:0.0.1-SNAPSHOT .
#docker run -m 256m -d --name users-micro-ecram  -p 30001:30001 ecram/users-micro:0.0.1-SNAPSHOT
#docker stop users-micro-ecram
#docker push ecram/users-micro:0.0.1-SNAPSHOT
#docker pull ecram/users-micro:0.0.1-SNAPSHOT