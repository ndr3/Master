package com.qamanagement.core.data.dao;

import java.util.List;

import com.qamanagement.core.data.model.Project;

public interface ProjectDao {

	List<Project> getAllUserProjects(String email);

	void save(Project project);

	Project getProjectById(Long id);
	
	void update(Project project);

}
