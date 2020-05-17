package com.trgint;

import javax.annotation.Priority;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;

import org.jboss.logging.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.quarkus.test.Mock;

/**
 * @author Vasilis Kleanthous
 * This is a mock class that simulates the queue producer.
 *
 */
@Mock
@Alternative()
@Priority(1)
@ApplicationScoped
public class SensorMeasurementProducerMock extends SensorMeasurementProducer {

	private static final Logger logger = Logger.getLogger(SensorMeasurementProducerMock.class.getName());
	
	@Override
	public void produce(String measurement) throws JsonProcessingException {
		logger.info("Adding Measurement to queue:"+new ObjectMapper().writeValueAsString(measurement));

		logger.info("Measurement added to queue:"+new ObjectMapper().writeValueAsString(measurement));
	}

}
