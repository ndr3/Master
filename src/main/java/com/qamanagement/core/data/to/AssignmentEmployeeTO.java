package com.qamanagement.core.data.to;

import java.io.Serializable;

public class AssignmentEmployeeTO implements Serializable {

	private static final long serialVersionUID = 5296552101766175739L;

	private Integer weekNumber;

	private String projectName;

	private String responsibility;

	public Integer getWeekNumber() {
		return weekNumber;
	}

	public void setWeekNumber(Integer weekNumber) {
		this.weekNumber = weekNumber;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getResponsibility() {
		return responsibility;
	}

	public void setResponsibility(String responsibility) {
		this.responsibility = responsibility;
	}

}
