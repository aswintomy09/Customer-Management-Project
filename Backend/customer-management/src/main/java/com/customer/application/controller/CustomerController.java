package com.customer.application.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
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

@CrossOrigin
@RestController
@RequestMapping("/api/v1/")
public class CustomerController {

	@Autowired
	private CustomerRepository customerRepository;

	@GetMapping(value = "/customer")
	public List<Customer> getAllCustomers(){
		return customerRepository.findAll();
	}
	
	@PostMapping(value = "/customer")
	public Customer createCustomer(@RequestBody Customer customer) {
		return customerRepository.save(customer);
	}

	@GetMapping(value = "/customer/{id}")
	public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
		Customer customer=customerRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not exist:" +id));
		return ResponseEntity.ok(customer);
	}

	@DeleteMapping(value = "/customer/{id}")
	public ResponseEntity<Map<String,Boolean>> deleteCustomer(@PathVariable Long id){
		Customer customer=customerRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not exist:" +id));
		customerRepository.delete(customer);
		Map<String,Boolean> response= new HashMap<>();
		response.put("deleted",Boolean.TRUE);
		return ResponseEntity.ok(response);

	}
}
