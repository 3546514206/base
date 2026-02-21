# Spring AI Java Function Callback Demo with OpenAI
This Spring Boot application demonstrates the implementation of function callbacks using OpenAI's API and Spring AI. The app simulates weather information retrieval for different cities using a mock weather service.
## Overview
The application showcases how to:
- Integrate OpenAI's function calling capabilities with Spring Boot
- Handle weather-related queries using a mock weather service
- Process and respond to natural language requests for weather information
## Components
### Main Application (SpringAiJavaFunctionCallbackApplication)
The main application class contains:
- Spring Boot configuration
- Command line runner for demo execution
- Mock weather service implementation
- Temperature unit enumeration (Celsius/Fahrenheit)
### Weather Request (WeatherRequest)
Data model for weather requests with the following fields:
- location: City and state (e.g., "San Francisco, CA")
- lat: Latitude coordinate
- lon: Longitude coordinate
- unit: Temperature unit (C/F)
### Weather Response (WeatherResponse)
Data model for weather information containing:
- temp: Current temperature
- feels_like: "Feels like" temperature
- temp_min: Minimum temperature
- temp_max: Maximum temperature
- pressure: Atmospheric pressure
- humidity: Humidity percentage
- unit: Temperature unit (C/F)
## Mock Weather Service
The mock service provides simulated weather data for three cities:
- San Francisco (30.0°C)
- Tokyo (10.0°C)
- Paris (15.0°C)
## Usage Example
The application demonstrates usage through a command line runner:
```java
ChatResponse response = chatClient
.prompt("What are the weather conditions in San Francisco, Tokyo, and Paris?")
.functions("WeatherInfo")
.call()
.chatResponse();
```
## Dependencies
The project uses Maven for dependency management. All required dependencies are included in the pom.xml file:
- Spring Boot Starter (spring-boot-starter)
- Spring AI OpenAI Starter (spring-ai-openai-spring-boot-starter)
- Spring Boot Starter Test (spring-boot-starter-test)
- Jackson libraries for JSON processing
- Other Spring framework dependencies
  For the exact versions and complete list of dependencies, please refer to the pom.xml file in the project root.
## Setup

Clone the repository
Set your OpenAI API key in the application configuration:
```properties
openai.api.key=your_api_key_here
```
Run the application using:
```bash
./mvnw spring-boot:run
```

## Notes
- This is a demonstration application using mock data
- Real implementation would require integration with actual weather APIs
- Temperature values are hardcoded for demonstration purposes
