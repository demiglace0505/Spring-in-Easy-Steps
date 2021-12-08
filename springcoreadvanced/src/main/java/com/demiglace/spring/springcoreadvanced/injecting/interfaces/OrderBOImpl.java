package com.demiglace.spring.springcoreadvanced.injecting.interfaces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("bo")
public class OrderBOImpl implements OrderBO {
	@Autowired
	@Qualifier("dao")
	private OrderDAO dao;

	@Override
	public void placeOrder() {
		// TODO Auto-generated method stub
		System.out.println("Inside OrderBO placeOrder()");
		dao.createOrder();
	}

	public OrderDAO getDao() {
		return dao;
	}

	public void setDao(OrderDAO dao) {
		this.dao = dao;
	}

}
