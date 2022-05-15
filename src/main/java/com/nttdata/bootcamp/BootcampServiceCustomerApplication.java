package com.nttdata.bootcamp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;

import com.nttdata.bootcamp.models.CurrentAccount;
import com.nttdata.bootcamp.models.CustomerPerson;
import com.nttdata.bootcamp.models.FixedTermAccount;
import com.nttdata.bootcamp.models.PersonalCredit;
import com.nttdata.bootcamp.models.ProductPerson;
import com.nttdata.bootcamp.models.SavingAccount;
import com.nttdata.bootcamp.repositories.ICustomerPersonRepo;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
@SpringBootApplication
public class BootcampServiceCustomerApplication implements CommandLineRunner {
	
	@Autowired
	ICustomerPersonRepo customerRepo;

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
		
		ProductPerson a = ProductPerson.builder().personalCredit(pc).savingAccount(sa).currentAccount(ca).fixedTermAccounts(lfta).build();

		Flux.just(CustomerPerson.builder()
				.firstName("omar")
				.lastName("lazo")
				.idDocument("123456")
				.productPerson(a)
				.build()).flatMap(c->{
					return customerRepo.save(c);
				}).subscribe(p->log.info("Se inserto: "+p));
	}

}
