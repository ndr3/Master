package com.qamanagement.core.data.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.qamanagement.core.data.model.JobClassification;

@Repository
public class JobClassificationDaoImp extends AbstractDao implements JobClassificationDao{

	private static final long serialVersionUID = 1698660629964515034L;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<JobClassification> getAllJobClassification() {
		Criteria criteria = getSession().createCriteria(JobClassification.class);
		List<JobClassification> jobClassifications = criteria.list();
		return jobClassifications;
	} 
	
	@SuppressWarnings("unchecked")
	@Override
	public JobClassification getJobClassificationByName(String name) {
		Criteria criteria = getSession().createCriteria(JobClassification.class);
		criteria.add(Restrictions.eq("name", name));
		JobClassification jobClassifications = (JobClassification) criteria.uniqueResult();
		return jobClassifications;
	} 

}
