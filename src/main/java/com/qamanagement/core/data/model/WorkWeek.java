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
@Table(name = "work_week")
public class WorkWeek implements Serializable {

	private static final long serialVersionUID = -5482505848838144531L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "number")
	private int number;

	@Column(name = "total_number_of_resp")

	@ManyToOne(fetch = FetchType.EAGER)
	@Fetch(FetchMode.SELECT)
	private Project project;

	@Transient
	private List<WeekResponsibility> weekResponsibilities;

	public WorkWeek(int number, Project project) {
		this.number = number;
		this.project = project;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getTotalNumberOfResp() {
		return weekResponsibilities.size();
	}
	
	public int getTotalNumberOfEmployees() {
		int total = 0;
		for (WeekResponsibility weekResp : weekResponsibilities) {
			total += weekResp.getNoOfEmployees(); 
		}
		
		return total;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public List<WeekResponsibility> getWeekResponsibilities() {
		return weekResponsibilities;
	}

	public void setWeekResponsibilities(
			List<WeekResponsibility> weekResponsibilities) {
		this.weekResponsibilities = weekResponsibilities;
	}
}
