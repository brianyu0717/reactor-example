package reactor;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.function.Consumer;

public class MonoTest {
    private final static Logger log = LoggerFactory.getLogger(MonoTest.class);

    @Test
    void ok() {
        final String data = "data_";
        final Mono<String> mono = Mono.just(data).log();

        final Consumer<Throwable> errorConsumer = Throwable::printStackTrace;
        final Consumer<String> itemConsumer = d -> log.info("Value is {}", d);
        final Runnable onCompletion = () -> log.info("Done");

        mono.subscribe(itemConsumer, errorConsumer, onCompletion);

        StepVerifier.create(mono)
                .expectNext(data)
                .verifyComplete();
    }

    @Test
    void failMonoMap() {
        final String data = "data_";
        final Mono<String> mono = Mono.just(data)
                .map(s -> {throw new RuntimeException("bad data");});

        final Consumer<Throwable> errorConsumer = Throwable::printStackTrace;
        final Consumer<String> itemConsumer = d -> log.info("Value is {}", d);
        final Runnable onCompletion = () -> log.info("Done");

        mono.subscribe(itemConsumer, errorConsumer, onCompletion);

        StepVerifier.create(mono)
                .expectError(RuntimeException.class)
                .verify();
    }
}
