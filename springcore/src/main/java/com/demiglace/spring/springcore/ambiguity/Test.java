package com.demiglace.spring.springcore.ambiguity;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"com/demiglace/spring/springcore/ambiguity/config.xml");
		Addition addition = (Addition) context.getBean("addition");
		System.out.println(addition);
	}

}
