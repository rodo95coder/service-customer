package com.nttdata.bootcamp.services;

import com.nttdata.bootcamp.models.product.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.nttdata.bootcamp.models.CustomerPerson;
import com.nttdata.bootcamp.repositories.ICustomerPersonRepo;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

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
			PersonalCredit personalCredit = clientRest.getForObject("http://localhost:8023/personalCredit/findById/"+person.getId(),PersonalCredit.class);
			SavingAccount savingAccount = clientRest.getForObject("http://localhost:8024/savingAccount/findById/"+person.getId(),SavingAccount.class);
			CurrentAccount currentAccount = clientRest.getForObject("http://localhost:8021/currentAccount/findById/"+person.getId(),CurrentAccount.class);
			List<FixedTermAccount> fixedTermAccounts = clientRest.getForObject("http://localhost:8021/fixedTermAccount/findById/"+person.getId(),List.class);
			ProductPerson productPerson =new ProductPerson(person.getId(), personalCredit, savingAccount, currentAccount, fixedTermAccounts);
			person.setProductPerson(productPerson);
			return Flux.just(person);
		});
	}
	

	@Override
	public Mono<CustomerPerson> findById(String id) {
		PersonalCredit personalCredit = clientRest.getForObject("http://localhost:8023/personalCredit/findById/"+id,PersonalCredit.class);
		SavingAccount savingAccount = clientRest.getForObject("http://localhost:8024/savingAccount/findById/"+id,SavingAccount.class);
		CurrentAccount currentAccount = clientRest.getForObject("http://localhost:8021/currentAccount/findById/"+id,CurrentAccount.class);
		List<FixedTermAccount> fixedTermAccounts = clientRest.getForObject("http://localhost:8021/fixedTermAccount/findById/"+id,List.class);
		ProductPerson productPerson =new ProductPerson(id, personalCredit, savingAccount, currentAccount, fixedTermAccounts);
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
