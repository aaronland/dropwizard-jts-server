package jts;

import org.locationtech.jts.geom.Geometry;

import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class GeometryReader {

    public GeometryReader() {

    }

    public Geometry fromGeoJSON(InputStream input) throws Exception {

	// https://github.com/locationtech/jts/issues/844
	
	GeoJsonReader reader = new GeoJsonReader();
	Geometry geom;

	try {

	    BufferedReader buf = new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8));
	    geom = reader.read(buf);
	}

	catch (Exception e){
	    throw e;
	}

	return geom;
    }
}
