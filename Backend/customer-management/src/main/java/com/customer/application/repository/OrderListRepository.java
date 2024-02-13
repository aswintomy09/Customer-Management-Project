package com.customer.application.repository;

import com.customer.application.entity.OrderList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OrderListRepository extends JpaRepository<OrderList, Integer> {

}
