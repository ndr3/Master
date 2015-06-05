package com.qamanagement.core.data.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qamanagement.core.data.dao.WorkWeekDao;
import com.qamanagement.core.data.model.WorkWeek;

@Service("workWeekService")
@Transactional
public class WorkWeekServiceImpl implements WorkWeekService{
	
	@Autowired
	private WorkWeekDao workWeekDao;

	@Override
	public void save(WorkWeek workWeek) {
		workWeekDao.save(workWeek);
	}

	@Override
	public List<WorkWeek> getAllProjectWorkWeeks(Long projectId) {
		return workWeekDao.getAllProjectWorkWeeks(projectId);
	}

}
