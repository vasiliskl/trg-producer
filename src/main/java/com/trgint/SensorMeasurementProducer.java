package com.trgint;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.jboss.logging.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * @author Vasilis Kleanthous
 * This class implements the queue producer. It provides a method to add data in the queue.
 *
 */
@ApplicationScoped
public class SensorMeasurementProducer {

	@Inject @Channel("measurement-out") Emitter<String> measurementEmitter;
	private static final Logger logger = Logger.getLogger(SensorMeasurementProducer.class.getName());
	
	/**
	 * This method adds the data in the queue.
	 * @param measurement - the data to add in the queue
	 * @throws JsonProcessingException
	 */
	public void produce(String measurement)throws JsonProcessingException
	{
		logger.info("Adding Measurement to queue:"+measurement);
		measurementEmitter.send(measurement);
		logger.info("Measurement added to queue:"+measurement);
	}
}
