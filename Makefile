exec:
	mvn clean
	mvn compile exec:java

build: 
	mvn clean
	mvn install

# TO DO: derive version from pom.xml
run:
	java -jar target/jts-server-0.0.1.jar server

debug:
	make build && make run
