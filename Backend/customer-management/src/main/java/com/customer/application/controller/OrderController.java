package com.customer.application.controller;

import com.customer.application.entity.Customer;
import com.customer.application.entity.Order;
import com.customer.application.entity.OrderList;
import com.customer.application.model.CustomerModel;
import com.customer.application.model.OrderModel;
import com.customer.application.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.customer.application.constants.Constants.CUSTOMER_URL;
import static com.customer.application.constants.Constants.ORDER_URL;

@CrossOrigin(origins = "*")
@Slf4j
@RestController
@RequestMapping(CUSTOMER_URL)
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/order/dropdown")
    public ResponseEntity<List<OrderList>> getOrderDropdownList(){
        final String METHOD_NAME = this.getClass().getName() + " :: getOrderDropdownList ::";
        log.info(METHOD_NAME + "calling getOrderDropdownList method ");
        List<OrderList> orderList = orderService.getOrderDropdownList();
        return new ResponseEntity<>(orderList, HttpStatus.OK);
    }

    @PostMapping(value = "/order")
    public ResponseEntity<List<Order>> saveOrderForCustomer(@RequestBody List<OrderModel> orderModels) {
        final String METHOD_NAME = this.getClass().getName() + " :: saveOrderForCustomer ::";
        log.info(METHOD_NAME + "calling saveOrderForCustomer" +
                " method with " + "orderModels :: {} ", orderModels);
        List<Order> savedOrderList = orderService.saveOrderForCustomer(orderModels);
        return new ResponseEntity<>(savedOrderList, HttpStatus.CREATED);
    }
}
