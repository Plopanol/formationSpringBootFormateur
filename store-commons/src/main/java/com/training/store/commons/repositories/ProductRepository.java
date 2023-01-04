package com.training.store.commons.repositories;

import com.training.store.commons.entities.Product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {

    List<Product> findByCategory_Name(String categoryName);

    @Query("SELECT p FROM Product p inner join p.category c WHERE c.name like ?1% ")
    List<Product> findByCategory(String categoryName);

}
