package com.nttdata.bootcamp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.nttdata.bootcamp.models.CustomerPerson;
import com.nttdata.bootcamp.models.product.ProductPerson;
import com.nttdata.bootcamp.repositories.ICustomerPersonRepo;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerPersonImp implements IServiceCustomerPerson {

	@Autowired
	private ICustomerPersonRepo personRepo;
	
	@Autowired
	private RestTemplate clientRest;
	
	
	@SuppressWarnings("unchecked")
	@Override
	public Flux<CustomerPerson> findAll() {
		return personRepo.findAll().flatMap(person->{
			ProductPerson productPerson = clientRest.getForObject("http://localhost:8020/product-person/find-by-idCustomerPerson/"+person.getId(), ProductPerson.class);
			person.setProductPerson(productPerson);
			return Flux.just(person);
		});
	}
	

	@Override
	public Mono<CustomerPerson> findById(String id) {
		ProductPerson productPerson = clientRest.getForObject("http://localhost:8020/product-person/find-by-idCustomerPerson/"+id, ProductPerson.class);
		return personRepo.findById(id).flatMap(p->{
			p.setProductPerson(productPerson);
			return Mono.just(p);
		});
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
