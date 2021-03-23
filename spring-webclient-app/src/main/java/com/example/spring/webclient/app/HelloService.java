package com.example.spring.webclient.app;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;

@Service
public class HelloService {
    private final WebClient webClient;

    public HelloService(WebClient webClient) {
        this.webClient = webClient;
    }

    public Flux<Message> makeReqReactive(int times) {
        return request("/message-reactive/{message}", times);
    }

    public Flux<Message> makeReqBlocking(int times) {
        return request("/message-blocking/{message}", times);
    }

    private Flux<Message> request(String uri, int times) {
        final ArrayList<Mono<Message>> monos = new ArrayList<>();

        for (int i = 0; i < times; i++) {
            monos.add(webClient.get()
                    .uri(uri, i).accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .bodyToMono(Message.class));
        }

        return Flux.merge(monos);
    }
}
