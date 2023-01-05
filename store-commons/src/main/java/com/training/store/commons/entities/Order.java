package com.training.store.commons.entities;

import static javax.persistence.GenerationType.AUTO;

import java.util.Set;
import java.util.HashSet;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "T_ORDER")
@SequenceGenerator(name = "seq_order", sequenceName = "seq_order", allocationSize = 10)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {
	@Id
	@GeneratedValue(strategy = AUTO, generator = "seq_order")
	private Long id;
	
	private String reference;
	
	private Boolean shipped;
	
	private Boolean delivered;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "customer_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_order_customer"))
	@JsonIgnoreProperties("orders")
    private Customer customer;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "order", fetch = FetchType.EAGER)
	@Builder.Default
	@JsonIgnoreProperties("order")
	private Set<OrderLine> lines = new HashSet<>(0);
}
