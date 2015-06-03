package com.qamanagement.core.data.dao;

import com.qamanagement.common.exception.ApplicationException;
import com.qamanagement.core.data.model.User;

public interface UserDao {
	
	public User getUser(String email);
	
	public void create(User account) throws ApplicationException;

}
