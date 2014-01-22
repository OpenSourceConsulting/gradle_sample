/* 
 * Copyright (C) 2012-2014 Open Source Consulting, Inc. All rights reserved by Open Source Consulting, Inc.
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 *
 * Revision History
 * Author			Date				Description
 * ---------------	----------------	------------
 * Sang-cheon Park	2014. 1. 7.		First Draft.
 */
package com.osc.edu.commons.employees.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.osc.edu.commons.employees.dao.EmployeesDao;
import com.osc.edu.commons.employees.dto.EmployeesDto;
import com.osc.edu.commons.employees.mapper.EmployeesMapper;

/**
 * <pre>
 * Implementation Class of EmployeesDao interface
 * </pre>
 * @author Sang-cheon Park
 * @version 1.0
 */
public class EmployeesDaoImpl implements EmployeesDao {
	
    private static final Logger logger = LoggerFactory.getLogger(EmployeesDaoImpl.class);
	
	@Autowired
	private EmployeesMapper mapper;
	
	@Autowired
	private SqlSession sqlSession;
	
	private boolean useMapper;
	
	public EmployeesDaoImpl() {
		this(true);
	}
	
	public EmployeesDaoImpl(boolean useMapper) {
		this.useMapper = useMapper;
	}

	/* (non-Javadoc)
	 * @see com.osc.edu.commons.customers.dao.CustomersDao#setUseMapper(boolean)
	 */
	@Override
	public void setUseMapper(boolean useMapper) {
		this.useMapper = useMapper;
	}

	/* (non-Javadoc)
	 * @see com.osc.edu.commons.employees.dao.EmployeesDao#getEmployeesList()
	 */
	@Override
	public List<EmployeesDto> getEmployeesList() {
		if (useMapper) {
			logger.debug("Start query using mapper.");
			return mapper.getEmployeesList();
		} else {
			logger.debug("Start query using sqlSession.");
			return sqlSession.selectList("com.osc.edu.commons.employees.mapper.EmployeesMapper.getEmployeesList");
		}
	}

	/* (non-Javadoc)
	 * @see com.osc.edu.commons.employees.dao.EmployeesDao#getEmployees(java.lang.Integer)
	 */
	@Override
	public EmployeesDto getEmployees(Integer id) {
		if (useMapper) {
			logger.debug("Start query using mapper.");
			return mapper.getEmployees(id);
		} else {
			logger.debug("Start query using sqlSession.");
			return sqlSession.selectOne("com.osc.edu.commons.employees.mapper.EmployeesMapper.getEmployees", id);
		}
	}

	/* (non-Javadoc)
	 * @see com.osc.edu.commons.employees.dao.EmployeesDao#insertEmployees(com.osc.edu.commons.employees.dto.EmployeesDto)
	 */
	@Override
	public void insertEmployees(EmployeesDto employees) {
		if (useMapper) {
			logger.debug("Start query using mapper.");
			mapper.insertEmployees(employees);
		} else {
			logger.debug("Start query using sqlSession.");
			sqlSession.insert("com.osc.edu.commons.employees.mapper.EmployeesMapper.insertEmployees", employees);
		}
	}

	/* (non-Javadoc)
	 * @see com.osc.edu.commons.employees.dao.EmployeesDao#updateEmployees(com.osc.edu.commons.employees.dto.EmployeesDto)
	 */
	@Override
	public void updateEmployees(EmployeesDto employees) {
		if (useMapper) {
			logger.debug("Start query using mapper.");
			mapper.updateEmployees(employees);
		} else {
			logger.debug("Start query using sqlSession.");
			sqlSession.update("com.osc.edu.commons.employees.mapper.EmployeesMapper.updateEmployees", employees);
		}
	}

	/* (non-Javadoc)
	 * @see com.osc.edu.commons.employees.dao.EmployeesDao#deleteEmployees(java.lang.Integer)
	 */
	@Override
	public void deleteEmployees(Integer id) {
		if (useMapper) {
			logger.debug("Start query using mapper.");
			mapper.deleteEmployees(id);
		} else {
			logger.debug("Start query using sqlSession.");
			sqlSession.delete("com.osc.edu.commons.employees.mapper.EmployeesMapper.deleteEmployees", id);
		}
	}

}
//end of EmployeesDaoImpl.java