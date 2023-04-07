package com.btg.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import model.CustomerRequest;

public interface CustomerRequestRepository extends MongoRepository<CustomerRequest, String> {

	List<CustomerRequest> findByCodigoCliente(int codCli);

}
