package com.demiglace.spring.springmvc.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.demiglace.spring.springmvc.dto.Employee;

@Controller
public class ListController {
	@RequestMapping("/readList")
	public ModelAndView sendList() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("displayList");
		
		Employee employee = new Employee();
		employee.setId(1);
		employee.setName("doge");
		employee.setSalary(3000);
		
		Employee employee2 = new Employee();
		employee2.setId(2);
		employee2.setName("cate");
		employee2.setSalary(2000);
		
		ArrayList<Employee> employees = new ArrayList<Employee>();
		employees.add(employee);
		employees.add(employee2);
		
		modelAndView.addObject("employees", employees);
		
		return modelAndView;
	}
}
