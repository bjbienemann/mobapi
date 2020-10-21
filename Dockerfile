FROM openjdk:11.0.7-jre

ENV API_HOME /usr/local/api
ENV PATH $API_HOME:$PATH
WORKDIR $API_HOME

RUN mkdir data
COPY taxi.txt ./data/taxi.txt

ADD target/mobapi*.jar ./api.jar

EXPOSE 8080
CMD ["java", "-jar", "-Dspring.profiles.active=prod", "api.jar"]