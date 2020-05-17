# trg-producer project

This project implements the producer of the queue.
It receives the sensor measurements by providing an end-point, does some basic validations and adds the sensor measurements in a queue.
The REST end-point provided is /sensor/measurement. Example:

POST URL

http://localhost:8080/sensor/measurement

Body

{
  "humidity": 23,
  "latitude": 30.099983,
  "longitude": 34.678934,
  "pressure": 2.3,
  "sensorId": 2,
  "temperature": 34.7
}

In addition, a Swagger UI is provided for documentation and can be also used to call the API. It can be accessed using the /swagger-ui path.
Example: http://localhost:8080/swagger-ui/


All configuration is defined in the application.properties file and can be overwritten by specifying system properties or environment variables.

There are also some basic tests implemented that test the REST end-point with different inputs.
They can be executed by running the command below. When executed, code coverage results are generated in file target/jacoco.exec

```
./mvnw clean test
```

In addition, there are 2 Dockerfiles that can be used to build the Docker image, 1 for jvm build and 1 for native build. Both can be found under src/main/docker.

To access the live metrics, you can use the /metrics/application endpoint, for example: http://localhost:8080/metrics/application .

There is also tracing of the API calls available and can be checked by installing and running Jaeger.

https://www.jaegertracing.io/download/

When run, Jaeger can be accessed and display the traces by using the following URL.

http://localhost:16686/

The application can be dynamically scaled horizontally by running multiple instances/containers of it, all of them will add data to the same topic of the queue.


## Running the application in dev mode

You can run your application in dev mode that enables live coding using:

```
./mvnw quarkus:dev -Dquarkus.http.port=8080 -Ddebug=5005
```

For each additional instance run on the same machine, the port numbers should be increased to avoid port conflicts.

## Packaging and running the application

The application can be packaged using `./mvnw package`.
It produces the `trg-producer-1.0.0-SNAPSHOT-runner.jar` file in the `/target` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/lib` directory.

The application is now runnable using `java -jar target/trg-producer-1.0.0-SNAPSHOT-runner.jar`.

## Creating a native executable

You can create a native executable using: `./mvnw package -Pnative`.

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: `./mvnw package -Pnative -Dquarkus.native.container-build=true`.

You can then execute your native executable with: `./target/trg-producer-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/building-native-image.
