#FROM openjdk:8-jdk-alpine
#ADD target/shop_v2-0.0.1-SNAPSHOT.jar .
#CMD java -jar shop_v2-0.0.1-SNAPSHOT.jar

FROM openjdk:8-jdk-alpine
ADD target/shop_v2-0.0.1-SNAPSHOT.jar .

CMD ["java","-jar","shop_v2-0.0.1-SNAPSHOT.jar"]
