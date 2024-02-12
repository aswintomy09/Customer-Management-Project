package com.customer.application.controller;

import com.customer.application.entity.Customer;
import com.customer.application.entity.Order;
import com.customer.application.model.CustomerModel;
import com.customer.application.service.CustomerService;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;

import static com.customer.application.constants.Constants.URL;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.lenient;

public class CustomerControllerTest {

    @InjectMocks
    CustomerController customerController;

    @Mock
    CustomerService customerService;
    private ObjectMapper objectMapper;

    private AutoCloseable closeable;
    private MockMvc mockMvc;

    public static long ID = 1;

    @Before
    public void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.customerController)
                .build();
        this.objectMapper = new ObjectMapper();
    }

    @After
    public void closeService() throws Exception {
        closeable.close();
    }

    @Test
    public void getAllCustomersTest() throws Exception {
        lenient().when(customerService.getAllCustomers()).
                thenReturn(Collections.singletonList(this.getCustomerEntity()));
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get(URL + "/customer")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = this.mockMvc.perform(requestBuilder).andReturn();
        int actualResult = mvcResult.getResponse().getStatus();
        int expectedResult = HttpStatus.OK.value();
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void createCustomerTest() throws Exception {
        lenient().when(customerService.createCustomer(this.getCustomerModel())).
                thenReturn(this.getCustomerEntity());
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post(URL + "/customer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(this.getCustomerEntity()));
        MvcResult mvcResult = this.mockMvc.perform(requestBuilder).andReturn();
        int actualResult = mvcResult.getResponse().getStatus();
        int expectedResult = HttpStatus.CREATED.value();
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void deleteCustomerTest() throws Exception {
        lenient().when(customerService.deleteCustomer(any())).
                thenReturn(this.getCustomerEntity());
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete(URL + "/customer/" + ID)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = this.mockMvc.perform(requestBuilder).andReturn();
        int actualResult = mvcResult.getResponse().getStatus();
        int expectedResult = HttpStatus.OK.value();
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void getCustomerByIdTest() throws Exception {
        lenient().when(customerService.getCustomerById(any())).
                thenReturn(this.getCustomerEntity());
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get(URL + "/customer/" + ID)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = this.mockMvc.perform(requestBuilder).andReturn();
        int actualResult = mvcResult.getResponse().getStatus();
        int expectedResult = HttpStatus.OK.value();
        assertEquals(expectedResult, actualResult);
    }

    private Customer getCustomerEntity() {
        return Customer.builder()
                .id(ID)
                .firstname("Test first name")
                .lastname("Test last name")
                .city("Test city")
                .state("State")
                .address("Address")
                .orders(Collections.singletonList(this.getOrder()))
                .orderTotal(34)
                .build();
    }

    private CustomerModel getCustomerModel() {
        return CustomerModel.builder()
                .firstname("Test first name")
                .lastname("Test last name")
                .city("Test city")
                .state("State")
                .address("Address")
                .orders(Collections.singletonList(this.getOrder()))
                .orderTotal(34)
                .build();
    }

    private Order getOrder() {
        return Order.builder()
                .item("Item")
                .price(230)
                .build();
    }
}
