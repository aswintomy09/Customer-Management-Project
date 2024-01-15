package com.customer.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.customer.application.entity.Customer;


@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long>{

}
