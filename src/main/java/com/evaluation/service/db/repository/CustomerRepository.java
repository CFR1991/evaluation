package com.evaluation.service.db.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.evaluation.service.db.model.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Integer> {

}
