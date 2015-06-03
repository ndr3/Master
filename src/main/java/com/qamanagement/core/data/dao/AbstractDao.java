package com.qamanagement.core.data.dao;

import java.io.Serializable;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractDao implements  Serializable{
	
	private static final long serialVersionUID = -3333050874228092277L;
	
	@Autowired
    private SessionFactory sessionFactory;
 
    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }
    
    public void save (Object entity) {
        getSession().save(entity);
    }
 
    public void persist(Object entity) {
        getSession().persist(entity);
    }
    
    public void update(Object entity) {
        getSession().update(entity);
    }
 
    public void delete(Object entity) {
        getSession().delete(entity);
    }

}
