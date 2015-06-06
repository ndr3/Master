package com.qamanagement.core.data.dao;

import org.springframework.stereotype.Repository;

import com.qamanagement.core.data.model.WeekResponsibilityEmployee;

@Repository
public class WeekResponsibilityEmployeeImpl extends AbstractDao implements
		WeekResponsibilityEmployeeDao {

	private static final long serialVersionUID = -876461216439507259L;

	@Override
	public void save(WeekResponsibilityEmployee weekResponsibilityEmployee) {
		super.save(weekResponsibilityEmployee);
	}

}
