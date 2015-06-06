package com.qamanagement.core.data.service;

import java.util.List;

import com.qamanagement.core.data.model.Employee;

public interface WeekResponsibilityEmployeeService {

	List<Employee> getEmployeesForWeekResponsibility(long responsibilityId);

}
