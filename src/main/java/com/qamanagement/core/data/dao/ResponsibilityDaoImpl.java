package com.qamanagement.core.data.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.qamanagement.core.data.model.JobResponsibility;
import com.qamanagement.core.data.model.Responsibility;

@Repository
public class ResponsibilityDaoImpl extends AbstractDao implements
		ResponsibilityDao {

	private static final long serialVersionUID = -7965751245410184494L;

	@SuppressWarnings("unchecked")
	@Override
	public List<Responsibility> getAllResponsibilities() {
		Criteria criteria = getSession().createCriteria(Responsibility.class);
		List<Responsibility> responsibilities = criteria.list();
		return responsibilities;
	}

	@SuppressWarnings("unchecked")
	@Override	
	public List<Responsibility> getAllResponsibilitiesByJobClassification(
			Long jobId) {
		Criteria criteria = getSession().createCriteria(JobResponsibility.class, "jr");
		criteria.createAlias("jr.job", "job");
		criteria.add(Restrictions.eq("job.id", jobId));
		List<JobResponsibility> jobResponsibilities = criteria.list();
		List<Responsibility> responsibilities = new ArrayList<Responsibility>();
		if(jobResponsibilities!=null){
			for(JobResponsibility jobResponsibility: jobResponsibilities){
				responsibilities.add(jobResponsibility.getResponsibility());
			}
		}
		return responsibilities;
	}

}
