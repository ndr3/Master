package com.qamanagement.core.data.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.qamanagement.core.data.model.Project;

@Repository
public class ProjectDaoImpl extends AbstractDao implements ProjectDao {

	private static final long serialVersionUID = 7079093932352480869L;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Project> getAllUserProjects(String email) {
		Criteria criteria = getSession().createCriteria(Project.class,"p");
		criteria.createAlias("p.user", "user");
		criteria.add(Restrictions.eq("user.email", email));
		List<Project> projects = criteria.list();
		return projects;
	}
	
	@Override
	public Project getProjectById(Long id) {
		Criteria criteria = getSession().createCriteria(Project.class,"p");
		criteria.add(Restrictions.eq("p.id", id));
		Project project = (Project) criteria.uniqueResult();
		return project;
	}
	
	@Override
	public void save(Project project){
		super.save(project);
	}

	@Override
	public void update(Project project) {
		super.update(project);
	}

}
