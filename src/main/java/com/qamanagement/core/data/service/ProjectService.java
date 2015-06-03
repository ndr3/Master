package com.qamanagement.core.data.service;

import java.util.List;

import com.qamanagement.core.data.model.Project;

public interface ProjectService {

	List<Project> getAllUserProjects(String email);

	void saveProject(Project project);

	void updateProject(Project project);
	
	Project getProjectById(Long id);

}
