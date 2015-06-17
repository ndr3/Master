package com.qamanagement.core.data.service;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qamanagement.common.exception.ApplicationException;
import com.qamanagement.core.data.dao.EmployeeDao;
import com.qamanagement.core.data.dao.UserDao;
import com.qamanagement.core.data.model.Employee;
import com.qamanagement.core.data.model.Role;
import com.qamanagement.core.data.model.User;

@Service("employeeService")
@Transactional
public class EmployeeServiceImpl implements EmployeeService, Serializable {

	private static final long serialVersionUID = 7381776484519555928L;

	@Autowired
	private EmployeeDao employeeDao;
	
	@Autowired
	private UserDao userDao;

	@Override
	public List<Employee> getAllUserEmployees(String email) {
		return employeeDao.getAllUserEmployees(email);
	}

	@Override
	public void save(Employee employee, String email) {
		User user  = new User();
		Role role = new Role();
		role.setId(2L);
		user.setRole(role);
		user.setEmail(email);
		user.setDateCreated(new Date());
		user.setPassword("test");
		try {
			userDao.create(user);
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
		employee.setUserAccount(user);
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
