package jts;

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

import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// import org.locationtech.jts.io.geojson.GeoJsonReader;
import org.locationtech.jts.io.geojson.GeoJsonWriter;
import org.locationtech.jts.geom.Geometry;

import org.locationtech.jts.algorithm.hull.PolygonHull;

import java.io.InputStream;

@Path(value = "/hull/polygon")
@Produces({MediaType.APPLICATION_JSON})
public class PolygonHullResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(FixGeometryResource.class);


    @GET
    @Produces({MediaType.APPLICATION_JSON})    
    public Response polygonHull(@QueryParam("url") String url){

	InputStream input;

	try {
	    input = new URL(url).openStream();
	} 

	catch (Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.toString()).build();
	}
	
	return polygonHullFromInputStream(input);
    }
    
    @POST
    @Consumes({MediaType.MULTIPART_FORM_DATA + ";charset=utf-8"})
    @Produces({MediaType.APPLICATION_JSON})    
    public Response polygonHull(@FormDataParam("file") InputStream input){

	return polygonHullFromInputStream(input);
    }

    @Produces({MediaType.APPLICATION_JSON})        
    private Response polygonHullFromInputStream(InputStream input){
	
	GeometryReader reader = new GeometryReader();
	Geometry geom;

	try {
	    geom = reader.fromGeoJSON(input);
	}

	catch (Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.toString()).build();
	}

	// What about addition properties
	
	PolygonHull hull = new PolygonHull(geom);
	Geometry new_geom = hull.getHull();

	// TBD: How to pass the Response writer to the GeoJSON writer
	
	GeoJsonWriter writer = new GeoJsonWriter();
	String output = writer.write(new_geom);
	
	return Response.status(Response.Status.OK).entity(output).build();
    }
    
}
