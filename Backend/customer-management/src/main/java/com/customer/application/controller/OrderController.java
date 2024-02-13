package com.customer.application.controller;

import com.customer.application.entity.Order;
import com.customer.application.entity.OrderList;
import com.customer.application.service.CustomerService;
import com.customer.application.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.customer.application.constants.Constants.ORDER_URL;

@CrossOrigin(origins = "*")
@Slf4j
@RestController
@RequestMapping(ORDER_URL)
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/dropdown")
    public ResponseEntity<List<OrderList>> getOrderDropdownList(){
        final String METHOD_NAME = this.getClass().getName() + " :: getOrderDropdownList ::";
        log.info(METHOD_NAME + "calling getOrderDropdownList method ");
        List<OrderList> orderList = orderService.getOrderDropdownList();
        return new ResponseEntity<>(orderList, HttpStatus.OK);
    }
}
