package com.customer.application.service;

import com.customer.application.entity.Customer;
import com.customer.application.entity.Order;
import com.customer.application.entity.OrderList;
import com.customer.application.exception.CustomerException;
import com.customer.application.exception.OrderException;
import com.customer.application.exception.ResourceNotFoundException;
import com.customer.application.model.CustomerModel;
import com.customer.application.repository.CustomerRepository;
import com.customer.application.repository.OrderListRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.customer.application.constants.Constants.*;

@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private OrderListRepository orderListRepository;

    public List<Customer> getAllCustomers(){
        final String METHOD_NAME = this.getClass().getName() + " :: getAllCustomers :: ";
        log.info(METHOD_NAME);
        try {
            return customerRepository.findAll();
        }catch (CustomerException e){
            log.info(METHOD_NAME + EXCEPTION_MESSAGE, e.getMessage(), e);
            throw new CustomerException(INTERNAL_SERVER_GET_ERROR_MESSAGE);
        }
    }

    public Customer createCustomer(CustomerModel customerModel) {
        final String METHOD_NAME = this.getClass().getName() + " :: createCustomer :: ";
        log.info(METHOD_NAME + "customerModel :: {} ", customerModel);
        try {
            Customer customer = Customer.builder()
                    .firstname(customerModel.getFirstname())
                    .lastname(customerModel.getLastname())
                    .address(customerModel.getAddress())
                    .city(customerModel.getCity())
                    .build();
            Customer savedCustomer = customerRepository.save(customer);
            log.info(METHOD_NAME + "Customer entity saved and data received");
            return savedCustomer;

        }catch (CustomerException e){
            log.info(METHOD_NAME + EXCEPTION_MESSAGE, e.getMessage(), e);
            throw new CustomerException(INTERNAL_SERVER_SAVE_ERROR_MESSAGE);
        }
    }

    public Customer getCustomerById(Long id) {
        final String METHOD_NAME = this.getClass().getName() + " :: getCustomerById :: ";
        log.info(METHOD_NAME + "id :: {} ", id);
        return customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(CUSTOMER_DOES_NOT_EXIST + id));
    }

    public Customer deleteCustomer(Long id){
        final String METHOD_NAME = this.getClass().getName() + " :: deleteCustomer :: ";
        log.info(METHOD_NAME + "id :: {} ", id);
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(CUSTOMER_DOES_NOT_EXIST + id));
        customerRepository.delete(customer);
        return customer;

    }
}
