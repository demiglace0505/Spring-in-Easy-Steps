package com.demiglace.spring.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.demiglace.spring.springmvc.dto.Employee;

@Controller
public class ObjectController {
	@RequestMapping("/readObject")
	public ModelAndView sendObject() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("displayObject");
		
		Employee employee = new Employee();
		employee.setId(1234);
		employee.setName("doge");
		employee.setSalary(3000);
		modelAndView.addObject("employee", employee);
		
		return modelAndView;
	}
}
