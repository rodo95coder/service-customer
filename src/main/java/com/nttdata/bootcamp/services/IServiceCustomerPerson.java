package com.nttdata.bootcamp.services;

import com.nttdata.bootcamp.models.CustomerPerson;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IServiceCustomerPerson {
	
	public Flux<CustomerPerson> findAll();
	
	public Mono<CustomerPerson> findById(String id);
	
	public Mono<CustomerPerson> save(CustomerPerson customerPerson);
	
	public Mono<Void> delete(CustomerPerson customerPerson);
}
