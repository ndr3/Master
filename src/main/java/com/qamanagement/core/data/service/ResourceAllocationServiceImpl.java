package com.qamanagement.core.data.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qamanagement.core.data.dao.EmployeeDao;
import com.qamanagement.core.data.dao.ProjectDao;
import com.qamanagement.core.data.dao.ResponsibilityDao;
import com.qamanagement.core.data.dao.WeekResponsibilityDao;
import com.qamanagement.core.data.model.Employee;
import com.qamanagement.core.data.model.Project;
import com.qamanagement.core.data.model.Responsibility;
import com.qamanagement.core.data.model.WeekResponsibility;

@Service("resourceAllocation")
@Transactional
public class ResourceAllocationServiceImpl implements ResourceAllocationService {

	@Autowired
	private ProjectDao projectDao;

	@Autowired
	private WeekResponsibilityDao weekResponsibilityDao;

	@Autowired
	private ResponsibilityDao responsibilityDao;

	@Autowired
	private EmployeeDao employeeDao;

	public void allocateResources(String email) {
		List<Project> projects = projectDao.getAllUserProjects(email);
		List<Employee> employees = employeeDao.getAllUserEmployees(email);
		List<Responsibility> responsibilities = responsibilityDao
				.getAllResponsibilities();
		for (int i = 1; i <= 12; ++i) {
			Map<String, Map<Long, Integer>> numberOfEmployeesByResponsabilityAndProject = new HashMap<String, Map<Long, Integer>>();
			for (Responsibility responsibility : responsibilities) {
				List<WeekResponsibility> weekResponsibilities = weekResponsibilityDao
						.getAllWeekRespByWeekNumberAndUserEmail(i, email,
								responsibility.getId());
				Map<Long, Integer> numberOfEmployeesByProject = new HashMap<Long, Integer>();
				for (WeekResponsibility weekResponsibility : weekResponsibilities) {
					numberOfEmployeesByProject.put(weekResponsibility
							.getWorkWeek().getProject().getId(),
							weekResponsibility.getNoOfEmployees());
				}
				numberOfEmployeesByResponsabilityAndProject.put(
						responsibility.getName(), numberOfEmployeesByProject);

			}

			for (Employee employee : employees) {

			}

		}

	}

}
