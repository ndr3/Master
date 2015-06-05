package com.qamanagement.core.data.service;

import java.util.List;

import com.qamanagement.core.data.model.WorkWeek;

public interface WorkWeekService {

	void save(WorkWeek workWeek);

	List<WorkWeek> getAllProjectWorkWeeks(Long projectId);

}
