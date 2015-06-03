package com.qamanagement.core.data.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.stereotype.Repository;

import com.qamanagement.common.exception.ApplicationException;
import com.qamanagement.core.data.model.User;

@Repository
public class UserDaoImpl extends AbstractDao implements UserDao {

	private static final long serialVersionUID = 3166022397427805309L;

	@Override
	public User getUser(String email) {
		Criteria criteria = getSession().createCriteria(User.class);
		criteria.add(Restrictions.eq("email", email));
		User user = (User) criteria.uniqueResult();
		return user;
	}

	public void create(User account) throws ApplicationException {
		try {
			save(account);
		} catch (ConstraintViolationException e) {
			throw new ApplicationException();
		}
	}

}
