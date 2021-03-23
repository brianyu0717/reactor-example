package com.example.spring.webclient.app;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class FluxController {
    private final HelloService helloService;

    public FluxController(HelloService helloService) {
        this.helloService = helloService;
    }

    @GetMapping("/flux-reactive/{times}")
    public Flux<Message> handleReactive(@PathVariable String times) {
        return helloService.makeReqReactive(Integer.parseInt(times));
    }

    @GetMapping("/flux-blocking/{times}")
    public Flux<Message> handleBlocking(@PathVariable String times) {
        return helloService.makeReqBlocking(Integer.parseInt(times));
    }
}
