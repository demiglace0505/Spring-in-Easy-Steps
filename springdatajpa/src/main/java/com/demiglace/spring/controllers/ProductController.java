package com.demiglace.spring.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demiglace.spring.data.entities.Product;
import com.demiglace.spring.data.repos.ProductRepository;

@RestController
@RequestMapping("/products")
public class ProductController {
	@Autowired
	ProductRepository repository;
	
	@GetMapping
	public Iterable<Product> getProducts(){
		return repository.findAll();
	}
	
	@GetMapping("/{id}")
	public Optional<Product> getProduct(@PathVariable Long id) {
		Optional<Product> productOptional = repository.findById(id);
		return productOptional;
	}
	
	@PostMapping
	public Product create(@RequestBody Product product) {
		return repository.save(product);
	}
	
	@PutMapping
	public Product update(@RequestBody Product product) {
		return repository.save(product);
	}
}
