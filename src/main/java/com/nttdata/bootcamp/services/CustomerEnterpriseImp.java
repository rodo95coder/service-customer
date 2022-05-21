package com.nttdata.bootcamp.services;

import com.nttdata.bootcamp.models.product.BusinessCredit;
import com.nttdata.bootcamp.models.product.CurrentAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.nttdata.bootcamp.models.CustomerEnterprise;
import com.nttdata.bootcamp.models.product.ProductEnterprise;
import com.nttdata.bootcamp.models.product.ProductPerson;
import com.nttdata.bootcamp.repositories.ICustomerEnterpriseRepo;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class CustomerEnterpriseImp implements IServiceCustomerEnterprise {

	
	@Autowired
	private ICustomerEnterpriseRepo enterpriseRepo;
	
	@Autowired
	private RestTemplate clientRest;
	
	@Override
	public Flux<CustomerEnterprise> findAll() {
		
		return enterpriseRepo.findAll().flatMap(enterprise->{
			List<BusinessCredit> businessCredits = clientRest.getForObject("http://localhost:8020/businessCredit/findById/"+enterprise.getId(), List.class);
			List<CurrentAccount> currentAccounts = clientRest.getForObject("http://localhost:8021/currentAccount/findById/"+enterprise.getId(), List.class);
			ProductEnterprise productEnterprise = new ProductEnterprise(enterprise.getId(),businessCredits,currentAccounts);
			enterprise.setProductEnterprise(productEnterprise);
			return Flux.just(enterprise);
		});
	}

	@Override
	public Mono<CustomerEnterprise> findById(String id) {
		List<BusinessCredit> businessCredits = clientRest.getForObject("http://localhost:8020/businessCredit/findById/"+id, List.class);
		List<CurrentAccount> currentAccounts = clientRest.getForObject("http://localhost:8021/currentAccount/findById/"+id, List.class);
		ProductEnterprise productEnterprise = new ProductEnterprise(id,businessCredits,currentAccounts);
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
