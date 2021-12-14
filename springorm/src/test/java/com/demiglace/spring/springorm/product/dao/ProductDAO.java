package com.demiglace.spring.springorm.product.dao;

import java.util.List;

import com.demiglace.spring.springorm.product.entity.Product;

public interface ProductDAO {
	int create(Product product);
	void update(Product product);
	void delete(Product product);
	
	Product find(int id);
	List<Product> findAll();
}
