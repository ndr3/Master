package com.qamanagement.core.data.to;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ProjectOverviewTO implements Serializable {

	private static final long serialVersionUID = -8840186680695123240L;

	private String name;

	private List<String> values = new ArrayList<String>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getValues() {
		return values;
	}

	public void setValues(List<String> values) {
		this.values = values;
	}

}
