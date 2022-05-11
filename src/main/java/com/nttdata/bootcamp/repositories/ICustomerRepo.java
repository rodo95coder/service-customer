package com.nttdata.bootcamp.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.nttdata.bootcamp.models.Customer;

public interface ICustomerRepo extends ReactiveMongoRepository<Customer, String>{

}
