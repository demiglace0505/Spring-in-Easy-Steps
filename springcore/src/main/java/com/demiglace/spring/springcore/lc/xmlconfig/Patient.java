package com.demiglace.spring.springcore.lc.xmlconfig;

public class Patient {
	private int id;
	
	public void hi() {
		System.out.println("Inside Hi Method");
	}
	
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
