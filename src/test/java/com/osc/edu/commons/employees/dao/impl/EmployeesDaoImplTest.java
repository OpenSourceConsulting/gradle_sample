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
 * Sang-cheon Park	2014. 1. 13.		First Draft.
 */
package com.osc.edu.commons.employees.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.osc.edu.commons.employees.dao.EmployeesDao;
import com.osc.edu.commons.employees.dto.EmployeesDto;

/**
 * <pre>
 * 
 * </pre>
 * @author Sang-cheon Park
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:./src/main/resources/spring/dispatcher-servlet.xml", "file:./src/main/resources/spring/context-common.xml" })
public class EmployeesDaoImplTest {

	@Autowired
	private EmployeesDao employeesDao;

	@Test
	public void testGetEmployeesList() {
		// 초기화
		List<EmployeesDto> employeesList = null;
		
		// 테스트
		try {
			// Mapper Interface 사용
			employeesDao.setUseMapper(true);
			employeesList = employeesDao.getEmployeesList();
			
			// 검증
			assertNotNull("employeesList must not be null.", employeesList);
			assertEquals("employeesList's size must be 23.", 23, employeesList.size());
			
			// SqlSession 사용
			employeesDao.setUseMapper(false);
			employeesList = employeesDao.getEmployeesList();
			
			// 검증
			assertNotNull("employeesList must not be null.", employeesList);
			assertEquals("employeesList's size must be 23.", 23, employeesList.size());			
		} catch (Exception e) {
			e.printStackTrace();
			fail("Exception has occurred.");
		}
	}
	
	@Test
	public void testGetEmployees() {
		// 초기화
		EmployeesDto employees = null;
		
		// 테스트
		try {
			// Mapper Interface 사용
			employeesDao.setUseMapper(true);
			employees = employeesDao.getEmployees(1002);
			
			// 검증
			assertNotNull("employees must not be null.", employees);
			assertEquals("employeeId must be 1002.", new Integer(1002), employees.getEmployeeId());
			
			// SqlSession 사용
			employeesDao.setUseMapper(false);
			employees = employeesDao.getEmployees(1002);
			
			// 검증
			assertNotNull("employees must not be null.", employees);
			assertEquals("employeeId must be 1002.", new Integer(1002), employees.getEmployeeId());		
		} catch (Exception e) {
			e.printStackTrace();
			fail("Exception has occurred.");
		}
	}
	
	@Test
	@Transactional
	@Rollback(value=true)
	public void testInsertEmployees() {
		// 초기화
		EmployeesDto employees = new EmployeesDto();
    	employees.setLastname("Lastname");
    	employees.setFirstname("Firstname");
    	employees.setExtension("Extension");
    	employees.setEmail("Email");
    	employees.setOfficeCode("OfficeCode");
    	employees.setReportsTo(100);
    	employees.setJobTitle("JobTitle");

		try {
			assertNull("employeeId must be null.", employees.getEmployeeId());

			// 테스트
			employeesDao.insertEmployees(employees);
			
			// 검증
			assertNotNull("employeeId must not be null.", employees.getEmployeeId());
			
			employees = employeesDao.getEmployees(employees.getEmployeeId());
			assertEquals("Lastname must be equals Lastname", "Lastname", employees.getLastname());
			assertEquals("Firstname must be equals Firstname", "Firstname", employees.getFirstname());
			assertEquals("Extension must be equals Extension", "Extension", employees.getExtension());
			assertEquals("Email must be equals Email", "Email", employees.getEmail());
			assertEquals("OfficeCode must be equals OfficeCode", "OfficeCode", employees.getOfficeCode());
			assertEquals("ReportsTo must be equals 100", new Integer(100), employees.getReportsTo());
			assertEquals("JobTitle must be equals JobTitle", "JobTitle", employees.getJobTitle());
		} catch (Exception e) {
			e.printStackTrace();
			fail("Exception has occurred.");
		}
	}
	
	@Test
	@Transactional
	@Rollback(value=true)
	public void testUpdateEmployees() {
		// 초기화
		EmployeesDto employees = employeesDao.getEmployees(1002);

		try {			
			assertNotEquals("Lastname must not be equals Lastname", "Lastname", employees.getLastname());
			assertNotEquals("Firstname must not be equals Firstname", "Firstname", employees.getFirstname());
			assertNotEquals("Extension must not be equals Extension", "Extension", employees.getExtension());
			assertNotEquals("Email must not be equals Email", "Email", employees.getEmail());
			assertNotEquals("OfficeCode must not be equals OfficeCode", "OfficeCode", employees.getOfficeCode());
			assertNotEquals("ReportsTo must not be equals 100", new Integer(100), employees.getReportsTo());
			assertNotEquals("JobTitle must not be equals JobTitle", "JobTitle", employees.getJobTitle());

	    	employees.setLastname("Lastname");
	    	employees.setFirstname("Firstname");
	    	employees.setExtension("Extension");
	    	employees.setEmail("Email");
	    	employees.setOfficeCode("OfficeCode");
	    	employees.setReportsTo(100);
	    	employees.setJobTitle("JobTitle");

			// 테스트
			employeesDao.updateEmployees(employees);
			
			// 검증
			employees = employeesDao.getEmployees(employees.getEmployeeId());
			assertEquals("Lastname must be equals Lastname", "Lastname", employees.getLastname());
			assertEquals("Firstname must be equals Firstname", "Firstname", employees.getFirstname());
			assertEquals("Extension must be equals Extension", "Extension", employees.getExtension());
			assertEquals("Email must be equals Email", "Email", employees.getEmail());
			assertEquals("OfficeCode must be equals OfficeCode", "OfficeCode", employees.getOfficeCode());
			assertEquals("ReportsTo must be equals 100", new Integer(100), employees.getReportsTo());
			assertEquals("JobTitle must be equals JobTitle", "JobTitle", employees.getJobTitle());
		} catch (Exception e) {
			e.printStackTrace();
			fail("Exception has occurred.");
		}
	}
	
	@Test
	@Transactional
	@Rollback(value=true)
	public void testDeleteEmployees() {
		// 초기화
		List<EmployeesDto> employeesList = employeesDao.getEmployeesList();
		
		try {
			assertEquals("employeesList's size must be 23.", 23, employeesList.size());
			
			// 테스트
			employeesDao.deleteEmployees(1002);
			
			// 검증
			employeesList = employeesDao.getEmployeesList();
			assertEquals("employeesList's size must be 22.", 22, employeesList.size());
		} catch (Exception e) {
			e.printStackTrace();
			fail("Exception has occurred.");
		}
	}
}
//end of EmployeesDaoImplTest.java