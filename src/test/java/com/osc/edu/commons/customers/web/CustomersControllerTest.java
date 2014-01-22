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
package com.osc.edu.commons.customers.web;

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
public class CustomersControllerTest {
	
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
	 * Test method for {@link com.osc.edu.chapter4.customers.CustomersController#getCustomersList(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)}.
	 * @throws Exception 
	 */
	@Test
	public void testGetCustomersList() {
		try {
			this.mockMvc.perform(get("/customers/getCustomersList.do").accept(MediaType.parseMediaType("text/html;charset=UTF-8")))
					.andDo(print())
					.andExpect(status().isOk())
					.andExpect(model().attributeExists("customersList"))
					.andExpect(view().name("customers/list"))
					.andExpect(forwardedUrl("/WEB-INF/jsp/customers/list.jsp"));
		} catch (Exception e) {
			e.printStackTrace();
			fail("Exception has occurred.");
		}
	}

	/**
	 * Test method for {@link com.osc.edu.chapter4.customers.CustomersController#insertCustomersList()}.
	 */
	@Test
	@Transactional
	@Rollback(value=true)
	public void insertCustomersList() {
		try {
			this.mockMvc.perform(post("/customers/insertCustomersList.do").accept(MediaType.parseMediaType("text/html;charset=UTF-8")))
					.andDo(print())
					.andExpect(status().isMovedTemporarily())
					.andExpect(redirectedUrl("/customers/getCustomersList.do?messageList=This&messageList=is&messageList=sample&messageList=message&messageList=using&messageList=%40ModelAttribute."));
		} catch (Exception e) {
			e.printStackTrace();
			fail("Exception has occurred.");
		}
	}

	/**
	 * Test method for {@link com.osc.edu.chapter4.customers.CustomersController#getCustomers(javax.servlet.http.HttpServletRequest, java.lang.Integer)}.
	 */
	@Test
	public void testGetCustomersWithValidId() {
		try {
			this.mockMvc.perform(get("/customers/getCustomers/103").accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
					.andDo(print())
					.andExpect(status().isOk())
					.andExpect(content().contentType("application/json;charset=UTF-8"))
					.andExpect(jsonPath("$.customerId").value(103))
					.andExpect(jsonPath("$.customerName").value("Atelier graphique"))
					.andExpect(jsonPath("$.contactLastname").value("Schmitt"))
					.andExpect(jsonPath("$.contactFirstname").value("Carine"))
					.andExpect(jsonPath("$.phone").value("40.32.2555"))
					.andExpect(jsonPath("$.address1").value("54, rue Royale"));
		} catch (Exception e) {
			e.printStackTrace();
			fail("Exception has occurred.");
		}
	}

	/**
	 * Test method for {@link com.osc.edu.chapter4.customers.CustomersController#getCustomers(javax.servlet.http.HttpServletRequest, java.lang.Integer)}.
	 */
	@Test
	public void testGetCustomersWithInvalidId() {
		try {
			this.mockMvc.perform(get("/customers/getCustomers/101").accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
					.andDo(print())
					.andExpect(status().isNotFound());
		} catch (Exception e) {
			e.printStackTrace();
			fail("Exception has occurred.");
		}
	}

}
//end of CustomersControllerTest.java