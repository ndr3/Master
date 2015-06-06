package com.qamanagement.core.data.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

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
	private Responsibility responsibility;

	@Column(name = "no_of_employees")
	private Integer noOfEmployees = 0;

	@Transient
	private List<Employee> employees;

	public WeekResponsibility() {
		super();
	}

	public WeekResponsibility(WorkWeek workWeek, Responsibility responsibility) {
		this.workWeek = workWeek;
		this.responsibility = responsibility;
	}

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

	public Responsibility getResponsibility() {
		return responsibility;
	}

	public void setResponsibility(Responsibility responsibility) {
		this.responsibility = responsibility;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

}
