mvn clean package
mvn package && java -jar target/state-server-0.0.1-SNAPSHOT.jar
docker build -t state-server .
docker run -p 8080:8080 state-server