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
TODO At the mo: no main manifest attribute, in java8work-1.0-SNAPSHOT.jar


##################################################
# To test the app
##################################################
curl http://localhost:8080/ -v -X GET


