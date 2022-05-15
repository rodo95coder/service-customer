package com.nttdata.bootcamp.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.nttdata.bootcamp.models.CustomerEnterprise;

public interface ICustomerEnterpriseRepo extends ReactiveMongoRepository<CustomerEnterprise, String>{

}
