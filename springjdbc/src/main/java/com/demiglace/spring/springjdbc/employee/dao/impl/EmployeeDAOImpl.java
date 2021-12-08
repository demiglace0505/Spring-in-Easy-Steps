package com.demiglace.spring.springjdbc.employee.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.demiglace.spring.springjdbc.employee.dao.EmployeeDAO;
import com.demiglace.spring.springjdbc.employee.dao.rowmapper.EmployeeRowMapper;
import com.demiglace.spring.springjdbc.employee.dto.Employee;

@Component("employeeDAO")
public class EmployeeDAOImpl implements EmployeeDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public Employee read(int id) {
		String sql = "SELECT * from employee WHERE id=?";
		EmployeeRowMapper rowMapper = new EmployeeRowMapper();
		Employee employee = jdbcTemplate.queryForObject(sql, rowMapper, id);
		return employee;
	}

	@Override
	public List<Employee> read() {
		String sql = "SELECT * from employee";
		EmployeeRowMapper rowMapper = new EmployeeRowMapper();
		List<Employee> result = jdbcTemplate.query(sql, rowMapper);
		return result;
	}
	
	@Override
	public int delete(int id) {
		String sql = "DELETE from employee WHERE id=?";
		int result = jdbcTemplate.update(sql, id);
		return 0;
	}

	@Override
	public int create(Employee employee) {
		String sql = "INSERT into employee values(?,?,?)";
		int result = jdbcTemplate.update(sql, employee.getId(), employee.getFirstName(), employee.getLastName());
		return result;
	}

	@Override
	public int update(Employee employee) {
		String sql = "UPDATE employee set firstname=?, lastname=? WHERE id=?";
		int result = jdbcTemplate.update(sql, employee.getFirstName(), employee.getLastName(), employee.getId());
		return result;
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}





}
