package com.qamanagement.core.data.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.qamanagement.core.data.model.Employee;
import com.qamanagement.core.data.model.WeekResponsibility;
import com.qamanagement.core.data.model.WeekResponsibilityEmployee;
import com.qamanagement.core.data.to.AssignmentEmployeeTO;

@Repository
public class WeekResponsibilityEmployeeImpl extends AbstractDao implements
		WeekResponsibilityEmployeeDao {

	private static final long serialVersionUID = -876461216439507259L;

	@Override
	public void save(WeekResponsibilityEmployee weekResponsibilityEmployee) {
		super.save(weekResponsibilityEmployee);
	}

	@Override
	public void truncate() {
		Session sess = getSession();
		Query query = sess.createQuery("delete from WeekResponsibilityEmployee");
		query.executeUpdate();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Employee> getEmployeesForWeekResponsibility(long responsibilityId) {
		Criteria criteria = getSession().createCriteria(
				WeekResponsibilityEmployee.class, "wr");
		criteria.createAlias("wr.weekResponsibility", "weekResponsibility");
		criteria.add(Restrictions.eq("weekResponsibility.id", responsibilityId));
		List<WeekResponsibilityEmployee> weekResponsibilityEmployees = criteria.list();
		List<Employee> employees = new ArrayList<Employee>();
		if(weekResponsibilityEmployees!=null){
			for(WeekResponsibilityEmployee weekResponsibilityEmployee:weekResponsibilityEmployees){
				employees.add(weekResponsibilityEmployee.getEmployee());
			}
		}
		return employees;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<AssignmentEmployeeTO> getEmployeeResponsibility(String username) {
		Criteria criteria = getSession().createCriteria(
				WeekResponsibilityEmployee.class, "wr");
		criteria.createAlias("wr.weekResponsibility", "weekResponsibility");
		criteria.createAlias("weekResponsibility.workWeek", "workWeek");
		criteria.createAlias("weekResponsibility.responsibility", "responsibility");
		criteria.createAlias("workWeek.project", "project");
		criteria.createAlias("wr.employee", "employee");
		criteria.createAlias("employee.userAccount", "userAccount");
		criteria.add(Restrictions.eq("userAccount.email", username));
		criteria.setProjection( Projections.projectionList()
		        .add( Projections.property("project.name"), "projectName" )
		        .add( Projections.property("workWeek.number"), "weekNumber" )
		        .add( Projections.property("responsibility.name"), "responsibility" )
		    );
		List<AssignmentEmployeeTO>  weekResponsibility = criteria.setResultTransformer(Transformers.aliasToBean(AssignmentEmployeeTO.class)).list();
		return weekResponsibility;
	}

}
