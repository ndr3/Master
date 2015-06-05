package com.qamanagement.core.data.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "job_classification")
public class JobClassification implements Serializable {

	private static final long serialVersionUID = 4437978851520460261L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "name", nullable = false)
	private String name;

	public List<JobResponsibility> getJobResponsibilities() {
		return jobResponsibilities;
	}

	public void setJobResponsibilities(List<JobResponsibility> jobResponsibilities) {
		this.jobResponsibilities = jobResponsibilities;
	}

	@Transient
	private List<JobResponsibility> jobResponsibilities;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "jobClassification")
	@Fetch(FetchMode.SELECT)
	private List<Employee> employees;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

}
