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
200 {"id":"123e4567-e89b-42d3-a456-556642440000","created":true}


##################################################
# Avro section
##################################################
- dependency and plugin were added to the pom.xml
- .avsc file created under src/main/avro
- mvn clean install --> generates classes under src/main/java/example.avro



TODO Get rid of SLF4J: Class path contains multiple SLF4J bindings.
                SLF4J: Found binding in [jar:file:/home/philippe/.m2/repository/ch/qos/logback/logback-classic/1.1.11/logback-classic-1.1.11.jar!/org/slf4j/impl/StaticLoggerBinder.class]
                SLF4J: Found binding in [jar:file:/home/philippe/.m2/repository/org/slf4j/slf4j-log4j12/1.7.25/slf4j-log4j12-1.7.25.jar!/org/slf4j/impl/StaticLoggerBinder.class]
                SLF4J: See http://www.slf4j.org/codes.html#multiple_bindings for an explanation.
                SLF4J: Actual binding is of type [ch.qos.logback.classic.util.ContextSelectorStaticBinder]
     This appears at runtime of the app.
     I tried the below which got rid of the warning but then unit tests were failing:
     <!-- To exclude globally slf4j-log4j12 as it was clashing with a logback lib. Note the scope of provided. -->
             <dependency>
                 <groupId>org.slf4j</groupId>
                 <artifactId>slf4j-log4j12</artifactId>
                 <scope>provided</scope>
             </dependency>

TODO in PublisherImpl

TODO in TestEndpointTest
