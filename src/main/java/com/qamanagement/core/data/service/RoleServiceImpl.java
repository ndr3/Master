package com.qamanagement.core.data.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qamanagement.core.data.dao.RoleDao;
import com.qamanagement.core.data.model.Role;

@Service
@Transactional
public class RoleServiceImpl implements RoleService{
	
	@Autowired
	private RoleDao roleDao;

	@Override
	public Role getRole(Long id) {
		return roleDao.getRole(id);
	}

}
