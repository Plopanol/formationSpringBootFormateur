package com.training.store.commons.services;

import com.training.store.commons.entities.Product;
import com.training.store.commons.repositories.ProductRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = ProductService.class)
class ProductServiceTest {

    @MockBean  // Pas besoin de configurer une connexionBD
    private ProductRepository productRepository;

    private ProductService productService;

    @BeforeEach
    public void init() {
        productService = new ProductServiceImpl(productRepository);
    }

    @Test
    public void save() {
        // Given // Arrange
        Product p = new Product();
        when(productRepository.save(p)).thenReturn(p);

        // When // Act
        Product savedProduct = productService.save(p);

        // Then // Assert
        verify(productRepository, times(1)).save(p);
        assertThat(p).isEqualTo(savedProduct);
    }

}
