package com.customer.application.service;

import com.customer.application.entity.Customer;
import com.customer.application.model.CustomerModel;

import java.util.List;

public interface CustomerService {
    List<Customer> getAllCustomers();

    Customer getCustomerById(Long id);

    Customer deleteCustomer(Long id);

    Customer createCustomer(CustomerModel customer);
}
