package com.customer.application.service;

import com.customer.application.entity.Customer;
import com.customer.application.exception.ResourceNotFoundException;
import com.customer.application.model.CustomerModel;
import com.customer.application.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> getAllCustomers(){
        return customerRepository.findAll();
    }

    public Customer createCustomer(CustomerModel customerModel) {
        Customer customer = Customer.builder()
                .firstname(customerModel.getFirstname())
                .lastname(customerModel.getLastname())
                .address(customerModel.getAddress())
                .city(customerModel.getCity())
                .orders(customerModel.getOrders())
                .orderTotal(customerModel.getOrderTotal())
                .build();
        return customerRepository.save(customer);
    }

    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist:" +id));
    }

    public Customer deleteCustomer(Long id){
        Customer customer=customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist:" +id));
        customerRepository.delete(customer);
        return customer;

    }
}
