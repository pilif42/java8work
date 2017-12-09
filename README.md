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
