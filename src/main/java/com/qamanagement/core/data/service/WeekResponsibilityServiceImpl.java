package com.qamanagement.core.data.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qamanagement.core.data.dao.WeekResponsibilityDao;
import com.qamanagement.core.data.model.WeekResponsibility;

@Service("weekResponsibilityService")
@Transactional
public class WeekResponsibilityServiceImpl implements WeekResponsibilityService{
	
	@Autowired
	private WeekResponsibilityDao weekResponsibilityDao;

	@Override
	public List<WeekResponsibility> getAllWorkWeekWeekResp(Long workWeekId) {
		return weekResponsibilityDao.getAllWorkWeekWeekResp(workWeekId);
	}

	@Override
	public void save(WeekResponsibility weekResponsibility) {
		weekResponsibilityDao.save(weekResponsibility);
	}

	@Override
	public void update(WeekResponsibility weekResponsibility) {
		weekResponsibilityDao.update(weekResponsibility);
	}

}
