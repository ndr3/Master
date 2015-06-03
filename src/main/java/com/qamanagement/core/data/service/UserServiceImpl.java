package com.qamanagement.core.data.service;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qamanagement.common.exception.ApplicationException;
import com.qamanagement.core.data.dao.UserDao;
import com.qamanagement.core.data.model.Role;
import com.qamanagement.core.data.model.User;

@Transactional
@Service("userService")
public class UserServiceImpl implements UserService, Serializable {

	private static final long serialVersionUID = 6636867365253658140L;
	
	@Autowired
	private UserDao userDao;

	@Override
	public User getUser(String email) {
		return userDao.getUser(email);
	}

	@Override
	public void createUser(User user) throws ApplicationException {
		Role role = new Role();
		role.setId(1L);
		user.setRole(role);
		userDao.create(user);
	}

}
