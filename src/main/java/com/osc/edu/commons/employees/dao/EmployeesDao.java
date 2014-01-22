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
package com.osc.edu.commons.employees.dao;

import java.util.List;

import com.osc.edu.commons.employees.dto.EmployeesDto;

/**
 * <pre>
 * Data Access Object Interface for Employees table
 * </pre>
 * @author Sang-cheon Park
 * @version 1.0
 */
public interface EmployeesDao {

	public void setUseMapper(boolean useMapper);
	public List<EmployeesDto> getEmployeesList();
	public EmployeesDto getEmployees(Integer id);
	public void insertEmployees(EmployeesDto customers);
	public void updateEmployees(EmployeesDto customers);
	public void deleteEmployees(Integer id);
}
//end of EmployeesDao.java