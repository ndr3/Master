package com.qamanagement.core.data.dao;

import java.util.List;

import com.qamanagement.core.data.model.Responsibility;

public interface ResponsibilityDao {

	List<Responsibility> getAllResponsibilities();

	List<Responsibility> getAllResponsibilitiesByJobClassification(Long jobId);

}
