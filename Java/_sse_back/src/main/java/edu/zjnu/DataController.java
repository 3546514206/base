package edu.zjnu;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Map;


/**
 * <p>
 * DataController
 * </p>
 *
 * @author 杨海波
 */
@RestController
public class DataController {

    @GetMapping(value = "/data", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> streamEvents() {
        return Flux.interval(Duration.ofSeconds(1)).map(sequence -> ChineseCharacterGenerator.getRandomChineseCharacter()).doOnCancel(() -> System.out.println("Client disconnected"));
    }

    @PostMapping(value = "/test", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> test(@RequestBody Map<String, Object> req) {
        return req;
    }
}
