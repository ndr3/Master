package com.qamanagement.core.data.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qamanagement.core.data.dao.ProjectDao;
import com.qamanagement.core.data.dao.WorkWeekDao;
import com.qamanagement.core.data.model.Project;
import com.qamanagement.core.data.model.Responsibility;
import com.qamanagement.core.data.model.WeekResponsibility;
import com.qamanagement.core.data.model.WorkWeek;

@Service("projectService")
@Transactional
public class ProjectServiceImpl implements ProjectService, Serializable {

	private static final long serialVersionUID = 2801892702029559306L;
	private static final int noOfWeeks = 12;
	private static final List<String> responsibilities = Arrays.asList("Test",
			"Automation", "Field Support", "Design", "Maintain Plan",
			"Outsource Docs", "Trade Show", "Test Planning", "Outsource");

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
		generateWorkWeeks(project);
	}

	@Override
	public void updateProject(Project project) {
		projectDao.update(project);
	}

	@Override
	public Project getProjectById(Long id) {
		return projectDao.getProjectById(id);
	}

	// initialize each work week
	private void generateWorkWeeks(Project project) {
		for (int i = 1; i <= noOfWeeks; ++i) {
			WorkWeek workWeek = new WorkWeek(i, project);
			generateWeekResponsibilities(workWeek);
		}
	}

	// initialize each week responsibility
	private void generateWeekResponsibilities(WorkWeek workWeek) {
		List<WeekResponsibility> weekResponsibilities = new ArrayList<WeekResponsibility>();
		for (int i = 0; i < responsibilities.size(); ++i) {
			Responsibility responsibility = new Responsibility(
					responsibilities.get(i));
			WeekResponsibility weekResponsibility = new WeekResponsibility(
					workWeek, responsibility);
			weekResponsibilities.add(weekResponsibility);
		}

		workWeek.setWeekResponsibilities(weekResponsibilities);
	}

}
