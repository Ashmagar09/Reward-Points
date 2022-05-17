FROM adoptopenjdk/openjdk11:latest as build
LABEL APPLICATION="Rewards Point System"
RUN mkdir /app
WORKDIR /app
COPY target/reward-points-1.0.0.jar reward-points-1.0.0.jar
ENTRYPOINT ["java","-jar","reward-points-1.0.0.jar"]
EXPOSE 8080