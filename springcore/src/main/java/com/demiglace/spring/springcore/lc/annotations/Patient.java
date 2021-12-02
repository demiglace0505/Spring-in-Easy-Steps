package com.demiglace.spring.springcore.lc.annotations;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class Patient  {
	private int id;
	
	@PostConstruct
	public void hi() {
		System.out.println("Inside Hi Method");
	}
	
	@PreDestroy
	public void bye() {
		System.out.println("Inside Bye Method");
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Patient [id=" + id + "]";
	}
}
