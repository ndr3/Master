package com.qamanagement.core.data.service;

public interface AuthenticationService {
	
	boolean login(String username, String password);

	void logout();

}
