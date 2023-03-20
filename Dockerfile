FROM openjdk:8-jdk-alpine
COPY target/WalletWrapper-0.0.1-SNAPSHOT.jar wallet-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/wallet-0.0.1-SNAPSHOT.jar"]