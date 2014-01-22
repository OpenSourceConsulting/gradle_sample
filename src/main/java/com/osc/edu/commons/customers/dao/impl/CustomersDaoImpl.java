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
package com.osc.edu.commons.customers.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.osc.edu.commons.customers.dao.CustomersDao;
import com.osc.edu.commons.customers.dto.CustomersDto;
import com.osc.edu.commons.customers.mapper.CustomersMapper;

/**
 * <pre>
 * Implementation Class of CustomersDao interface
 * </pre>
 * @author Sang-cheon Park
 * @version 1.0
 */
public class CustomersDaoImpl implements CustomersDao {
	
    private static final Logger logger = LoggerFactory.getLogger(CustomersDaoImpl.class);
	
	@Autowired
	private CustomersMapper mapper;
	
	@Autowired
	private SqlSession sqlSession;
	
	private boolean useMapper;
	
	public CustomersDaoImpl() {
		this(true);
	}
	
	public CustomersDaoImpl(boolean useMapper) {
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
	 * @see com.osc.edu.commons.customers.dao.CustomersDao#getCustomersList()
	 */
	@Override
	public List<CustomersDto> getCustomersList() {
		if (useMapper) {
			logger.debug("Start query using mapper.");
			return mapper.getCustomersList();
		} else {
			logger.debug("Start query using sqlSession.");
			return sqlSession.selectList("com.osc.edu.commons.customers.mapper.CustomersMapper.getCustomersList");
		}
	}

	/* (non-Javadoc)
	 * @see com.osc.edu.commons.customers.dao.CustomersDao#getCustomers(java.lang.Integer)
	 */
	@Override
	public CustomersDto getCustomers(Integer id) {
		if (useMapper) {
			logger.debug("Start query using mapper.");
			return mapper.getCustomers(id);
		} else {
			logger.debug("Start query using sqlSession.");
			return sqlSession.selectOne("com.osc.edu.commons.customers.mapper.CustomersMapper.getCustomers", id);
		}
	}

	/* (non-Javadoc)
	 * @see com.osc.edu.commons.customers.dao.CustomersDao#insertCustomers(com.osc.edu.commons.customers.dto.CustomersDto)
	 */
	@Override
	public void insertCustomers(CustomersDto customers) {
		if (useMapper) {
			logger.debug("Start query using mapper.");
			mapper.insertCustomers(customers);
		} else {
			logger.debug("Start query using sqlSession.");
			sqlSession.insert("com.osc.edu.commons.customers.mapper.CustomersMapper.insertCustomers", customers);
		}
	}

	/* (non-Javadoc)
	 * @see com.osc.edu.commons.customers.dao.CustomersDao#updateCustomers(com.osc.edu.commons.customers.dto.CustomersDto)
	 */
	@Override
	public void updateCustomers(CustomersDto customers) {
		if (useMapper) {
			logger.debug("Start query using mapper.");
			mapper.updateCustomers(customers);
		} else {
			logger.debug("Start query using sqlSession.");
			sqlSession.update("com.osc.edu.commons.customers.mapper.CustomersMapper.updateCustomers", customers);
		}
	}

	/* (non-Javadoc)
	 * @see com.osc.edu.commons.customers.dao.CustomersDao#deleteCustomers(java.lang.Integer)
	 */
	@Override
	public void deleteCustomers(Integer id) {
		if (useMapper) {
			logger.debug("Start query using mapper.");
			mapper.deleteCustomers(id);
		} else {
			logger.debug("Start query using sqlSession.");
			sqlSession.delete("com.osc.edu.commons.customers.mapper.CustomersMapper.deleteCustomers", id);
		}
	}

}
//end of CustomersDaoImpl.java