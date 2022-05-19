package com.nttdata.bootcamp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.nttdata.bootcamp.models.CustomerEnterprise;
import com.nttdata.bootcamp.models.product.ProductEnterprise;
import com.nttdata.bootcamp.models.product.ProductPerson;
import com.nttdata.bootcamp.repositories.ICustomerEnterpriseRepo;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerEnterpriseImp implements IServiceCustomerEnterprise {

	
	@Autowired
	private ICustomerEnterpriseRepo enterpriseRepo;
	
	@Autowired
	private RestTemplate clientRest;
	
	@Override
	public Flux<CustomerEnterprise> findAll() {
		
		return enterpriseRepo.findAll().flatMap(enterprise->{
			ProductEnterprise productEnterprise = clientRest.getForObject("http://localhost:8020/product-enterprise/find-by-idCustomerEnterprise/"+enterprise.getId(), ProductEnterprise.class);
			enterprise.setProductEnterprise(productEnterprise);
			return Flux.just(enterprise);
		});
	}

	@Override
	public Mono<CustomerEnterprise> findById(String id) {
		
		ProductEnterprise productEnterprise = clientRest.getForObject("http://localhost:8020/product-enterprise/find-by-idCustomerEnterprise/"+id, ProductEnterprise.class);
		return enterpriseRepo.findById(id).flatMap(p->{
			p.setProductEnterprise(productEnterprise);
			return Mono.just(p);
		});
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
