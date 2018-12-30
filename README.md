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



TODO CustomMapperServiceImplTest to fix as currently:
        SLF4J: Class path contains multiple SLF4J bindings.
        SLF4J: Found binding in [jar:file:/home/philippe/.m2/repository/org/slf4j/slf4j-log4j12/1.7.25/slf4j-log4j12-1.7.25.jar!/org/slf4j/impl/StaticLoggerBinder.class]
        SLF4J: Found binding in [jar:file:/home/philippe/.m2/repository/ch/qos/logback/logback-classic/1.1.11/logback-classic-1.1.11.jar!/org/slf4j/impl/StaticLoggerBinder.class]
        SLF4J: See http://www.slf4j.org/codes.html#multiple_bindings for an explanation.
        SLF4J: Detected both log4j-over-slf4j.jar AND bound slf4j-log4j12.jar on the class path, preempting StackOverflowError.
        SLF4J: See also http://www.slf4j.org/codes.html#log4jDelegationLoop for more details.

        java.lang.ExceptionInInitializerError
        	at org.slf4j.impl.StaticLoggerBinder.<init>(StaticLoggerBinder.java:72)
        	at org.slf4j.impl.StaticLoggerBinder.<clinit>(StaticLoggerBinder.java:45)
        	at org.slf4j.LoggerFactory.bind(LoggerFactory.java:150)
        	at org.slf4j.LoggerFactory.performInitialization(LoggerFactory.java:124)
        	at org.slf4j.LoggerFactory.getILoggerFactory(LoggerFactory.java:412)
        	at org.slf4j.LoggerFactory.getLogger(LoggerFactory.java:357)
        	at org.slf4j.LoggerFactory.getLogger(LoggerFactory.java:383)
        	at org.apache.avro.LogicalTypes.<clinit>(LogicalTypes.java:29)
        	at org.apache.avro.Schema.parse(Schema.java:1327)
        	at org.apache.avro.Schema.parse(Schema.java:1269)
        	at org.apache.avro.Schema$Parser.parse(Schema.java:1032)
        	at org.apache.avro.Schema$Parser.parse(Schema.java:1020)
        	at example.avro.User.<clinit>(User.java:17)
TODO in PublisherImpl
TODO in TestEndpointTest
