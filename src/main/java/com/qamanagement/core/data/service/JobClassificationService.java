package com.qamanagement.core.data.service;

import java.util.List;

import com.qamanagement.core.data.model.JobClassification;

public interface JobClassificationService {

	List<JobClassification> getAllJobClassification();

	JobClassification getJobClassificationByName(String name);

}
