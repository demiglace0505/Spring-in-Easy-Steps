package com.demiglace.spring.springcoreadvanced.standalone.collections;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"com/demiglace/spring/springcoreadvanced/standalone/collections/config.xml");
		ProductsList list = (ProductsList) context.getBean("productslist");
		System.out.println(list);
	}

}
