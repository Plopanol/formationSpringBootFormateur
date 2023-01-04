package com.training.store.commons.services;

import com.training.store.commons.entities.Category;
import com.training.store.commons.entities.Product;
import com.training.store.commons.repositories.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    public Product save(Product p) {
        return productRepository.save(p);
    }

    @Override
    public List<Product> findAllByCategorie(Category category) {
        return productRepository.findAll(hasCategory(category));
    }

    /**
     * Methode pour des requetes par specification
     * 
     * @param category
     * @return
     */
    static Specification<Product> hasCategory(Category category) {
        return (product, cq, cb) -> cb.equal(product.get("category"), category);
    }

}
