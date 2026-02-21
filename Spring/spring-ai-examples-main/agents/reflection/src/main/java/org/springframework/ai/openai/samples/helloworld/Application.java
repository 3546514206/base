package org.springframework.ai.openai.samples.helloworld;

import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner cli(ReflectionAgent reflectionAgent) {
        return args -> {
            var scanner = new Scanner(System.in);
            System.out.println("\nLet's chat!");

            // Generate a Java implementation of the Merge Sort algorithm
            while (true) {
                System.out.print("\nUSER: ");
                System.out.println("AGENT: " +
                        reflectionAgent.run(scanner.nextLine(), 2));
            }
        };
    }
}
