package com.customer.application.service;

import com.customer.application.entity.OrderList;
import com.customer.application.exception.OrderException;
import com.customer.application.repository.OrderListRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction.*;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.customer.application.constants.Constants.EXCEPTION_MESSAGE;
import static com.customer.application.constants.Constants.INTERNAL_SERVER_GET_ERROR_MESSAGE;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderListRepository orderListRepository;


    @Override
    public List<OrderList> getOrderDropdownList() {
        final String METHOD_NAME = this.getClass().getName() + " :: getOrderDropdownList :: ";
        log.info(METHOD_NAME);
        try {
            return orderListRepository.findAll();
        }catch (OrderException e){
            log.info(METHOD_NAME + EXCEPTION_MESSAGE, e.getMessage(), e);
            throw new OrderException(INTERNAL_SERVER_GET_ERROR_MESSAGE);
        }
    }
}
