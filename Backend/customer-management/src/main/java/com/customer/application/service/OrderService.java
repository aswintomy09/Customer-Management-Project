package com.customer.application.service;

import com.customer.application.entity.Order;
import com.customer.application.entity.OrderList;
import com.customer.application.model.OrderModel;

import java.util.List;

public interface OrderService {
    List<OrderList> getOrderDropdownList();

    List<Order> saveOrderForCustomer(List<OrderModel> orders);
}
