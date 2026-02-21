# Kotlin Spring AI Hello World Application

A simple "Hello World" Spring Boot application written in Kotlin that demonstrates Spring AI integration by fetching jokes from OpenAI.

## Overview

This hello world application shows how to get started with Spring AI and OpenAI in a Kotlin Spring Boot project. When run, it connects to OpenAI's API and requests a joke, then displays the setup and punchline - a fun twist on the traditional "Hello World" example. The application demonstrates Kotlin's extension function capabilities by using `org.springframework.ai.chat.client.entity` to automatically convert the chat response into a `Joke` data object, showcasing how Kotlin's type-safe builders can make AI response handling more elegant and type-safe.

## Prerequisites

- JDK 17 or later
- OpenAI API key

## Dependencies

The application uses the following main dependencies:
- Spring Boot
- Spring AI OpenAI starter
- Kotlin standard library and reflection
- Spring Web starter

## Configuration

Add your OpenAI API key to `application.properties` or `application.yml`:
```yaml
spring.ai.openai.api-key=your-api-key-here
```

## How It Works

The application uses Spring AI's OpenAI integration to make API calls to OpenAI's service. The main components are:

1. A Spring Boot application class (`KotlinHelloWorldApplication`)
2. A `CommandLineRunner` bean that:
- Creates a chat client
- Sends a prompt requesting a joke
- Parses the response into a `Joke` object
- Prints the setup and punchline

## Running the Application

```bash
./mvnw spring-boot:run
```

The application will start, fetch a joke from OpenAI, and display it in the console.

## Example Output

```
Joke:
Setup: Why don't programmers like nature?
Punchline: It has too many bugs!
```

## Notes

- Make sure to keep your OpenAI API key secure and never commit it to version control
- The application requires an active internet connection to communicate with OpenAI's API
- Rate limits and API usage costs may apply based on your OpenAI account settings
