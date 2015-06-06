package com.qamanagement.core.data.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qamanagement.core.data.dao.ProjectDao;
import com.qamanagement.core.data.dao.ResponsibilityDao;
import com.qamanagement.core.data.dao.WeekResponsibilityDao;
import com.qamanagement.core.data.dao.WorkWeekDao;
import com.qamanagement.core.data.model.Project;
import com.qamanagement.core.data.model.Responsibility;
import com.qamanagement.core.data.model.WeekResponsibility;
import com.qamanagement.core.data.model.WorkWeek;

@Service("projectService")
@Transactional
public class ProjectServiceImpl implements ProjectService, Serializable {

	private static final long serialVersionUID = 2801892702029559306L;
	private static final int NO_OF_WEEKS = 12;

	@Autowired
	private ProjectDao projectDao;

	@Autowired
	private WorkWeekDao workWeekDao;

	@Autowired
	private WeekResponsibilityDao weekResponsibilityDao;

	@Autowired
	private ResponsibilityDao responsibilityDao;

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
		for (int i = 1; i <= NO_OF_WEEKS; ++i) {
			WorkWeek workWeek = new WorkWeek(i, project);
			workWeekDao.save(workWeek);
			generateWeekResponsibilities(workWeek);
		}
	}

	// initialize each week responsibility
	private void generateWeekResponsibilities(WorkWeek workWeek) {
		List<Responsibility> responsibilities = responsibilityDao
				.getAllResponsibilities();
		for (Responsibility responsibility : responsibilities) {
			WeekResponsibility weekResponsibility = new WeekResponsibility(
					workWeek, responsibility);
			weekResponsibilityDao.save(weekResponsibility);
		}

	}

}
