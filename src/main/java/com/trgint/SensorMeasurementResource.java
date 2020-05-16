package com.trgint;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * @author Vasilis Kleanthous
 * This class implements the sensor REST endpoints.
 *
 */
@Path("/sensor")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SensorMeasurementResource {

	@Inject @Channel("measurement-out") Emitter<String> measurementEmitter;
	
    /**
     * This method accepts the measurement JSON and if valid, it adds it in a queue to be asynchronously processed.
     * @param measurement
     * @return HTTP Response
     */
    @POST
    @Path("/measurement")
    public Response addMeasurementToQueue(SensorMeasurement measurement) {
    	
    	if(measurement!=null)
    	{
    		if(measurement.getSensorId()==null)
    		{
    			return Response.status(Status.BAD_REQUEST).entity(new SensorMeasurementError("INVALID_SENSOR_ID","Please specify sensor Id.")).build();
    		}   		

    		try 
    		{
				measurementEmitter.send(new ObjectMapper().writeValueAsString(measurement));
			} 
    		catch (JsonProcessingException e) 
    		{
				e.printStackTrace();
				return Response.serverError().build();
			}
    	}
    	else
    	{
    		return Response.status(Status.BAD_REQUEST).build();
    	}
    	
    	return Response.ok().build();
    }
}