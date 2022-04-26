FROM openjdk:8 AS TEMP_BUILD_IMAGE
ARG AMBIENTE
ENV APP_HOME=/usr/app/
WORKDIR $APP_HOME
COPY . .
COPY ./docker/$AMBIENTE.application.yaml ./microservicio/src/main/resources/application.yaml
WORKDIR /usr/app/microservicio
RUN chmod +x gradlew
RUN ./gradlew clean build || return 0
RUN chmod +x gradlew

FROM openjdk:8
ENV ARTIFACT_NAME=0.0.1-SNAPSHOT.jar
ENV APP_HOME=/usr/app/
WORKDIR $APP_HOME
COPY --from=TEMP_BUILD_IMAGE $APP_HOME/microservicio/build/libs/$ARTIFACT_NAME .
CMD java -jar $ARTIFACT_NAME