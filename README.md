# Train Information

This is a Java application built with Spring Boot that fetches data from the NS (Nederlandse Spoorwegen) API on trains, delays, departures, and other related information.

## Project Setup

### Prerequisites

- Java 1.8
- Maven

### Building the Project

To build the project, run the following command:

```sh
mvn clean install
```

### Running the Application

To run the application, use the following command:

```sh
mvn spring-boot:run
```

## API Endpoints

The application provides the following endpoints:

- `/trains` - Fetches information about trains.
- `/delays` - Fetches information about train delays.
- `/departures` - Fetches information about train departures.

## Dependencies

The project uses the following dependencies:

- Spring Boot Starter Web
- Spring Boot Starter Actuator
- Springfox Swagger2
- Springfox Swagger UI
- JAXB API
- Log4j2
- Commons Codec

## Note

This project is using an old version of the NS API which no longer works. An upgrade to the latest version of the NS API is needed.
