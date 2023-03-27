FROM eclipse-temurin:17.0.4_8-jre-jammy as builder
WORKDIR application
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} application.jar
COPY ./target/newrelic/newrelic.jar .
COPY newrelic.yml .
RUN java -Djarmode=layertools -jar application.jar extract

FROM eclipse-temurin:17.0.4_8-jre-jammy
WORKDIR application
COPY --from=builder application/dependencies/ ./
COPY --from=builder application/spring-boot-loader/ ./
COPY --from=builder application/snapshot-dependencies/ ./
COPY --from=builder application/application/ ./
COPY --from=builder newrelic.yml .
ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]
