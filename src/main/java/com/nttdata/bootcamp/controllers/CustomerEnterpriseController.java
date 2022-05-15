package com.nttdata.bootcamp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nttdata.bootcamp.models.CustomerEnterprise;
import com.nttdata.bootcamp.services.IServiceCustomerEnterprise;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/enterprise")
public class CustomerEnterpriseController {

	@Autowired
	private IServiceCustomerEnterprise enterpriseRepo;
	
	@GetMapping("/find-all")
	public Flux<CustomerEnterprise> findAll() {
		Flux<CustomerEnterprise> enterprises = enterpriseRepo.findAll();
		log.info("all customer enterprises were consulted");
		return enterprises;
	}
	
	@GetMapping("/find-by-id/{id}")
	public Mono<CustomerEnterprise> findById(@PathVariable String id){
		Mono<CustomerEnterprise> enterprise = enterpriseRepo.findById(id);
		log.info("one customer enterprises was consulted by id");
		return enterprise;
	}
	
	@PutMapping("/update/{id}")
	public Mono<CustomerEnterprise> update(@RequestBody CustomerEnterprise customerEnterprise, @PathVariable String id){
		return enterpriseRepo.findById(id).flatMap(c->{
			c.setOwnerNames(customerEnterprise.getOwnerNames());
			c.setAuthorizedSigners(customerEnterprise.getAuthorizedSigners());
			c.setRucDocument(customerEnterprise.getRucDocument());
			c.setProductEnterprise(customerEnterprise.getProductEnterprise());
			log.info("costumer enterprise was updated");
			return enterpriseRepo.save(customerEnterprise);
		});
		
	}
	
	public Mono<CustomerEnterprise> save(@RequestBody CustomerEnterprise customerEnterprise){
		log.info("costumer enterprise was created");
		return enterpriseRepo.save(customerEnterprise);
	}
	
	@DeleteMapping("/delete/{id}")
	public Mono<Void> delete(@PathVariable String id){
		return enterpriseRepo.findById(id).flatMap(c->{
			return enterpriseRepo.delete(c);
		});
	}
	
	
}
