package com.nttdata.bootcamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;

import com.nttdata.bootcamp.models.Customer;
import com.nttdata.bootcamp.repositories.ICustomerRepo;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
@SpringBootApplication
public class BootcampServiceCustomerApplication implements CommandLineRunner {
	
	@Autowired
	ICustomerRepo customerRepo;

	@Autowired
	ReactiveMongoTemplate mongoTemplate;

	public static void main(String[] args) {
		SpringApplication.run(BootcampServiceCustomerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		mongoTemplate.dropCollection("customers").subscribe();
		
		Flux.just(new Customer("a1","omar","lazo"),
				new Customer("a2","alina","reyes")).flatMap(c->{
					return customerRepo.save(c);
				}).subscribe(p->log.info("Se inserto: "+p));
	}

}
