package com.qamanagement.web.controller;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

@ManagedBean(name = "loginMgmtBean")
@RequestScoped
public class LoginBean {

	private String userName = null;
	private String password = null;
	private User user;

	@ManagedProperty(value = "#{authenticationManager}")
	private AuthenticationManager authenticationManager = null;

	public String login() {
		try {
			Authentication request = new UsernamePasswordAuthenticationToken(
					this.getUserName(), this.getPassword());
			Authentication result = authenticationManager.authenticate(request);
			SecurityContextHolder.getContext().setAuthentication(result);
		} catch (AuthenticationException e) {
			FacesContext.getCurrentInstance().addMessage(
					"msgLogin",
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Username or password incorrect.",
							"Username or password incorrect."));
			return "login.xhtml";
		}
		setCurrentUser();
		if(user.getAuthorities().contains(new GrantedAuthorityImpl("ROLE_PROJECT_MANAGER"))){
			return "secure/dashboard.xhtml?faces-redirect=true";
		} else {
			return "employee/assignments.xhtml?faces-redirect=true";
		}
	}

	public String home() {
		return "dashboard.xhtml?faces-redirect=true";
	}

	public String cancel() {
		return null;
	}

	public String logout() {
		SecurityContextHolder.clearContext();
		return "/pages/login.xhtml?faces-redirect=true";
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

	public AuthenticationManager getAuthenticationManager() {
		return authenticationManager;
	}

	public void setAuthenticationManager(
			AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
