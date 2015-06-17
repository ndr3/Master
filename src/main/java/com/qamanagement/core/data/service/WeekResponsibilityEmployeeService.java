package com.qamanagement.core.data.service;

import java.util.List;

import com.qamanagement.core.data.model.Employee;
import com.qamanagement.core.data.to.AssignmentEmployeeTO;

public interface WeekResponsibilityEmployeeService {

	List<Employee> getEmployeesForWeekResponsibility(long responsibilityId);

	List<AssignmentEmployeeTO> getEmployeeResponsibility(String username);

}
