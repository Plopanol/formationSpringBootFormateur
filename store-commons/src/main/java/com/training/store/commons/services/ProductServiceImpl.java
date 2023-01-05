package com.training.store.commons.services;

import com.training.store.commons.entities.Category;
import com.training.store.commons.entities.Product;
import com.training.store.commons.repositories.CategoryRepository;
import com.training.store.commons.repositories.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    public ProductServiceImpl(ProductRepository productRepository2) {
        productRepository = productRepository2;
    }

    @Override
    public Product save(Product p) {
        updateCategorySiExistante(p);
        return productRepository.save(p);
    }

    @Override
    public Product saveWithCategory(Product p, Category c) {
        Category categorySave = categoryRepository.save(c);
        p.setCategory(categorySave);
        return productRepository.save(p);
    }

    @Override
    public List<Product> findByCategory_Name(String nomCategorie) {
        return productRepository.findByCategory_Name(nomCategorie);
    }

    @Override
    public List<Product> findByCategory(String nomCategorie) {
        return productRepository.findByCategory(nomCategorie);
    }

    @Override
    public List<Product> findAll(String nomCategorie) {
        Specification<Product> productSpec = Specification.where((product, cq, cb) -> cb.equal(product.get("category").get("name"), nomCategorie));
        return productRepository.findAll(productSpec);
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

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    private void updateCategorySiExistante(Product p) {
        if (p.getCategory().getId() != null) {
            Optional<Category> catOpt = categoryRepository.findById(p.getCategory().getId());
            if (catOpt.isPresent()) {
                Category category = catOpt.get();
                p.setCategory(category);
            }
        }
    }
}
