package com.qamanagement.core.data.dao;

import java.util.List;

import com.qamanagement.core.data.model.Employee;

public interface EmployeeDao {

	List<Employee> getAllUserEmployees(String email);

	void save(Employee employee);

	Employee getEmployeeById(Long id);

	void update(Employee employee);

}
