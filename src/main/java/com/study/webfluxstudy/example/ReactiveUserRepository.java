package com.study.webfluxstudy.example;

import java.util.List;
import java.util.concurrent.Flow.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class ReactiveUserRepository implements ReactiveRepository<User> {
    @Override
    public Mono<Void> save(Publisher<User> publisher) {
        System.out.print("saved.");
        return Mono.empty();
    }

    @Override
    public Mono<User> findFirst() {
        return Mono.just(User.JESSE);
    }

    @Override
    public Flux<User> findAll() {
        return Flux.fromIterable(List.of(User.JESSE, User.SAUL, User.SKYLER, User.WALTER));
    }

    @Override
    public Mono<User> findById(String id) {
        return Mono.just(User.SAUL);
    }
}
