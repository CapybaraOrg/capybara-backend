FROM amazoncorretto:17.0.5@sha256:650f101c5339254fc4788dde1efed575e227494d8756a8ae1d37629cc5164ca0

USER nobody

ARG JAR_FILE=build/libs/capybara-backend-0.0.1-SNAPSHOT.jar

COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]
