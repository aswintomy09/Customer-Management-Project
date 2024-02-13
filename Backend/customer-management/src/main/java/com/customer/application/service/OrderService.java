package com.customer.application.service;

import com.customer.application.entity.Order;
import com.customer.application.entity.OrderList;

import java.util.List;

public interface OrderService {
    List<OrderList> getOrderDropdownList();
}
