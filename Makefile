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

docker:
ifdef NOCACHE
	docker build --no-cache=true -t jts-server .
else
	docker build -t jts-server .
endif

docker-run:
	docker run -it -p 8080:8080 jts-server java -jar /usr/local/jar/jts-server.jar server
