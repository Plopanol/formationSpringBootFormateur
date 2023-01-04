package com.training.store.commons.services;

import com.training.store.commons.entities.Category;
import com.training.store.commons.entities.Product;

import java.util.List;

public interface ProductService {

    List<Product> findAllByCategorie(Category category);

}
