package com.qamanagement.core.data.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qamanagement.core.data.dao.EmployeeDao;
import com.qamanagement.core.data.model.Employee;

@Service("employeeService")
@Transactional
public class EmployeeServiceImpl implements EmployeeService, Serializable {

	private static final long serialVersionUID = 7381776484519555928L;

	@Autowired
	private EmployeeDao employeeDao;

	@Override
	public List<Employee> getAllUserEmployees(String email) {
		return employeeDao.getAllUserEmployees(email);
	}

	@Override
	public void save(Employee employee) {
		employeeDao.save(employee);
	}

	@Override
	public Employee getEmployeeById(Long id) {
		return employeeDao.getEmployeeById(id);
	}

	@Override
	public void update(Employee employee) {
		employeeDao.update(employee);
	}

}
