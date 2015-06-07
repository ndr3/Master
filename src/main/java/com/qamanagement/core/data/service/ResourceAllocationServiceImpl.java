package com.qamanagement.core.data.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qamanagement.core.data.dao.EmployeeDao;
import com.qamanagement.core.data.dao.ProjectDao;
import com.qamanagement.core.data.dao.ResponsibilityDao;
import com.qamanagement.core.data.dao.WeekResponsibilityDao;
import com.qamanagement.core.data.dao.WeekResponsibilityEmployeeDao;
import com.qamanagement.core.data.model.Employee;
import com.qamanagement.core.data.model.Responsibility;
import com.qamanagement.core.data.model.WeekResponsibility;
import com.qamanagement.core.data.model.WeekResponsibilityEmployee;

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
	private WeekResponsibilityEmployeeDao weekResponsibilityEmployeeDao;

	@Autowired
	private EmployeeDao employeeDao;

	@Override
	public void allocateResources(String email) {
		weekResponsibilityEmployeeDao.truncate();
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

			sortEmployeesByNumberOfResponsibilities(employees);

			for (Employee employee : employees) {
				List<Responsibility> employeeResponsibilities = responsibilityDao
						.getAllResponsibilitiesByJobClassification(employee
								.getJobClassification().getId());
				boolean assigned = false;
				for (Responsibility responsibility : employeeResponsibilities) {
					Map<Long, Integer> noOfEmployeesByProject = numberOfEmployeesByResponsabilityAndProject
							.get(responsibility.getName());
					Set<Long> projectIds = noOfEmployeesByProject.keySet();
					for (Long projctId : projectIds) {
						if (noOfEmployeesByProject.get(projctId) > 0) {
							int noOfEmployees = noOfEmployeesByProject
									.get(projctId);
							noOfEmployees = noOfEmployees - 1;
							noOfEmployeesByProject.put(projctId, noOfEmployees);
							assigned = true;
							WeekResponsibility weekResponsibility = weekResponsibilityDao
									.getWeekRespByWeekNumberAndProjectIdAndResponsibilityId(
											i, projctId, responsibility.getId());
							WeekResponsibilityEmployee weekResponsibilityEmployee = new WeekResponsibilityEmployee();
							weekResponsibilityEmployee.setEmployee(employee);
							weekResponsibilityEmployee
									.setWeekResponsibility(weekResponsibility);
							weekResponsibilityEmployeeDao
									.save(weekResponsibilityEmployee);
							break;
						}
					}
					if (assigned) {
						break;
					}

				}
			}

		}

	}

	private void sortEmployeesByNumberOfResponsibilities(
			List<Employee> employees) {

		Collections.sort(employees, new Comparator<Employee>() {
			public int compare(Employee e1, Employee e2) {
				int noOfResponsibilities1 = e1.getJobClassification()
						.getJobResponsibilities().size();
				int noOfResponsibilities2 = e2.getJobClassification()
						.getJobResponsibilities().size();

				return (noOfResponsibilities1 < noOfResponsibilities2) ? -1
						: ((noOfResponsibilities1 == noOfResponsibilities2) ? 0
								: 1);
			}
		});
	}
}
