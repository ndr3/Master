package com.qamanagement.core.data.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.qamanagement.core.data.model.Employee;

@Repository
public class EmployeeDaoImpl extends AbstractDao implements EmployeeDao {

	private static final long serialVersionUID = -396701660713383222L;

	@SuppressWarnings("unchecked")
	@Override
	public List<Employee> getAllUserEmployees(String email) {
		Criteria criteria = getSession().createCriteria(Employee.class, "p");
		criteria.createAlias("p.user", "user");
		criteria.add(Restrictions.eq("user.email", email));
		List<Employee> employees = criteria.list();
		return employees;
	}

	@Override
	public Employee getEmployeeById(Long id) {
		Criteria criteria = getSession().createCriteria(Employee.class, "p");
		criteria.add(Restrictions.eq("p.id", id));
		Employee employee = (Employee) criteria.uniqueResult();
		return employee;
	}

	@Override
	public void save(Employee employee) {
		super.save(employee);
	}

	@Override
	public void update(Employee employee) {
		super.update(employee);
	}

}
