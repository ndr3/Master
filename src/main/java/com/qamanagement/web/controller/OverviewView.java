package com.qamanagement.web.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import com.qamanagement.core.data.model.Employee;
import com.qamanagement.core.data.model.Project;
import com.qamanagement.core.data.model.WeekResponsibility;
import com.qamanagement.core.data.model.WorkWeek;
import com.qamanagement.core.data.service.ProjectService;
import com.qamanagement.core.data.service.UserService;
import com.qamanagement.core.data.service.WeekResponsibilityEmployeeService;
import com.qamanagement.core.data.service.WeekResponsibilityService;
import com.qamanagement.core.data.service.WorkWeekService;
import com.qamanagement.core.data.to.ProjectOverviewTO;

@ManagedBean(name = "overviewView")
@ViewScoped
public class OverviewView implements Serializable {

	private static final long serialVersionUID = -4176685034187124134L;

	private User user;

	private List<ProjectOverviewTO> totalOverviewTO = new ArrayList<ProjectOverviewTO>();

	private List<ProjectOverviewTO> assignedOverviewTO = new ArrayList<ProjectOverviewTO>();

	private List<ProjectOverviewTO> unassignedOverviewTO = new ArrayList<ProjectOverviewTO>();

	private List<ColumnModel> columns = new ArrayList<OverviewView.ColumnModel>();

	@ManagedProperty(value = "#{weekResponsibilityEmployeeService}")
	private WeekResponsibilityEmployeeService weekResponsibilityEmployeeService;

	@ManagedProperty(value = "#{projectService}")
	private ProjectService projectService;

	@ManagedProperty(value = "#{workWeekService}")
	private WorkWeekService workWeekService;

	@ManagedProperty(value = "#{weekResponsibilityService}")
	private WeekResponsibilityService weekResponsibilityService;

	@ManagedProperty(value = "#{userService}")
	private UserService userService;
	
	@PostConstruct
	public void init() {
		loadData();
	}

	public void loadData() {
		user = (User) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		columns = new ArrayList<OverviewView.ColumnModel>();
		totalOverviewTO = new ArrayList<ProjectOverviewTO>();
		assignedOverviewTO = new ArrayList<ProjectOverviewTO>();
		unassignedOverviewTO = new ArrayList<ProjectOverviewTO>();
		List<Project> projects = projectService.getAllUserProjects(user
				.getUsername());
		for (Project project : projects) {
			ProjectOverviewTO totalOverview = new ProjectOverviewTO();
			List<String> total = new ArrayList<String>();
			ProjectOverviewTO assignedOverview = new ProjectOverviewTO();
			List<String> assigned = new ArrayList<String>();
			ProjectOverviewTO unassignedOverview = new ProjectOverviewTO();
			List<String> unassigned = new ArrayList<String>();
			List<WorkWeek> workWeeks = workWeekService
					.getAllProjectWorkWeeks(project.getId());
			for (WorkWeek workWeek : workWeeks) {
				int totalNumber = 0;
				int totalAssignedEmployeesNumber = 0;
				int totalUnassignedEmployeeNumber = 0;
				List<WeekResponsibility> workResponsibilities = weekResponsibilityService
						.getAllWorkWeekWeekResp(workWeek.getId());
				for (WeekResponsibility weekResponsibility : workResponsibilities) {
					List<Employee> assignedEmployes = weekResponsibilityEmployeeService
							.getEmployeesForWeekResponsibility(weekResponsibility
									.getId());
					int assignedEmployeesNumber = assignedEmployes.size();
					int unassignedEmployeeNumber = weekResponsibility
							.getNoOfEmployees() - assignedEmployeesNumber;
					weekResponsibility
							.setUnassignedEmployeeNumber(unassignedEmployeeNumber);
					weekResponsibility
							.setAssignedEmployeesNumber(assignedEmployeesNumber);
					weekResponsibility
							.setEmployees(weekResponsibilityEmployeeService
									.getEmployeesForWeekResponsibility(weekResponsibility
											.getId()));
					totalNumber = totalNumber
							+ weekResponsibility.getNoOfEmployees();
					totalAssignedEmployeesNumber = totalAssignedEmployeesNumber
							+ assignedEmployeesNumber;
					totalUnassignedEmployeeNumber = totalUnassignedEmployeeNumber
							+ unassignedEmployeeNumber;
				}
				workWeek.setTotalAssignedEmployeesNumber(totalAssignedEmployeesNumber);
				workWeek.setTotalUnassignedEmployeeNumber(totalUnassignedEmployeeNumber);
				workWeek.setWeekResponsibilities(workResponsibilities);
				workWeek.setTotalNumberOfEmployees(totalNumber);
				total.add(Integer.toString(totalNumber));
				assigned.add(Integer.toString(totalAssignedEmployeesNumber));
				unassigned.add(Integer.toString(totalUnassignedEmployeeNumber));
				totalOverview.setName(project.getName());
				totalOverview.setValues(total);
				assignedOverview.setName(project.getName());
				assignedOverview.setValues(assigned);
				unassignedOverview.setName(project.getName());
				unassignedOverview.setValues(unassigned);
			}
			totalOverviewTO.add(totalOverview);
			assignedOverviewTO.add(assignedOverview);
			unassignedOverviewTO.add(unassignedOverview);
		}
		for (int j = 1; j <= 12; j++) {
			columns.add(new ColumnModel("Week " + j, j - 1));
		}

	}

	public String overviewView() {
		return "overview.xhtml?faces-redirect=true";
	}

	static public class ColumnModel implements Serializable {

		private static final long serialVersionUID = 536213351815390552L;

		private String header;

		private Integer index;

		public ColumnModel(String header, Integer index) {
			super();
			this.header = header;
			this.index = index;
		}

		public String getHeader() {
			return header;
		}

		public void setHeader(String header) {
			this.header = header;
		}

		public Integer getIndex() {
			return index;
		}

		public void setIndex(Integer index) {
			this.index = index;
		}

	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public WeekResponsibilityEmployeeService getWeekResponsibilityEmployeeService() {
		return weekResponsibilityEmployeeService;
	}

	public void setWeekResponsibilityEmployeeService(
			WeekResponsibilityEmployeeService weekResponsibilityEmployeeService) {
		this.weekResponsibilityEmployeeService = weekResponsibilityEmployeeService;
	}

	public ProjectService getProjectService() {
		return projectService;
	}

	public void setProjectService(ProjectService projectService) {
		this.projectService = projectService;
	}

	public WorkWeekService getWorkWeekService() {
		return workWeekService;
	}

	public void setWorkWeekService(WorkWeekService workWeekService) {
		this.workWeekService = workWeekService;
	}

	public WeekResponsibilityService getWeekResponsibilityService() {
		return weekResponsibilityService;
	}

	public void setWeekResponsibilityService(
			WeekResponsibilityService weekResponsibilityService) {
		this.weekResponsibilityService = weekResponsibilityService;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public List<ProjectOverviewTO> getTotalOverviewTO() {
		return totalOverviewTO;
	}

	public void setTotalOverviewTO(List<ProjectOverviewTO> totalOverviewTO) {
		this.totalOverviewTO = totalOverviewTO;
	}

	public List<ProjectOverviewTO> getAssignedOverviewTO() {
		return assignedOverviewTO;
	}

	public void setAssignedOverviewTO(List<ProjectOverviewTO> assignedOverviewTO) {
		this.assignedOverviewTO = assignedOverviewTO;
	}

	public List<ProjectOverviewTO> getUnassignedOverviewTO() {
		return unassignedOverviewTO;
	}

	public void setUnassignedOverviewTO(
			List<ProjectOverviewTO> unassignedOverviewTO) {
		this.unassignedOverviewTO = unassignedOverviewTO;
	}

	public List<ColumnModel> getColumns() {
		return columns;
	}

	public void setColumns(List<ColumnModel> columns) {
		this.columns = columns;
	}

}
