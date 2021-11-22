FROM openjdk

WORKDIR /app

COPY target/seucontrolefinanceiro-0.0.1-SNAPSHOT.jar /app/scf.jar

ENTRYPOINT ["java", "-jar", "scf.jar"]