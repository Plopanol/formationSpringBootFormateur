package com.training.store.commons.entities;

import static javax.persistence.GenerationType.AUTO;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "orderline")
@SequenceGenerator(name = "seq_orderline", sequenceName = "seq_orderline", allocationSize = 10)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderLine {
	@Id
	@GeneratedValue(strategy = AUTO, generator = "seq_orderline")
	private Long id;
	
	private Float quantity;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "order_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_orderline_order"))
	@JsonIgnoreProperties("lines")
    private Order order;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "product_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_orderline_product"))
	@JsonIgnoreProperties("orderLines")
    private Product product;
}
