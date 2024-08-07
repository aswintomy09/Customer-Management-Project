package com.customer.application.service;

import com.customer.application.entity.Order;
import com.customer.application.entity.OrderList;
import com.customer.application.exception.OrderException;
import com.customer.application.model.OrderModel;
import com.customer.application.repository.OrderListRepository;
import com.customer.application.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.customer.application.constants.Constants.*;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderListRepository orderListRepository;

    @Autowired
    private OrderRepository orderRepository;


    @Override
    public List<OrderList> getOrderDropdownList() {
        final String METHOD_NAME = this.getClass().getName() + " :: getOrderDropdownList :: ";
        log.info(METHOD_NAME);
        try {
            return orderListRepository.findInOrder();
        } catch (OrderException e) {
            log.info(METHOD_NAME + EXCEPTION_MESSAGE, e.getMessage(), e);
            throw new OrderException(INTERNAL_SERVER_GET_ERROR_MESSAGE);
        }
    }

    @Override
    public List<Order> saveOrderForCustomer(List<OrderModel> orders) {
        final String METHOD_NAME = this.getClass().getName() + " :: saveOrderForCustomer :: ";
        log.info(METHOD_NAME);
        try {
            List<Order> orderList = orders.stream()
                    .map(orderModel -> Order.builder()
                            .item(orderModel.getItem())
                            .price(orderModel.getPrice())
                            .quantity(orderModel.getQuantity())
                            .orderTotal(orderModel.getPrice() * orderModel.getQuantity())
                            .customerId(orderModel.getCustomerId())
                            .build())
                    .collect(Collectors.toList());
            orderList.forEach(order -> orderRepository.save(order));
            log.info(METHOD_NAME + "Updating the stock value");
            this.updateStock(orderList);
            return orderList;
        } catch (OrderException e) {
            log.info(METHOD_NAME + EXCEPTION_MESSAGE, e.getMessage(), e);
            throw new OrderException(INTERNAL_SERVER_GET_ERROR_MESSAGE);
        }
    }

    public void updateStock(List<Order> orders) {
        final String METHOD_NAME = this.getClass().getName() + " :: updateStock :: ";
        log.info(METHOD_NAME + "orders :: {} ", orders);
        try {
            Map<String, Integer> orderItems = orders.stream()
                    .collect(Collectors.toMap(Order::getItem, Order::getQuantity));
            List<OrderList> orderList = orderListRepository.findAll();
            orderList.stream()
                    .filter(list -> orderItems.containsKey(list.getItem()) && list.getStock() > 0)
                    .forEach(list -> {
                        list.setStock(list.getStock() - orderItems.get(list.getItem()));
                        orderListRepository.save(list);
                    });
        } catch (OrderException e) {
            log.info(METHOD_NAME + EXCEPTION_MESSAGE, e.getMessage(), e);
            throw new OrderException(INTERNAL_SERVER_SAVE_ERROR_MESSAGE);
        }
    }

}
