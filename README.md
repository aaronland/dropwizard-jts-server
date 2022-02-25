# jts-server

A simple Dropwizard server wrapping a select number of operations provided by the Java Topology Suite (JTS) package.

## Important

* This is work in progress and lacks well-formatted Java docs.
* I am not a day-to-day Java person so it's very possible I don't even know what I don't know.
* This application does not provide the full suite of JTS operations. It probably never will.
* The application still lacks some Dropwizard basics, like health checks.
* This application lacks any kind of access control so be very careful if you choose to put this on the public internet.
* Contributions and suggestions are welcome.

## Building

```
$> mvn clean
$> mvn install
```

## Running

```
$> java -jar target/jts-server-0.0.1.jar server
```

## Endpoints

### /fix/geometry

This endpoint will run the JTS `GeometryFixer` utility using a GeoJSON document as it's input returning a new GeoJSON document. The new document will not contain any GeoJSON `properties` from the original document.

This endpoint accepts multi-part form HTTP `POST` request:

```
$> curl -v -X POST -F file=@102527513.geojson localhost:8080/fix/geometry
```

Or a `url` parameter in a `GET` request:

```
$> curl 'http://localhost:8080/fix/geometry?url=https://static.sfomuseum.org/geojson/id/102527513'
```

_Note: There are currently no checks on upload file sizes or validating `url` parameters._

See also:

* https://lin-ear-th-inking.blogspot.com/2021/05/fixing-invalid-geometry-with-jts.html
* https://github.com/locationtech/jts/blob/master/modules/core/src/main/java/org/locationtech/jts/geom/util/GeometryFixer.java

## Docker

Yes.

```
$> docker build -t jts-server .

$> docker run -it -p 8080:8080 jts-server java -jar /usr/local/jar/jts-server.jar server
```

## Known knowns

* The `GeoJsonReader` (and `GeoJsonConstants`) packages have been cloned in to `info.aaronland.jts` package. I don't know why this is necessary but it fixes the ["Could not parse Geometry from GeoJson string. Unsupported 'type':Feature" errors](https://github.com/locationtech/jts/issues/844) bug report. I don't know why it fixes the problem either.

## See also:

* https://github.com/locationtech/jts
* https://www.dropwizard.io