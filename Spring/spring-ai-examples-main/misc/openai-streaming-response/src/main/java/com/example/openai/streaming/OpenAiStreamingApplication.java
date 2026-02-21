package com.example.openai.streaming;

import reactor.core.publisher.Flux;

import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class OpenAiStreamingApplication {
	public static void main(String[] args) {
		SpringApplication.run(OpenAiStreamingApplication.class, args);
	}
}

@RestController
@RequestMapping("/ai")
class ChatController {
	private final OpenAiChatModel chatModel;

	@Autowired
	public ChatController(OpenAiChatModel chatModel) {
		this.chatModel = chatModel;
	}

	@GetMapping(value = "/generateStream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<ChatResponse> generateStream(
			@RequestParam(value = "message", defaultValue = "Tell me a joke") String message) {
		return chatModel.stream(new Prompt(new UserMessage(message)));
	}
}
