package org.springframework.ai.openai.samples.helloworld;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner cli(ChatClient.Builder builder, ConfigurableApplicationContext context) {
        return args -> {
            var chat = builder.build();
            System.out.println("\nSpring AI Hello World!");
            System.out.println("USER: Tell me a joke");
            System.out.println("ASSISTANT: " + 
                    chat.prompt("Tell me a joke").call().content());
            System.out.println("\nHello World demo completed!");
            context.close();
        };
    }
}
