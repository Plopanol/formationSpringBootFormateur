package com.training.store.commons.services;

import com.training.store.commons.entities.Category;
import com.training.store.commons.entities.Product;

import java.util.List;

public interface ProductService {

    List<Product> findAllByCategorie(Category category);

    Product save(Product p);

    List<Product> findByCategory_Name(String nomCategorie);

    List<Product> findByCategory(String nomCategorie);

    List<Product> findAll(String nomCategorie);

    Product saveWithCategory(Product p, Category c);

    List<Product> findAll();

}
