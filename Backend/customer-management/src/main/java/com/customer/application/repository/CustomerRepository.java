package com.customer.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.customer.application.entity.Customer;

import java.util.List;

import static com.customer.application.constants.Constants.GET_CUSTOMERS_WITH_ORDER_QUERY;


@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long>{

    @Query(GET_CUSTOMERS_WITH_ORDER_QUERY)
    List<Customer> findAllWithOrders();
}
