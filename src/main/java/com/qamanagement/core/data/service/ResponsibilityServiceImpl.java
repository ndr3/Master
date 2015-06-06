package com.qamanagement.core.data.service;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qamanagement.core.data.dao.ResponsibilityDao;

@Service("responsibilityService")
@Transactional
public class ResponsibilityServiceImpl implements ResponsibilityService, Serializable{

	private static final long serialVersionUID = 2435291590948660634L;
	
	@Autowired
	private ResponsibilityDao responsibilityDao;
	
}
