package com.training.store.commons.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.AUTO;

@Entity
@Table(name = "category")
@SequenceGenerator(name = "seq_category", sequenceName = "seq_category", allocationSize = 10)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = AUTO, generator = "seq_category")
    private Long id;

    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "category")
    @Builder.Default
    @JsonIgnoreProperties("category")
    private Set<Product> products = new HashSet<>(0);

    public Category(String name, Set<Product> products) {
        super();
        this.name = name;
        this.products = products;
    }

    public Category(String name) {
        super();
        this.name = name;
    }

}
