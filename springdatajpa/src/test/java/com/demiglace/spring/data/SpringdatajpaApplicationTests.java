package com.demiglace.spring.data;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import com.demiglace.spring.data.entities.Product;
import com.demiglace.spring.data.repos.ProductRepository;

@SpringBootTest
class SpringdatajpaApplicationTests {
	
	@Autowired
	ApplicationContext context;

	@Test
	public void saveProduct() {
		ProductRepository repository = context.getBean(ProductRepository.class);
		
		//CREATE
		Product product = new Product();
		product.setId(1L);
		product.setName("Legion 5");
		product.setDescription("Gaming Laptop");
		product.setPrice(199d);
		repository.save(product);
		
//		// READ
//		Optional<Product> productOptional = repository.findById(1L);
//		System.out.println(productOptional);
	
		
//		// UPDATE
//		if (productOptional.isPresent()) {
//			Product productToUpdate = productOptional.get();
//			productToUpdate.setPrice(1500d);
//			repository.save(productToUpdate);
//		}
//		
//		//FIND ALL
//		repository.findAll().forEach(p->{System.out.println(p.getPrice());});
		
		System.out.println(repository.findByName("Legion 5"));
		System.out.println(repository.findByPrice(199d));
		System.out.println(repository.findByNameAndPrice("Legion 5", 199d));
	}

}
