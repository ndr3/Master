package com.qamanagement.core.data.dao;

import org.springframework.stereotype.Repository;

import com.qamanagement.core.data.model.Role;

@Repository
public class RoleDaoImpl extends AbstractDao implements RoleDao{

	private static final long serialVersionUID = 2930858188900235511L;

	@Override
	public Role getRole(Long id) {
		Role role = (Role) getSession().load(Role.class, id);
        return role;
	}

}
