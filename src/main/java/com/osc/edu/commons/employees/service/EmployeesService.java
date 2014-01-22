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
package com.osc.edu.commons.employees.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.osc.edu.commons.employees.dao.EmployeesDao;
import com.osc.edu.commons.employees.dto.EmployeesDto;

/**
 * <pre>
 * Service Class for Employees
 * </pre>
 * @author Sang-cheon Park
 * @version 1.0
 */
public class EmployeesService {

	@Autowired
	private EmployeesDao employeesDao;
	
	public void setUseMapper(boolean useMapper) {
		employeesDao.setUseMapper(useMapper);
	}

	public List<EmployeesDto> getEmployeesList() {
		return employeesDao.getEmployeesList();
	}

	public EmployeesDto getEmployees(Integer id) {
		return employeesDao.getEmployees(id);
	}

	public void insertEmployees(EmployeesDto employees) {
		employeesDao.insertEmployees(employees);
	}

	public void updateEmployees(EmployeesDto employees) {
		employeesDao.updateEmployees(employees);
	}

	public void deleteEmployees(Integer id) {
		employeesDao.deleteEmployees(id);
	}

	public void insertEmployeesList(List<EmployeesDto> employeesList) {
		int i = employeesList.size();
		
		for (EmployeesDto employees : employeesList) {
			employeesDao.insertEmployees(employees);
			
			if (--i <= 1) {
				throw new RuntimeException("Throw RuntimeException manually for Rollback.");
			}
		}
	}
}
//end of EmployeesService.java