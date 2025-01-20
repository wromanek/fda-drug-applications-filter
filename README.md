# FDA Drugs Record Applications Filter Service
Simple service that provides information about drugs based on [openFDA](https://open.fda.gov/apis/drug/event/how-to-use-the-endpoint/) data. Works as a simple proxy for filtering openFDA dataset with limited number of fields.

Additional feature is possibility to store and list particular drug record applications.

## Prerequisites for development
- JDK 21

## Configuration
openFDA API has some limitations in regard to the number of requests that can be made. To overcome this limitation, you can set an API key. To do this, you need to set the following environment variable:

- `openfda.api.key` - [API key for the openFDA API](https://open.fda.gov/apis/authentication/)

This service uses MongoDB in order to persist drug record applications. To configure MongoDB connection, you need to set the following environment variables:

- `spring.data.mongodb.host`
- `spring.data.mongodb.port`
- `spring.data.mongodb.database`
- `spring.data.mongodb.username`
- `spring.data.mongodb.password`

## How to test
To run the tests execute the following command in the root directory of the project:
```shell
./mvnw clean verify
```

## How to run
To run the application execute the following command in the root directory of the project:
```shell
./mvnw spring-boot:run
```

By default, the application will be available at [http://localhost:8080](http://localhost:8080). 

## API documentation
API documentation is available at [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html).
