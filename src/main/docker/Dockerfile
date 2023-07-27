FROM java:8
MAINTAINER yvan

RUN mkdir -p /data/logs
VOLUME /data/logs

WORKDIR /data
ADD project-example-1.0.0-SNAPSHOT.jar app.jar
RUN touch app.jar
EXPOSE 10081
EXPOSE 8012
CMD ["java", "-Dspring.profiles.active=test", "-jar", "app.jar"]