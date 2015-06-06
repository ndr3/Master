package com.qamanagement.core.data.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qamanagement.core.data.dao.JobClassificationDao;
import com.qamanagement.core.data.model.JobClassification;

@Service("jobClassificationService")
@Transactional
public class JobClassificationServiceImpl implements JobClassificationService,
		Serializable {

	private static final long serialVersionUID = 2144379223429168685L;

	@Autowired
	private JobClassificationDao jobClassificationDao;

	@SuppressWarnings("unchecked")
	@Override
	public List<JobClassification> getAllJobClassification() {
		return jobClassificationDao.getAllJobClassification();
	}
	
	public JobClassification getJobClassificationByName(String name) {
		return jobClassificationDao.getJobClassificationByName(name);
	}


}
