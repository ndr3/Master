package com.qamanagement.core.data.service;

import java.util.List;

import com.qamanagement.core.data.model.Employee;

public interface EmployeeService {
	
	List<Employee> getAllUserEmployees(String email);

	void save(Employee employee);

	Employee getEmployeeById(Long id);

	void update(Employee employee);

}
