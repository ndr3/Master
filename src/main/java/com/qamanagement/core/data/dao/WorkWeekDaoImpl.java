package com.qamanagement.core.data.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.qamanagement.core.data.model.WorkWeek;

@Repository
public class WorkWeekDaoImpl extends AbstractDao implements WorkWeekDao {

	private static final long serialVersionUID = 6693414709503525267L;

	@SuppressWarnings("unchecked")
	@Override
	public List<WorkWeek> getAllProjectWorkWeeks(Long projectId) {
		Criteria criteria = getSession().createCriteria(WorkWeek.class, "ac");
		criteria.createAlias("ac.project", "project");
		criteria.add(Restrictions.eq("project.id", projectId));
		criteria.addOrder(Order.asc("ac.number"));
		List<WorkWeek> workWeeks = criteria.list();
		return workWeeks;
	}

	@Override
	public void save(WorkWeek workWeek) {
		super.save(workWeek);
	}

}
