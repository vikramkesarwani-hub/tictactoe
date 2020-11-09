# tictactoe-spring-boot-mvc
Spring Boot Tictactoe Application

# run server
java -jar target/tictactoe-spring-boot-0.0.1-SNAPSHOT.jar

# play simple game on browser
http://localhost:8083/tictactoe

///TODO
run database
docker run --name postgres -e POSTGRES_PASSWORD=admin -e POSTGRES_USER=admin -p 127.0.0.1:5432:5432 -d postgres
