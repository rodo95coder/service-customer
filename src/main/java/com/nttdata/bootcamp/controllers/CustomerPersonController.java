package com.nttdata.bootcamp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nttdata.bootcamp.models.CustomerPerson;
import com.nttdata.bootcamp.services.IServiceCustomerPerson;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/person")
public class CustomerPersonController {
	
	@Autowired
	private IServiceCustomerPerson personRepo;
	
	@GetMapping("/find-all")
	public Flux<CustomerPerson> findAll() {
		Flux<CustomerPerson> persons = personRepo.findAll();
		log.info("all customer persons were consulted");
		return persons;
	}
	
	@GetMapping("/find-by-id/{id}")
	public Mono<CustomerPerson> findById(@PathVariable String id){
		Mono<CustomerPerson> person = personRepo.findById(id);
		log.info("one customer enterprises was consulted by id");
		return person;
	}
	
	@PutMapping("/update")
	public Mono<CustomerPerson> update(@RequestBody CustomerPerson customerPerson){
		return personRepo.findById(customerPerson.getId()).flatMap(c->{
			c.setFirstName(customerPerson.getFirstName());
			c.setLastName(customerPerson.getLastName());
			c.setIdDocument(customerPerson.getIdDocument());
			c.setProductPerson(customerPerson.getProductPerson());
			log.info("costumer person was updated");
			return personRepo.save(c);
		});
		
	}
	
	@PostMapping("/create")
	public Mono<CustomerPerson> save(@RequestBody CustomerPerson customerPerson){
		log.info("costumer person was created");
		return personRepo.save(customerPerson);
	}
	
	@DeleteMapping("/delete/{id}")
	public Mono<Void> delete(@PathVariable String id){
		return personRepo.findById(id).flatMap(c->{
			log.info("costumer person was deleted");
			return personRepo.delete(c);
		});
	}

}
