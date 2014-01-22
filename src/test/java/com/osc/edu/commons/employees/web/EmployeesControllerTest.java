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
package com.osc.edu.commons.employees.web;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

/**
 * <pre>
 * 
 * </pre>
 * @author Sang-cheon Park
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "file:./src/main/resources/spring/dispatcher-servlet.xml", "file:./src/main/resources/spring/context-common.xml" })
public class EmployeesControllerTest {
	
	@Autowired
    private WebApplicationContext wac;
	
	private MockMvc mockMvc;

	/**
	 * <pre>
	 * 
	 * </pre>
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	/**
	 * Test method for {@link com.osc.edu.chapter4.employees.EmployeesController#getEmployeesList(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)}.
	 */
	@Test
	public void testGetEmployeesList() {
		try {
			this.mockMvc.perform(get("/employees/getEmployeesList.do").accept(MediaType.parseMediaType("text/html;charset=UTF-8")))
					.andDo(print())
					.andExpect(status().isOk())
					.andExpect(model().attributeExists("employeesList"))
					.andExpect(view().name("employees/list"))
					.andExpect(forwardedUrl("/WEB-INF/jsp/employees/list.jsp"));
		} catch (Exception e) {
			e.printStackTrace();
			fail("Exception has occurred.");
		}
	}

	/**
	 * Test method for {@link com.osc.edu.chapter4.employees.EmployeesController#insertEmployeesList()}.
	 */
	@Test
	@Transactional
	@Rollback(value=true)
	public void insertEmployeesList() {
		try {
			this.mockMvc.perform(get("/employees/insertEmployeesList.do").accept(MediaType.parseMediaType("text/html;charset=UTF-8")))
					.andDo(print())
					.andExpect(status().isMovedTemporarily())
					.andExpect(redirectedUrl("/employees/getEmployeesList.do?messageList=This&messageList=is&messageList=sample&messageList=message&messageList=using&messageList=%40ModelAttribute."));
		} catch (Exception e) {
			e.printStackTrace();
			fail("Exception has occurred.");
		}
	}

	/**
	 * Test method for {@link com.osc.edu.chapter4.employees.EmployeesController#getEmployees(javax.servlet.http.HttpServletRequest, java.lang.Integer)}.
	 */
	@Test
	public void testGetEmployeesWithValidId() {
		try {
			this.mockMvc.perform(get("/employees/getEmployees/1002").accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
					.andDo(print())
					.andExpect(status().isOk())
					.andExpect(content().contentType("application/json;charset=UTF-8"))
					.andExpect(jsonPath("$.employeeId").value(1002))
					.andExpect(jsonPath("$.lastname").value("Murphy"))
					.andExpect(jsonPath("$.firstname").value("Diane"))
					.andExpect(jsonPath("$.extension").value("x5800"))
					.andExpect(jsonPath("$.email").value("dmurphy@osci.com"))
					.andExpect(jsonPath("$.officeCode").value("1"));
		} catch (Exception e) {
			e.printStackTrace();
			fail("Exception has occurred.");
		}
	}

	/**
	 * Test method for {@link com.osc.edu.chapter4.employees.EmployeesController#getEmployees(javax.servlet.http.HttpServletRequest, java.lang.Integer)}.
	 */
	@Test
	public void testGetEmployeesWithInvalidId() {
		try {
			this.mockMvc.perform(get("/employees/getEmployees/1001").accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
					.andDo(print())
					.andExpect(status().isNotFound());
		} catch (Exception e) {
			e.printStackTrace();
			fail("Exception has occurred.");
		}
	}
}
//end of EmployeesControllerTest.java