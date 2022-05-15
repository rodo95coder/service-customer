package com.nttdata.bootcamp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nttdata.bootcamp.models.CustomerEnterprise;
import com.nttdata.bootcamp.repositories.ICustomerEnterpriseRepo;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerEnterpriseImp implements IServiceCustomerEnterprise {

	
	@Autowired
	private ICustomerEnterpriseRepo enterpriseRepo;
	
	@Override
	public Flux<CustomerEnterprise> findAll() {
		return enterpriseRepo.findAll();
	}

	@Override
	public Mono<CustomerEnterprise> findById(String id) {
		return enterpriseRepo.findById(id);
	}

	@Override
	public Mono<CustomerEnterprise> save(CustomerEnterprise customerEnterprise) {
		return enterpriseRepo.save(customerEnterprise);
	}

	@Override
	public Mono<Void> delete(CustomerEnterprise customerEnterprise) {
		return enterpriseRepo.delete(customerEnterprise);
	}

}
