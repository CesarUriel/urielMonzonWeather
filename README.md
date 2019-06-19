# weatherApp

The Weather App is capable to get the current weather from London and Hong Kong (Information provided by OpenWeatherMap.org).

# Prerequisites

You need the installation of:

 - Java 1.8
 - Maven (3.3 or later)
 - Git

# Installation
Run the **git** command:

```
git clone https://github.com/CesarUriel/weatherAppTest.git
```

Then, enter in WeatherApp directory and execute de **Maven** command:

```
mvn clean install
```

Exceute the **Maven Spring Boot** command to start the service

```
mvn spring-boot:run
```

This command will execute all the Test classes, but if you want execute only the Test classes, you can execute:

```
mvn clean test
```

# Endpoint examples
To test de app using Rest Clients, you can hit the next endpoints with the app started:

```
localhost:8081/app/weather/Hongkong
localhost:8081/app/weather/London
```

# Web App
In main directory **WeatherApp** exists the **WeatherCli** directory, this folder contains **index.html**, it can be executed in any explorer to consume the Weather services.

**NOTE: TO DO THIS YOU NEED START UP THE SERVICES**

# Responses
200 -> Ok
400 -> **RestClientException** When the OpenWeatherMap fails
404 -> **ResourceAccessException** When the request to OpenWeatherMap fails
500 -> For null or unexpected response

# Author
**Cesar Uriel Monzon Arredondo**
