package com.qamanagement.core.data.model;

import java.io.Serializable;

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
@Table(name = "jobResponsibility")
public class JobResponsibility implements Serializable {
	
	private static final long serialVersionUID = 3939220963539611716L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public JobClassification getJob() {
		return job;
	}

	public void setJob(JobClassification job) {
		this.job = job;
	}

	public Responsibility getResponsibility() {
		return responsibility;
	}

	public void setResponsibility(Responsibility responsibility) {
		this.responsibility = responsibility;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@Fetch(FetchMode.SELECT)	
	private JobClassification job;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@Fetch(FetchMode.SELECT)	
	private Responsibility responsibility;
}
