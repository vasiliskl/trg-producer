package com.trgint;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

import org.junit.jupiter.api.Test;

import com.google.gson.JsonObject;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;


/**
 * @author Vasilis Kleanthous
 * This class implements some basic tests for the /sensor/measurement REST endpoint
 *
 */
@QuarkusTest
public class SensorMeasurementResourceTest {

    @Test
    public void testValidMeasurement() {
		JsonObject json = new JsonObject();
	    
		json.addProperty("sensorId", 1);
		json.addProperty("latitude", 35.075204);
		json.addProperty("longitude", 33.389475);
		json.addProperty("humidity", 55.67);
		json.addProperty("temperature", 39.5);
		json.addProperty("pressure", 2.1);
    	
        given()
          .when().contentType(ContentType.JSON)
          .body(json.toString()).post("/sensor/measurement")
          .then()
             .statusCode(200)
             .body(is(""));
    }
    
    @Test
    public void testMissingSensorId() {
		JsonObject json = new JsonObject();
	    
		json.addProperty("latitude", 35.075204);
		json.addProperty("longitude", 33.389475);
		json.addProperty("humidity", 55.67);
		json.addProperty("temperature", 39.5);
		json.addProperty("pressure", 2.1);
    	
        given()
          .when().contentType(ContentType.JSON)
          .body(json.toString()).post("/sensor/measurement")
          .then()
             .statusCode(400);
    }

    @Test
    public void testInvalidMeasurements() {
		JsonObject json = new JsonObject();
	    
		json.addProperty("latitude", "abcd");
		json.addProperty("longitude", "efg");
		json.addProperty("humidity", 123);
		json.addProperty("temperature", 4567);
		json.addProperty("pressure", 890);
    	
        given()
          .when().contentType(ContentType.JSON)
          .body(json.toString()).post("/sensor/measurement")
          .then()
             .statusCode(400);
    }
}