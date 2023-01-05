package com.training.store.api.controllers;

import com.training.store.commons.entities.Product;
import com.training.store.commons.services.ProductService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/products", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    ProductService productService;

    @GetMapping
    public List<Product> getProducts() {
        return this.productService.findAll();
    }

    @GetMapping("/{nomCategorie}")
    public List<Product> getProductsByCategorie(@PathVariable(value = "nomCategorie") final String nomCategorie) {
        return this.productService.findByCategory_Name(nomCategorie);
    }

    @PostMapping
    public void save(@RequestBody Product product) {
        this.productService.save(product);
    }

}
