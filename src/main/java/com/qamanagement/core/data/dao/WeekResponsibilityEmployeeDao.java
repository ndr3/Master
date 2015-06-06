package com.qamanagement.core.data.dao;

import java.util.List;

import com.qamanagement.core.data.model.Employee;
import com.qamanagement.core.data.model.WeekResponsibilityEmployee;

public interface WeekResponsibilityEmployeeDao {

	void save(WeekResponsibilityEmployee weekResponsibilityEmployee);

	void truncate();

	List<Employee> getEmployeesForWeekResponsibility(long responsibilityId);

}
