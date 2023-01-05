package com.training.store.api.controllers;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

import com.training.store.commons.entities.Category;
import com.training.store.commons.repositories.CategoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/categories", produces = MediaType.APPLICATION_JSON_VALUE)
public class CategorieController {

    @Autowired
    CategoryRepository categoryRepository;

    @GetMapping
    public List<Category> getCategories() {
        return this.categoryRepository.findAll();
    }

    // @PostMapping("/{nomCategorie}")
    // public ResponseEntity<Category> saveCategorie(@PathVariable(value = "nomCategorie") final String nomCategorie) {
    // // Faire les verifications lol
    // Category catSave = this.categoryRepository.save(new Category(nomCategorie));
    // return new ResponseEntity<>(catSave, HttpStatus.OK);
    // }

    @PostMapping
    public void save(@RequestBody Category category) {
        this.categoryRepository.save(category);
    }

}
