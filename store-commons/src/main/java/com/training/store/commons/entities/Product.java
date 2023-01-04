package com.training.store.commons.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.AUTO;

@Entity
@Table(name = "product")
@SequenceGenerator(name = "seq_product", sequenceName = "seq_product", allocationSize = 10)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = AUTO, generator = "seq_product")
    private Long id;

    private String name;

    private String description;

    private Float price;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "category_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_product_category"))
    private Category category;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    @Builder.Default
    private Set<OrderLine> orderLines = new HashSet<>(0);

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "product_supplier",
        joinColumns = {@JoinColumn(name = "product_id", foreignKey = @ForeignKey(name = "fk_product_supplier_product"))},
        inverseJoinColumns = {
            @JoinColumn(name = "supplier_id", foreignKey = @ForeignKey(name = "fk_product_supplier_supplier"))})
    @Builder.Default
    private Set<Supplier> suppliers = new HashSet<>(0);

    public Product(String name, String description, Float price, Category category) {
        super();
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
    }

}
