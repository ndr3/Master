package com.qamanagement.core.data.service;

import com.qamanagement.common.exception.ApplicationException;
import com.qamanagement.core.data.model.User;

public interface UserService {
	
	public User getUser(String email);
	
	public void createUser(User user) throws ApplicationException;

}
