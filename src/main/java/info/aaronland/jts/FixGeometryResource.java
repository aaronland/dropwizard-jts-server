package jts;

import java.io.InputStream;
import java.io.File;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.media.multipart.FormDataParam;
import javax.ws.rs.core.MediaType;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;

import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.Response;

// import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.locationtech.jts.io.geojson.GeoJsonReader;
import org.locationtech.jts.geom.Geometry;

// import org.apache.commons.io.IOUtils;

import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

@Path(value = "/fix")
@Produces({MediaType.APPLICATION_JSON})
public class FixGeometryResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(FixGeometryResource.class);

    /*
    @GET
    public Response extrudeThisURL(@QueryParam("url") String url){

	Document doc;
	DocumentView view;

	try {
	    doc = extrudeThis(url);
	    view = new DocumentView(doc);
	}

	// TODO: trap MalformedURLExceptions and return NOT_ACCEPTABLE here (20130901/straup)

	catch (Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.toString()).build();
	}

	//String html = doc.toHTML();
	//return Response.status(Response.Status.OK).entity(html).build();

	return Response.status(Response.Status.OK).entity(view).build();
    }
    */
    
    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response extrudeThisFile(@FormDataParam("file") InputStream input){

	// START OF put me in a function
	
	GeoJsonReader reader = new GeoJsonReader();
	Geometry geom;

	try {

	    BufferedReader buf = new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8));
	    geom = reader.read(buf);
	}

	catch (Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.toString()).build();
	}

	// END OF put me in a function
	
	return Response.status(Response.Status.OK).entity("OK").build();
    }

}
