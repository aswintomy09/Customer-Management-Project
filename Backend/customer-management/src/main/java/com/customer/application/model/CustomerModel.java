package com.customer.application.model;

import com.customer.application.entity.Order;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CustomerModel {

    private String firstname;

    private String lastname;

    private String address;

    private String city;

    private List<Order> orders;

    private Integer orderTotal;
}
