# clustr

How to start the clustr application
---

1. Run `mvn clean install` to build your application
1. Start application with `java -jar target/0.0.1-0.0.1.jar server config.yml`
1. To check that your application is running enter url `http://localhost:8080`

Health Check
---

To see your applications health enter url `http://localhost:8081/healthcheck`

## See also

* https://lin-ear-th-inking.blogspot.com/2022/01/concave-hulls-in-jts.html
* https://github.com/locationtech/jts/blob/master/modules/io/common/src/main/java/org/locationtech/jts/io/geojson/GeoJsonReader.java