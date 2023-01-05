package com.training.store.commons.services;

import com.training.store.commons.entities.Category;
import com.training.store.commons.entities.Product;
import com.training.store.commons.repositories.CategoryRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Profile("local")
public class InitDataBase implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(InitDataBase.class);

    @Autowired
    ProductService productService;

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public void run(String... args) throws Exception {
        logger.info("Base de donnée initialisée : Debut");
        Category catElectro = new Category("Electronique");
        productService.save(new Product("rasberyPie", "rasberyPie IV", 59.9f, catElectro));

        Category catNourriture = new Category("Nourriture");
        productService.save(new Product("Chocolat", "Tablette de chocolat", 9.9f, catNourriture));

        Category catSport = Category.builder().name("Sport").build();
        productService.saveWithCategory(new Product("Chaussure", "Chaussure de sport", 109.9f, null), catSport);

        List<Product> produit = productService.findByCategory_Name("Electronique");
        logger.info("Produit trouvé : " + produit.get(0).getName());

        List<Product> produit2 = productService.findByCategory("Electronique");
        logger.info("Produit trouvé par query : " + produit2.size());

        List<Product> produitBySpecLambda = productService.findAll("Nourriture");
        try {
            logger.info("Produit trouvé par produitBySpecLambda : " + produitBySpecLambda.get(0).getName());
        }
        catch (Exception e) {
            logger.error("Aucun produit trouvé");
        }

        List<Product> produitBySpecByService = productService.findAllByCategorie(catNourriture);
        logger.info("Produit trouvé par produitBySpecByService : " + produitBySpecByService.get(0).getName());

        logger.info("Base de donnée initialisée : Fin");

    }

}
