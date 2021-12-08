package com.demiglace.spring.springjdbc.employee.test;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import com.demiglace.spring.springjdbc.employee.dao.EmployeeDAO;
import com.demiglace.spring.springjdbc.employee.dto.Employee;

public class Test {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("com/demiglace/spring/springjdbc/employee/test/config.xml");
		EmployeeDAO dao = (EmployeeDAO) context.getBean("employeeDAO");
//		Employee employee = new Employee();
//		employee.setId(1);
//		employee.setFirstName("John");
//		employee.setLastName("Dalt");
//		int result = dao.create(employee);
//		int result = dao.update(employee);
//		int result = dao.delete(1);
//		Employee employee = dao.read(1);
		List<Employee> result = dao.read();
		System.out.println(result);
	}

}
