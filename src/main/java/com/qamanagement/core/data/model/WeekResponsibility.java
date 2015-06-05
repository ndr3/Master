package com.qamanagement.core.data.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "week_responsibility")
public class WeekResponsibility implements Serializable {

	private static final long serialVersionUID = 5825642788644260180L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@Fetch(FetchMode.SELECT)	
	private WorkWeek workWeek;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@Fetch(FetchMode.SELECT)	
	private JobResponsibility jobResponsibility;
	
	public JobResponsibility getJobResponsibility() {
		return jobResponsibility;
	}

	public void setJobResponsibility(JobResponsibility jobResponsibility) {
		this.jobResponsibility = jobResponsibility;
	}

	@Column(name = "no_of_employees")
	private Integer noOfEmployees;
	
	public Integer getNoOfEmployees() {
		return noOfEmployees;
	}

	public void setNoOfEmployees(Integer noOfEmployees) {
		this.noOfEmployees = noOfEmployees;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public WorkWeek getWorkWeek() {
		return workWeek;
	}

	public void setWorkWeek(WorkWeek workWeek) {
		this.workWeek = workWeek;
	}
}
