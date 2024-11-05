package com.study.webfluxstudy;

import com.study.webfluxstudy.example.User;
import java.time.Duration;
import java.util.function.Supplier;
import org.junit.jupiter.api.Test;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class StepVerifierAndHowToUseIt {

    @Test
    void stepVerifierTest() {
        // 테스트할 Flux 생성
        Flux<String> flux = Flux.just("Hello", "WebFlux", "World");

        // StepVerifier를 사용한 테스트
        StepVerifier.create(flux)
                .expectNext("Hello")
                .expectNext("WebFlux") 
                .expectNext("World")
                .verifyComplete();
    }

    @Test
    void stepVerifierErrorTest() {
        // 에러를 발생시키는 Flux 생성
        Flux<String> flux = Flux.error(new RuntimeException("에러 발생!"));

        // 에러 검증
        StepVerifier.create(flux)
                .expectError(RuntimeException.class)
                .verify();
    }

//========================================================================================

    // TODO Use StepVerifier to check that the flux parameter emits "foo" and "bar" elements then completes successfully.
    void expectFooBarComplete(Flux<String> flux) {
        StepVerifier.create(flux).expectNext("foo", "bar").verifyComplete();
    }

//========================================================================================

    // TODO Use StepVerifier to check that the flux parameter emits "foo" and "bar" elements then a RuntimeException error.
    void expectFooBarError(Flux<String> flux) {
        StepVerifier.create(flux).expectError(RuntimeException.class);
    }

//========================================================================================

    // TODO Use StepVerifier to check that the flux parameter emits a User with "swhite"username
    //  and another one with "jpinkman" then completes successfully.
    void expectSkylerJesseComplete(Flux<User> flux) {
        StepVerifier.create(flux)
                .assertNext(user -> {assert user.getUsername().equals("swhite");})
                .assertNext(user -> {assert user.getUsername().equals("jpinkman");})
                .verifyComplete();
    }

//========================================================================================

    // TODO Expect 10 elements then complete and notice how long the test takes.
    void expect10Elements(Flux<Long> flux) {
        StepVerifier.create(flux).expectNextCount(10).verifyComplete();
    }

//========================================================================================

    // TODO Expect 3600 elements at intervals of 1 second, and verify quicker than 3600s
    // by manipulating virtual time thanks to StepVerifier#withVirtualTime, notice how long the test takes
    void expect3600Elements(Supplier<Flux<Long>> supplier) {
        StepVerifier.withVirtualTime(supplier)
                .thenAwait(Duration.ofSeconds(3600))
                .expectNextCount(3600)
                .verifyComplete();
    }
}
