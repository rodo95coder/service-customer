package com.nttdata.bootcamp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;

import com.nttdata.bootcamp.models.CustomerEnterprise;
import com.nttdata.bootcamp.models.CustomerPerson;
import com.nttdata.bootcamp.models.product.BusinessCredit;
import com.nttdata.bootcamp.models.product.CurrentAccount;
import com.nttdata.bootcamp.models.product.FixedTermAccount;
import com.nttdata.bootcamp.models.product.PersonalCredit;
import com.nttdata.bootcamp.models.product.ProductEnterprise;
import com.nttdata.bootcamp.models.product.ProductPerson;
import com.nttdata.bootcamp.models.product.SavingAccount;
import com.nttdata.bootcamp.repositories.ICustomerEnterpriseRepo;
import com.nttdata.bootcamp.repositories.ICustomerPersonRepo;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
@SpringBootApplication
public class BootcampServiceCustomerApplication implements CommandLineRunner {
	
	@Autowired
	ICustomerPersonRepo personRepo;
	
	@Autowired
	ICustomerEnterpriseRepo enterpriseRepo;

	@Autowired
	ReactiveMongoTemplate mongoTemplate;

	public static void main(String[] args) {
		SpringApplication.run(BootcampServiceCustomerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		mongoTemplate.dropCollection("customer_persons").subscribe();
		
		PersonalCredit pc = PersonalCredit.builder().accountingBalance("100").availableBalance("100").build();
		SavingAccount sa = SavingAccount.builder().accountingBalance("100").maintenance("2").movementLimit("2").build();
		CurrentAccount  ca = CurrentAccount.builder().accountingBalance("150").maintenance("5").build();
		FixedTermAccount fta = FixedTermAccount.builder().accountingBalance("133").movementDay("01").build();
		List<FixedTermAccount> lfta = new ArrayList<>();
		lfta.add(fta);
		
		//ProductPerson a = ProductPerson.builder().personalCredit(pc).savingAccount(sa).currentAccount(ca).fixedTermAccounts(lfta).build();

		Flux.just(CustomerPerson.builder()
				.firstName("omar")
				.lastName("lazo")
				.idDocument("123456")
				.productPerson(null)
				.build()).flatMap(c->{
					return personRepo.save(c);
				}).subscribe(p->log.info("Se inserto customerPerson: "+p));
		
		mongoTemplate.dropCollection("customer_enterprises").subscribe();
		
		List<String> ownerNames = new ArrayList<>();
		ownerNames.add("alina");
		ownerNames.add("vanesa");
		List<String> authorizedSigners = new ArrayList<>();
		authorizedSigners.add("firma01");
		
		List<BusinessCredit> businessCredits = new ArrayList<>();
		BusinessCredit bc1 = BusinessCredit.builder().accountingBalance("524").availableBalance("543").build();
		BusinessCredit bc2 = BusinessCredit.builder().accountingBalance("524").availableBalance("543").build();
		businessCredits.add(bc1);
		businessCredits.add(bc2);
		List<CurrentAccount> currentAccounts = new ArrayList<>();
		CurrentAccount ca1 = CurrentAccount.builder().accountingBalance("159").maintenance("456").build();
		CurrentAccount ca2 = CurrentAccount.builder().accountingBalance("159").maintenance("456").build();
		currentAccounts.add(ca1);
		currentAccounts.add(ca2);
		
		//ProductEnterprise b = ProductEnterprise.builder().businessCredits(businessCredits).currentAccounts(currentAccounts).build();
		
		Flux.just(CustomerEnterprise.builder()
				.ownerNames(ownerNames)
				.authorizedSigners(authorizedSigners)
				.rucDocument("123456789")
				.productEnterprise(null)
				.build()).flatMap(z->{
					return enterpriseRepo.save(z); 
				}).subscribe(p->log.info("se inserto customerEnterprise: "+ p));
		
	}

}
