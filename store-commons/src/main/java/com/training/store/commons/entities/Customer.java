package com.training.store.commons.entities;

import static javax.persistence.GenerationType.AUTO;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "customer")
@SequenceGenerator(name = "seq_customer", sequenceName = "seq_customer", allocationSize = 10)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
	@Id
	@GeneratedValue(strategy = AUTO, generator = "seq_customer")
	private Long id;
	
	private String firstname;
	
	private String lastname;
	
	private String company;
	
	private String address;
	
	private String zipcode;
	
	private String city;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
	@Builder.Default
	private Set<Order> orders = new HashSet<>(0);
}
