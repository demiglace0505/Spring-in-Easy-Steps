package com.demiglace.spring.springorm.product.test;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.demiglace.spring.springorm.product.dao.ProductDAO;
import com.demiglace.spring.springorm.product.entity.Product;

public class Test {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("com/demiglace/spring/springorm/product/test/config.xml");
		ProductDAO productDAO = (ProductDAO) context.getBean("productDAO");
//		Product product = new Product();
//		product.setId(2);
//		product.setName("legion 5");
//		product.setDesc("gaming laptop");
//		product.setPrice(1500);
//		productDAO.create(product);
//		productDAO.update(product);
//		productDAO.delete(product);
		
//		Product product = productDAO.find(2);
//		System.out.println(product);
		
		List<Product> products = productDAO.findAll();
		System.out.println(products);
	}

}
