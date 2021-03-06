package com.qamanagement.core.data.dao;

import java.util.List;

import com.qamanagement.core.data.model.WeekResponsibility;

public interface WeekResponsibilityDao {

	List<WeekResponsibility> getAllWorkWeekWeekResp(Long workWeekId);

	void save(WeekResponsibility weekResponsibility);

	void update(WeekResponsibility weekResponsibility);

	List<WeekResponsibility> getAllWeekRespByWeekNumberAndUserEmail(
			int weekNumber, String email, long responsibilityId);

	WeekResponsibility getWeekRespByWeekNumberAndProjectIdAndResponsibilityId(
			int weekNumber, long projectId, long responsibilityId);

}
