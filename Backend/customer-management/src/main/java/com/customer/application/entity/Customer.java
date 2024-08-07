package com.customer.application.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "customer")

public class Customer {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Column(name="FirstName")
	private String firstname;
	
	@Column(name="LastName")
	private String lastname;
	
	@Column(name="Address")
	private String address;
	
	@Column(name="City")
	private String city;
	
	@OneToMany
	@JoinColumn(name = "customerId", referencedColumnName = "id", insertable = false, updatable = false)
	private List<Order> orders;

}

