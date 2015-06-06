package com.qamanagement.web.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.primefaces.event.RowEditEvent;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import com.qamanagement.core.data.model.Project;
import com.qamanagement.core.data.model.WeekResponsibility;
import com.qamanagement.core.data.model.WorkWeek;
import com.qamanagement.core.data.service.ProjectService;
import com.qamanagement.core.data.service.ResourceAllocationService;
import com.qamanagement.core.data.service.UserService;
import com.qamanagement.core.data.service.WeekResponsibilityService;
import com.qamanagement.core.data.service.WorkWeekService;

@ManagedBean(name = "projectView")
@ViewScoped
public class ProjectView implements Serializable {

	private static final long serialVersionUID = 5323066814321042662L;

	private User user;

	private Long projectId;

	private Project project;

	private String projectName;

	private Project selectedProject;

	private List<WorkWeek> workWeeks = new ArrayList<WorkWeek>();

	@ManagedProperty(value = "#{projectService}")
	private ProjectService projectService;

	@ManagedProperty(value = "#{workWeekService}")
	private WorkWeekService workWeekService;

	@ManagedProperty(value = "#{weekResponsibilityService}")
	private WeekResponsibilityService weekResponsibilityService;

	@ManagedProperty(value = "#{resourceAllocation}")
	private ResourceAllocationService resourceAllocationService;

	@ManagedProperty(value = "#{userService}")
	private UserService userService;

	public void loadData() {
		setProject(projectService.getProjectById(projectId));
		List<WorkWeek> workWeeks = workWeekService
				.getAllProjectWorkWeeks(projectId);
		for (WorkWeek workWeek : workWeeks) {
			int totalNumber = 0;
			List<WeekResponsibility> workResponsibilities = weekResponsibilityService
					.getAllWorkWeekWeekResp(workWeek.getId());
			workWeek.setWeekResponsibilities(workResponsibilities);
			for (WeekResponsibility weekResponsibility : workResponsibilities) {
				totalNumber = totalNumber
						+ weekResponsibility.getNoOfEmployees();
			}
			workWeek.setTotalNumberOfEmployees(totalNumber);
		}
		setWorkWeeks(workWeeks);
		user = (User) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();

	}

	public void save(ActionEvent actionEvent) {
	}

	public String projectView() {
		return "project.xhtml?faces-redirect=true";
	}

	public void onRowEdit(RowEditEvent event) {
		WeekResponsibility weekResponsibility = (WeekResponsibility) event
				.getObject();
		weekResponsibilityService.update(weekResponsibility);
		loadData();
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public Project getSelectedProject() {
		return selectedProject;
	}

	public void setSelectedProject(Project selectedProject) {
		this.selectedProject = selectedProject;
	}

	public ProjectService getProjectService() {
		return projectService;
	}

	public void setProjectService(ProjectService projectService) {
		this.projectService = projectService;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public List<WorkWeek> getWorkWeeks() {
		return workWeeks;
	}

	public void setWorkWeeks(List<WorkWeek> workWeeks) {
		this.workWeeks = workWeeks;
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

	public ResourceAllocationService getResourceAllocationService() {
		return resourceAllocationService;
	}

	public void setResourceAllocationService(
			ResourceAllocationService resourceAllocationService) {
		this.resourceAllocationService = resourceAllocationService;
	}

}
