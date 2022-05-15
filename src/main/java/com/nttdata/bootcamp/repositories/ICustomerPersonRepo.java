package com.nttdata.bootcamp.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.nttdata.bootcamp.models.CustomerPerson;

public interface ICustomerPersonRepo extends ReactiveMongoRepository<CustomerPerson, String>{

}
