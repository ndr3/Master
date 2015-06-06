package com.qamanagement.core.data.dao;

import java.util.List;

import com.qamanagement.core.data.model.JobClassification;

public interface JobClassificationDao {

	List<JobClassification> getAllJobClassification();

	JobClassification getJobClassificationByName(String name);

}
