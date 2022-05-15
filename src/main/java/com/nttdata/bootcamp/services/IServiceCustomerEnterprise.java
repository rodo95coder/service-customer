package com.nttdata.bootcamp.services;

import com.nttdata.bootcamp.models.CustomerEnterprise;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IServiceCustomerEnterprise {
	
	public Flux<CustomerEnterprise> findAll();
	public Mono<CustomerEnterprise> findById(String id);
	public Mono<CustomerEnterprise> save(CustomerEnterprise customerEnterprise);
	public Mono<Void> delete(CustomerEnterprise customerEnterprise);
}
