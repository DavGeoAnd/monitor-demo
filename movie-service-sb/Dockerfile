FROM eclipse-temurin:17.0.6_10-jre-jammy

ADD ./target/*.jar movie-service-sb.jar

EXPOSE 12001

CMD java $JAVA_OPTS -jar movie-service-sb.jar