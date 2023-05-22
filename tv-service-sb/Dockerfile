FROM eclipse-temurin:17.0.6_10-jre-jammy

ADD ./target/*.jar tv-service-sb.jar

EXPOSE 11001

CMD java $JAVA_OPTS -jar tv-service-sb.jar