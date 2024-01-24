package com.customer.application.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerModel {

    private String firstname;

    private String lastname;

    private String address;

    private String city;

    private String state;

    private String orders;

    private Integer orderTotal;
}
