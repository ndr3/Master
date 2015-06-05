package com.qamanagement.core.data.service;

import java.util.List;

import com.qamanagement.core.data.model.WeekResponsibility;

public interface WeekResponsibilityService {
	
	List<WeekResponsibility> getAllWorkWeekWeekResp(Long workWeekId);

	void save(WeekResponsibility weekResponsibility);

	void update(WeekResponsibility weekResponsibility);

}
