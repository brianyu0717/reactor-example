package com.spring.app;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.time.Duration;

@RestController
public class HelloController {

    public static final int DELAY = 2000;

    @GetMapping("/message-blocking/{message}")
    public Message handle(@PathVariable String message) throws InterruptedException {
        // Takes ~20s when queried 100 times simultaneously, overwhelming the service
        // Default netty has 10 threads and all 10 threads taken up by the Thread.sleep()

        Thread.sleep(DELAY);
        return new Message("Message: " + message);
    }

    @GetMapping("/message-reactive/{message}")
    public Mono<Message> handleReactive(@PathVariable String message) {
        // non-blocking, no threads are "delayed", it is just this mono taking 2s to publish to subscriber
        // we can subscribe as many as we like in parallel, it returns in ~2s

        return Mono.just(new Message("Message: " + message))
                .delayElement(Duration.ofMillis(DELAY));
    }
}
