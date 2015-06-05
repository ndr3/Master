package com.qamanagement.core.data.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.qamanagement.core.data.model.WeekResponsibility;

@Repository
public class WeekResponsibilityDaoImpl extends AbstractDao implements
		WeekResponsibilityDao {

	private static final long serialVersionUID = 7658295667562813840L;

	@SuppressWarnings("unchecked")
	@Override
	public List<WeekResponsibility> getAllWorkWeekWeekResp(Long workWeekId) {
		Criteria criteria = getSession().createCriteria(
				WeekResponsibility.class, "ac");
		criteria.createAlias("ac.workWeek", "workWeek");
		criteria.add(Restrictions.eq("workWeek.id", workWeekId));
		List<WeekResponsibility> weekResponsibilities = criteria.list();
		return weekResponsibilities;
	}

	@Override
	public void save(WeekResponsibility weekResponsibility) {
		super.save(weekResponsibility);
	}

	@Override
	public void update(WeekResponsibility weekResponsibility) {
		super.update(weekResponsibility);
	}

}
