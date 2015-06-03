package com.qamanagement.web.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import com.qamanagement.core.data.model.Project;
import com.qamanagement.core.data.service.ProjectService;
import com.qamanagement.core.data.service.UserService;

@ManagedBean(name = "projectView")
@ViewScoped
public class ProjectView implements Serializable {

	private static final long serialVersionUID = 5323066814321042662L;

	private User user;
	
	private Long projectId;

	private Project project;

	private String projectName;

	private Project selectedProject;

	private List<Project> projects = new ArrayList<Project>();

	@ManagedProperty(value = "#{projectService}")
	private ProjectService projectService;

	@ManagedProperty(value = "#{userService}")
	private UserService userService;

	@PostConstruct
	public void init() {
		setCurrentUser();
		setProjects(projectService.getAllUserProjects(getUser().getUsername()));
	}

	public void save(ActionEvent actionEvent) {
		FacesMessage msg = null;
		Project projNew = new Project();
		projNew.setName(projectName);
		projNew.setUser(userService.getUser(getUser().getUsername()));
		projectService.saveProject(projNew);
		msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
				"The project was saved", "");
		FacesContext.getCurrentInstance().addMessage("createMsg", msg);
		setProjects(projectService.getAllUserProjects(getUser().getUsername()));
		RequestContext requestContext = RequestContext.getCurrentInstance();
		requestContext.execute("PF('project').hide()");
	}
	
	public String projectView() {
		return "project.xhtml?faces-redirect=true";
	}

	public void onRowEdit(RowEditEvent event) {
		Project project = (Project) event.getObject();
		projectService.updateProject(project);
	}

	private void setCurrentUser() {
		user = (User) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
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

	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
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

}
