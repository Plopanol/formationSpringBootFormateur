package com.training.store.commons.entities;

import static javax.persistence.GenerationType.AUTO;

import java.util.Set;
import java.util.HashSet;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "supplier")
@SequenceGenerator(name = "seq_supplier", sequenceName = "seq_supplier", allocationSize = 10)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Supplier {
	@Id
	@GeneratedValue(strategy = AUTO, generator = "seq_supplier")
	private Long id;
	
	private String name;
	
	@ManyToMany(mappedBy = "suppliers", cascade = CascadeType.ALL) 
	@Builder.Default
	private Set<Product> products = new HashSet<>(0);
}
