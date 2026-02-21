package com.example.kotlin_hello_world

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.ai.chat.client.ChatClient
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.ConfigurableApplicationContext

import org.springframework.ai.chat.client.entity

data class Joke(val setup: String, val punchline: String)

@SpringBootApplication
class KotlinHelloWorldApplication {

	@Bean
	fun jokeRunner(chatClientBuilder: ChatClient.Builder, context: ConfigurableApplicationContext) = CommandLineRunner {
		val chatClient = chatClientBuilder.build();
		val response = chatClient.prompt().user("Tell me a joke").call().entity<Joke>()

		println("\nJoke:")
		println("Setup: ${response.setup}")
		println("Punchline: ${response.punchline}")
		
		// Exit the application after running
		context.close()
	}
}

fun main(args: Array<String>) {
	runApplication<KotlinHelloWorldApplication>(*args)
}
