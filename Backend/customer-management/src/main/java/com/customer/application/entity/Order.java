package com.customer.application.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "Orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "Item")
    private String item;

    @Column(name = "Price")
    private Integer price;

    @Column(name="Qty")
    private Integer quantity;

    @Column(name="OrderTotal")
    private Integer orderTotal;

    @Column(name = "customerId")
    private Integer customerId;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "customerId", referencedColumnName = "id")
//    @JsonIgnore
//    private Customer customer;
}
