package com.customer.application.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.customer.application.model.CustomerModel;
import com.customer.application.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.customer.application.exception.ResourceNotFoundException;
import com.customer.application.entity.Customer;
import com.customer.application.repository.CustomerRepository;

import static com.customer.application.constants.Constants.URL;

@CrossOrigin
@Slf4j
@RestController
@RequestMapping(URL)
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@GetMapping(value = "/customer")
	public List<Customer> getAllCustomers(){
		return customerService.getAllCustomers();
	}
	
	@PostMapping(value = "/customer")
	public ResponseEntity<Customer> createCustomer(@RequestBody CustomerModel customerModel) {
		final String METHOD_NAME = this.getClass().getName() + " :: createCustomer ::";

		log.info(METHOD_NAME + "calling createCustomer" +
				" method with " + "customerModel :: {} ", customerModel);
		Customer savedCustomer = customerService.createCustomer(customerModel);

		return new ResponseEntity<>(savedCustomer, HttpStatus.CREATED);
	}

	@GetMapping(value = "/customer/{id}")
	public Customer getCustomerById(@PathVariable Long id) {
		final String METHOD_NAME = this.getClass().getName() + " :: getCustomerById ::";
		log.info(METHOD_NAME + "calling getCustomerById method with id :: {} ", id);
        return customerService.getCustomerById(id);
	}

	@DeleteMapping(value = "/customer/{id}")
	public ResponseEntity<Map<String,Customer>> deleteCustomer(@PathVariable Long id){
		final String METHOD_NAME = this.getClass().getName() + " :: deleteCustomer ::";
		log.info(METHOD_NAME + "calling deleteCustomer" +
				" method with " + "id :: {} ", id);
		Customer customer = customerService.deleteCustomer(id);
		Map<String,Customer> response= new HashMap<>();
		response.put("Data deleted successfully",customer);
		return ResponseEntity.ok(response);

	}
}
