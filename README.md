##################################################
# To add all the necessary Maven Wrapper files
#
# This is only run once and has already been run.
##################################################
mvn -N io.takari:maven:wrapper


##################################################
# To build
##################################################
./mvnw clean install


##################################################
# To run the app
##################################################
# Prerequisites
    - create the directory /var/log/perso/java8work and chmod it to 777

# To run the app with the Maven wrapper
./mvnw spring-boot:run

# To run the app without the Maven wrapper
cd /java8work/target
java -jar java8work-1.0-SNAPSHOT.jar


##################################################
# To test the app
##################################################
curl http://localhost:8080/ -v -X GET


# TODO
http://winterbe.com/posts/2014/07/31/java8-stream-tutorial-examples/
Start at FlatMap

