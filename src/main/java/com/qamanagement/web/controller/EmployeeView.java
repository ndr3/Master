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

import com.qamanagement.core.data.model.Employee;
import com.qamanagement.core.data.service.EmployeeService;
import com.qamanagement.core.data.service.UserService;

@ManagedBean(name = "employeeView")
@ViewScoped
public class EmployeeView implements Serializable {

	private static final long serialVersionUID = 8807049694877619792L;

	private User user;

	private String employeeName;

	private List<Employee> employees = new ArrayList<Employee>();

	@ManagedProperty(value = "#{employeeService}")
	private EmployeeService employeeService;

	@ManagedProperty(value = "#{userService}")
	private UserService userService;

	@PostConstruct
	public void init() {
		setCurrentUser();
		setEmployees(employeeService
				.getAllUserEmployees(getUser().getUsername()));
	}

	public void save(ActionEvent actionEvent) {
		FacesMessage msg = null;
		Employee employeeNew = new Employee();
		employeeNew.setName(employeeName);
		employeeNew.setUser(userService.getUser(getUser().getUsername()));
		employeeService.save(employeeNew);
		msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
				"The employee was saved", "");
		FacesContext.getCurrentInstance().addMessage("createMsg", msg);
		setEmployees(employeeService.getAllUserEmployees(getUser().getUsername()));
		RequestContext requestContext = RequestContext.getCurrentInstance();
		requestContext.execute("PF('employee').hide()");
	}

	public String employeeView() {
		return "employee.xhtml?faces-redirect=true";
	}

	public void onRowEdit(RowEditEvent event) {
		Employee employee = (Employee) event.getObject();
		employeeService.update(employee);;
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

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	public EmployeeService getEmployeeService() {
		return employeeService;
	}

	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}
