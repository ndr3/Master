package com.qamanagement.web.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import com.qamanagement.core.data.service.WeekResponsibilityEmployeeService;
import com.qamanagement.core.data.to.AssignmentEmployeeTO;

@ManagedBean(name = "assignmentView")
@ViewScoped
public class AssignmentView implements Serializable {

	private static final long serialVersionUID = 8147086830752477705L;

	private User user;

	private List<AssignmentEmployeeTO> assignmentEmployeeTOs;

	@ManagedProperty(value = "#{weekResponsibilityEmployeeService}")
	private WeekResponsibilityEmployeeService weekResponsibilityEmployeeService;

	@PostConstruct
	public void init() {
		setCurrentUser();
		setAssignmentEmployeeTOs(weekResponsibilityEmployeeService.getEmployeeResponsibility(user.getUsername()));
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	private void setCurrentUser() {
		user = (User) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
	}

	public WeekResponsibilityEmployeeService getWeekResponsibilityEmployeeService() {
		return weekResponsibilityEmployeeService;
	}

	public void setWeekResponsibilityEmployeeService(
			WeekResponsibilityEmployeeService weekResponsibilityEmployeeService) {
		this.weekResponsibilityEmployeeService = weekResponsibilityEmployeeService;
	}

	public List<AssignmentEmployeeTO> getAssignmentEmployeeTOs() {
		return assignmentEmployeeTOs;
	}

	public void setAssignmentEmployeeTOs(
			List<AssignmentEmployeeTO> assignmentEmployeeTOs) {
		this.assignmentEmployeeTOs = assignmentEmployeeTOs;
	}

}
