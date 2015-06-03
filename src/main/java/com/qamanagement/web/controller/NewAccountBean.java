package com.qamanagement.web.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.context.RequestContext;

import com.qamanagement.common.exception.ApplicationException;
import com.qamanagement.core.data.model.User;
import com.qamanagement.core.data.service.AuthenticationService;
import com.qamanagement.core.data.service.CustomUserDetailsService;
import com.qamanagement.core.data.service.UserService;

@ManagedBean(name="newAccount")
@RequestScoped
public class NewAccountBean implements Serializable {

	private static final long serialVersionUID = 7306761168760283061L;

	private String email;

	private String password;

	private String lastName;

	private String firstName;

	private String company;

	@ManagedProperty(value = "#{userService}")
	private UserService userManager;

	@ManagedProperty(value = "#{customUserDetailsService}")
	private CustomUserDetailsService customUserDetailsService;

	@ManagedProperty(value = "#{authenticationService}")
	private AuthenticationService authenticationService;

	public void save(ActionEvent actionEvent) {
		RequestContext context = RequestContext.getCurrentInstance();
		FacesMessage msg = null;
		boolean accountCreated = false;
		User newUser = new User();
		newUser.setFirstName(firstName);
		newUser.setLastName(lastName);
		newUser.setPassword(password);
		newUser.setEmail(email);
		newUser.setDateCreated(new Date());
		newUser.setCompany(company);
		try {
			userManager.createUser(newUser);
		} catch (ApplicationException e) {
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Duplicate Email", "");
			FacesContext.getCurrentInstance().addMessage("createAccountErr",
					msg);
			context.addCallbackParam("created", accountCreated);
			return;
		}
		accountCreated = true;
		context.addCallbackParam("created", accountCreated);
		customUserDetailsService.loadUserByUsername(newUser.getEmail());

		authenticationService.login(email, password);
		try {
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect("faces/pages/secure/dashboard.xhtml?faces-redirect=true");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public AuthenticationService getAuthenticationService() {
		return authenticationService;
	}

	public void setAuthenticationService(
			AuthenticationService authenticationService) {
		this.authenticationService = authenticationService;
	}

	public UserService getUserManager() {
		return userManager;
	}

	public void setUserManager(UserService userManager) {
		this.userManager = userManager;
	}

	public CustomUserDetailsService getCustomUserDetailsService() {
		return customUserDetailsService;
	}

	public void setCustomUserDetailsService(
			CustomUserDetailsService customUserDetailsService) {
		this.customUserDetailsService = customUserDetailsService;
	}
	
	


}
