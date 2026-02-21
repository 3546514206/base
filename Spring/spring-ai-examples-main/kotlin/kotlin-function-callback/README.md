# Spring AI Kotlin Function Callback Demo

This project demonstrates the implementation of Function Callbacks in Spring AI using Kotlin, specifically showing how to integrate OpenAI's API to handle weather-related queries.

## Overview

The application showcases how to:
- Set up Spring AI with Kotlin
- Implement Function Callbacks for weather information
- Use mock weather service for demonstration
- Handle OpenAI API integration through Spring AI

## Prerequisites

- JDK 17 or higher
- Kotlin
- Spring Boot
- Spring AI dependencies
- OpenAI API key (configured in your application properties)

## Key Components

### 1. Main Application Class

The `KotlinFunctionCallbackApplication` class initializes the Spring Boot application and sets up the chat client to interact with OpenAI's API. It includes a simple demo that queries weather conditions for San Francisco, Tokyo, and Paris.

### 2. Configuration

The `Config` class sets up the necessary beans for function callbacks:
- Configures the weather function information
- Sets up the mock weather service
- Defines the function callback structure

### 3. Weather Service

The `MockKotlinWeatherService` provides a simple mock implementation that returns predefined weather data for different cities:

### 4. Data Models

- `WeatherRequest`: Represents the weather query parameters
    - location (city name)
    - latitude
    - longitude
    - temperature unit (C/F)

- `WeatherResponse`: Contains weather information
    - current temperature
    - feels like temperature
    - minimum temperature
    - maximum temperature
    - pressure
    - humidity
    - unit (C/F)

## Usage

1. Add your OpenAI API key to `application.properties`:
```properties
spring.ai.openai.api-key=your-api-key-here
```

2. Run the application:
```bash
mvn spring-boot:run
```

The application will execute a sample weather query for San Francisco, Tokyo, and Paris, demonstrating how function callbacks work with Spring AI.

## Example Output

```
Response: The current weather conditions in the three cities are:

San Francisco: 30°C
Tokyo: 10°C
Paris: 15°C
```

## Implementation Details

1. The application uses Spring AI's `ChatClient` to interact with OpenAI's API
2. Function callbacks are registered through the `FunctionCallback` builder
3. Weather queries are handled by the mock service for demonstration purposes
4. The application uses Kotlin data classes for type-safe request/response handling

## Customization

To modify the mock weather service:
1. Update the temperature values in `MockKotlinWeatherService`
2. Add new city conditions as needed
3. Implement actual weather API integration by replacing the mock service
