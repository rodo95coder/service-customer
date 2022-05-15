package com.nttdata.bootcamp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nttdata.bootcamp.models.CustomerPerson;
import com.nttdata.bootcamp.repositories.ICustomerPersonRepo;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerPersonImp implements IServiceCustomerPerson {

	@Autowired
	private ICustomerPersonRepo personRepo;
	
	@Override
	public Flux<CustomerPerson> findAll() {
		return personRepo.findAll();
	}

	@Override
	public Mono<CustomerPerson> findById(String id) {
		return personRepo.findById(id);
	}

	@Override
	public Mono<CustomerPerson> save(CustomerPerson customerPerson) {
		return personRepo.save(customerPerson);
	}

	@Override
	public Mono<Void> delete(CustomerPerson customerPerson) {
		return personRepo.delete(customerPerson);
	}

}
