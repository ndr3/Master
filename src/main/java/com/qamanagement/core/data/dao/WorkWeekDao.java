package com.qamanagement.core.data.dao;

import java.util.List;

import com.qamanagement.core.data.model.WorkWeek;

public interface WorkWeekDao {

	void save(WorkWeek workWeek);

	List<WorkWeek> getAllProjectWorkWeeks(Long projectId);

}
