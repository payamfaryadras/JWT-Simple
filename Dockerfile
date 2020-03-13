FROM openjdk:11-jre-slim
EXPOSE 8080
WORKDIR opt/program
COPY target/usermanagement-service.jar /opt/program/
CMD java -jar usermanagement-service.jar