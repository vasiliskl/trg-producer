package com.trgint;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.eclipse.microprofile.metrics.MetricUnits;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Timed;
import org.jboss.logging.Logger;

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
    private static final Logger logger = Logger.getLogger(SensorMeasurementResource.class.getName());
    @Inject SensorMeasurementProducer producer;
	
    /**
     * This method accepts the measurement JSON and if valid, it adds it in a queue to be asynchronously processed.
     * @param measurement
     * @return HTTP Response
     */
    @POST
    @Path("/measurement")
    @Counted(name = "measurementsReceived", description = "How many measurements have been received from the sensors.")
    @Timed(name = "measurementsTimer", description = "A measure of how long it takes to receive the measurement and add it to queue.", unit = MetricUnits.MILLISECONDS)
    public Response addMeasurementToQueue(@Valid SensorMeasurement measurement) {
    	
		try 
		{
	    	logger.info("Measurement received:"+new ObjectMapper().writeValueAsString(measurement));
	    	
	    	if(measurement!=null)
	    	{
	    		if(measurement.getSensorId()==null)
	    		{
	    			return Response.status(Status.BAD_REQUEST).entity(new SensorMeasurementError("INVALID_SENSOR_ID","Please specify sensor Id.")).build();
	    		}   		
	
	    		producer.produce(new ObjectMapper().writeValueAsString(measurement));
	
	    	}
	    	else
	    	{
	    		logger.error("Invalid measurement.");
	    		return Response.status(Status.BAD_REQUEST).build();
	    	}
		} 
		catch (JsonProcessingException e) 
		{
			logger.error("Error while processing measurement.");
			e.printStackTrace();
			return Response.serverError().build();
		}
    	return Response.ok().build();
    }
}