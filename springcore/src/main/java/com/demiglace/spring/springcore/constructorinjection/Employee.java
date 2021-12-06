package com.demiglace.spring.springcore.constructorinjection;

public class Employee {
	private int id;
	private Address address;
	
	Employee(int id, Address address){
		this.address = address;
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", address=" + address + "]";
	}
}
