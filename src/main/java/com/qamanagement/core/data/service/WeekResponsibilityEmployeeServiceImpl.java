package com.qamanagement.core.data.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qamanagement.core.data.dao.WeekResponsibilityEmployeeDao;
import com.qamanagement.core.data.model.Employee;

@Service("weekResponsibilityEmployeeService")
@Transactional
public class WeekResponsibilityEmployeeServiceImpl implements WeekResponsibilityEmployeeService, Serializable{

	private static final long serialVersionUID = 2879557090130565939L;
	
	@Autowired
	private WeekResponsibilityEmployeeDao weekResponsibilityEmployeeDao;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Employee> getEmployeesForWeekResponsibility(long responsibilityId) {
		return weekResponsibilityEmployeeDao.getEmployeesForWeekResponsibility(responsibilityId);
	}

}
