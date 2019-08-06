##################################################
# To display dependencies as a tree
##################################################
mvn dependency:tree


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

To create a user:
curl -d '{"name":"Lionel", "favouriteColor":"blaugrana", "favouriteNumber":10}' -H "Content-Type: application/json" -X POST http://localhost:8151/tester/123e4567-e89b-42d3-a456-556642440000/users
200 {"id":"123e4567-e89b-42d3-a456-556642440000","created":true}

The non-blocking endpoint
curl http://localhost:8151/async-deferredresult/ -v -X GET

To publish a notification to Kafka:
curl -d '{"version":"1.0.0","publishTime":"2009-12-31T23:59:59","deviceGuid":"123e4567-e89b-42d3-a456-556642440999","item":{"guid":"123e4567-e89b-42d3-a456-556642440666","itemType":"LETTER"},"outcome":"DELIVERED"}' -H "Content-Type: application/json" -X POST http://localhost:8151/tester/123e4567-e89b-42d3-a456-556642440000/notifications
200 {"id":"123e4567-e89b-42d3-a456-556642440000","created":true}


##################################################
# Avro section
##################################################
- dependency and plugin were added to the pom.xml
- .avsc file created under src/main/avro
- mvn clean install --> generates classes under src/main/java/example.avro
- Problem to solve:
        - I publish events to a Kafka topic with Publisher Api v1.0 which uses Avro-codec-lib v1.0 containing Avro Event schema v1.0. I can consume them with Consumer Api v1.0 which uses Avro-codec-lib v1.0.
        - I then do a new PROD release v2.0 for all components but I still want to be able to consume events that were published with v1.0.
        - Solution:
                - It is OK to add fields to the v2.0 Avro schema as long as you give a default value to these fields.
                - However, you can’t remove fields as you would not be able to decode byte[] which are on the Kafka topic and which were published with v1.0.


##################################################
# Spring Admin Server section
##################################################
Let's say that you have a Spring Boot application. You would like to register it automatically with a Spring Admin Server at start-up so you can monitor its status.


Add the dependencies below:
compile('org.jolokia:jolokia-core:1.6.0')
compile('de.codecentric:spring-boot-admin-starter-client:1.5.7') {
   exclude group: 'org.jolokia'
}


Define an application.yml with:
logging:
  level:
    com.example.demo: DEBUG
    org.springframework: WARN
    org.springframework.cloud.client.discovery: DEBUG
  profile: DEV

server:
  port: 8081

eureka:
  client:
    registerWithEureka: false
    fetchRegistry: true
    serviceUrl:
      defaultZone: https://someserver.com/eureka

management:
  security.enabled: false
  context-path: /actuator

# To register itself with the admin server.
spring:
  boot:
    admin:
      url: http://localhost:8085
      username: user
      password: pass

info:
  name: ${name}
  version: ${version}
  origin: ${githubRepo}
  branch: master
  built: 2018-12-13 06:43:19.77


TODO: publish notifications to the Kafka server.
        - see send(SparkPocNotification sparkPocNotification). Shall we use Spring or directly Apache classes?


TODO: publish events to the Kafka server.
               - an event contains:
                         - a guid
                         - a description: make it an enum (MailDelivered, MailReturned, etc.)
                         - a time: when the event occurred (we may not need it as this would be the time at which the event was published)
                         - a related-item guid: the parcel guid.
                         - a related-item type: Parcel if delivery scenario.
                         - an actor-guid: the delivery man guid. No need to give a type as this will always be a responsible person.


TODO: publish itemsToDeliver to the Kafka server.
                - an item contains:
                        - a guid
                        - a type: Parcel, Letter
                        - a target delivery date/time
                        - a deliverer: guid of the delivery man


TODO In NonBlockingEndpoint


TODO Write a client consuming the non blocking endpoint. See TODO in CallingServiceImpl.


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
