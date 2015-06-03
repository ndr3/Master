package com.qamanagement.core.data.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qamanagement.core.data.dao.ProjectDao;
import com.qamanagement.core.data.dao.WorkWeekDao;
import com.qamanagement.core.data.model.Project;

@Service("projectService")
@Transactional
public class ProjectServiceImpl implements ProjectService, Serializable {

	private static final long serialVersionUID = 2801892702029559306L;

	@Autowired
	private ProjectDao projectDao;

	@Autowired
	private WorkWeekDao workWeekDao;

	@Override
	public List<Project> getAllUserProjects(String email) {
		return projectDao.getAllUserProjects(email);
	}

	@Override
	public void saveProject(Project project) {
		projectDao.save(project);
	}

	@Override
	public void updateProject(Project project) {
		projectDao.update(project);
	}

	@Override
	public Project getProjectById(Long id) {
		return projectDao.getProjectById(id);
	}

}
