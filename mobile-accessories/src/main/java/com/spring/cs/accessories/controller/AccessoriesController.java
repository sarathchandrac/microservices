package com.spring.cs.accessories.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.cs.accessories.model.Accessory;
import com.spring.cs.accessories.repository.AccessoriesRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("accessories")
public class AccessoriesController {
	@Autowired
	AccessoriesRepository accessoriesRepository;

	@GetMapping
	public Flux<Accessory> getAccessories() {

		return accessoriesRepository.findAll();
	}

	@GetMapping("{id}")
	public Mono<Accessory> getAccessoryById(@PathVariable("id") String id) {

		return accessoriesRepository.findById(id);
	}

	@GetMapping("/name/{name}")
	public Mono<Accessory> getAccessoryByName(@PathVariable String name) {
		return accessoriesRepository.findByName(name);
	}

	@PostMapping
	public Mono<Accessory> saveAccessory(@RequestBody Accessory accessory) {
		UUID uuid = UUID.randomUUID();
		accessory.setId(uuid.toString());
		accessory.setActive(true);
		return accessoriesRepository.save(accessory);
	}

	@PutMapping
	public Mono<Accessory> updateAccessory(@RequestBody Accessory accessory) {
		return accessoriesRepository.save(accessory);
	}

	@DeleteMapping("/{id}")
	public Mono<String> deleteAccessory(@PathVariable String id) {
		Mono<Accessory> accessory = accessoriesRepository.findById(id);
		return accessory.flatMap(accessoryObj -> {
			System.err.println("error :: " + id);
			accessoryObj.setActive(false);
			Mono<Accessory> updateObj = accessoriesRepository.save(accessoryObj);
			return updateObj.flatMap(upm -> {
				return Mono.just("Successfully deleted the Accessory  - " + id);
			});
		});
	}
}
