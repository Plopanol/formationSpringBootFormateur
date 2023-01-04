package com.training.store.commons.services;

import com.training.store.commons.entities.Category;
import com.training.store.commons.entities.Product;
import com.training.store.commons.repositories.CategoryRepository;
import com.training.store.commons.repositories.ProductRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Profile("local")
public class InitDataBase implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(InitDataBase.class);

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductService productService;

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public void run(String... args) throws Exception {
        logger.info("Base de donnée initialisée : Debut");
        Category catElectro = new Category("Electronique");
        productRepository.save(new Product("rasberyPie", "rasberyPie IV", 59.9f, catElectro));

        Category catNourriture = new Category("Nourriture");
        productRepository.save(new Product("Chocolat", "Tablette de chocolat", 9.9f, catNourriture));

        Category catSport = Category.builder().name("Sport").build();
        categoryRepository.save(catSport);

        List<Product> produit = productRepository.findByCategory_Name("Electronique");
        logger.info("Produit trouvé : " + produit.get(0).getName());

        List<Product> produit2 = productRepository.findByCategory("Electronique");
        logger.info("Produit trouvé par query : " + produit2.size());

        Specification<Product> productSpec = Specification.where((product, cq, cb) -> cb.equal(product.get("category").get("name"), "Nourriture"));
        List<Product> produitBySpecLambda = productRepository.findAll(productSpec);
        try {
            logger.info("Produit trouvé par produitBySpecLambda : " + produitBySpecLambda.get(0).getName());
        }
        catch (Exception e) {
            logger.error("Aucun produit trouvé");
        }

        List<Product> produitBySpecByService = productService.findAllByCategorie(catSport);
        logger.info("Produit trouvé par produitBySpecByService : " + produitBySpecByService.get(0).getName());

        logger.info("Base de donnée initialisée : Fin");

    }

}
