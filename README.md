##################################################
# To build
##################################################
mvn clean install


##################################################
# To run the app
##################################################
# To run the app with the Maven wrapper
./mvnw spring-boot:run

# To run the app without the Maven wrapper
cd /java8work/target
java -jar java8work-1.0-SNAPSHOT.jar


##################################################
# To test the app
##################################################
curl http://localhost:8151/tester/ -v -X GET
200

curl -d '{"name":"Lionel", "favouriteColor":"blaugrana", "favouriteNumber":10}' -H "Content-Type: application/json" -X POST http://localhost:8151/tester/123e4567-e89b-42d3-a456-556642440000/users
200 {"id":"123e4567-e89b-42d3-a456-556642440000","created":false}


##################################################
# Avro section
##################################################
- dependency and plugin were added to the pom.xml
- .avsc file created under src/main/avro
- mvn clean install --> generates classes under src/main/java/example.avro



TODO
TODO in PublisherImpl
TODO in TestEndpointTest
