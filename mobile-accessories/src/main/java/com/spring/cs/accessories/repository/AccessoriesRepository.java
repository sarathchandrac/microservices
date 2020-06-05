package com.spring.cs.accessories.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.spring.cs.accessories.model.Accessory;

import reactor.core.publisher.Mono;

public interface AccessoriesRepository extends ReactiveMongoRepository<Accessory, String> {

	Mono<Accessory> findByName(String name);

}
