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
package com.osc.edu.commons.customers.dao.impl;

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

import com.osc.edu.commons.customers.dao.CustomersDao;
import com.osc.edu.commons.customers.dto.CustomersDto;

/**
 * <pre>
 * 
 * </pre>
 * @author Sang-cheon Park
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:./src/main/resources/spring/dispatcher-servlet.xml", "file:./src/main/resources/spring/context-common.xml" })
public class CustomersDaoImplTest {

	@Autowired
	private CustomersDao customersDao;

	@Test
	public void testGetCustomersList() {
		// 초기화
		List<CustomersDto> customersList = null;
		
		// 테스트
		try {
			// Mapper Interface 사용
			customersDao.setUseMapper(true);
			customersList = customersDao.getCustomersList();
			
			// 검증
			assertNotNull("customersList must not be null.", customersList);
			assertEquals("customersList's size must be 122.", 122, customersList.size());
			
			// SqlSession 사용
			customersDao.setUseMapper(false);
			customersList = customersDao.getCustomersList();
			
			// 검증
			assertNotNull("customersList must not be null.", customersList);
			assertEquals("customersList's size must be 122.", 122, customersList.size());			
		} catch (Exception e) {
			e.printStackTrace();
			fail("Exception has occurred.");
		}
	}
	
	@Test
	public void testGetCustomers() {
		// 초기화
		CustomersDto customers = null;
		
		// 테스트
		try {
			// Mapper Interface 사용
			customersDao.setUseMapper(true);
			customers = customersDao.getCustomers(103);
			
			// 검증
			assertNotNull("customers must not be null.", customers);
			assertEquals("customerId must be 103.", new Integer(103), customers.getCustomerId());
			
			// SqlSession 사용
			customersDao.setUseMapper(false);
			customers = customersDao.getCustomers(103);
			
			// 검증
			assertNotNull("customers must not be null.", customers);
			assertEquals("customerId must be 103.", new Integer(103), customers.getCustomerId());		
		} catch (Exception e) {
			e.printStackTrace();
			fail("Exception has occurred.");
		}
	}
	
	@Test
	@Transactional
	@Rollback(value=true)
	public void testInsertCustomers() {
		// 초기화
		CustomersDto customers = new CustomersDto();
    	customers.setCustomerName("CustomerName");
    	customers.setContactFirstname("FirstName");
    	customers.setContactLastname("LastName");
    	customers.setAddress1("Address");
    	customers.setPhone("Phone");
    	customers.setCity("City");
    	customers.setCountry("Country");

		try {
			assertNull("customerId must be null.", customers.getCustomerId());

			// 테스트
			customersDao.insertCustomers(customers);
			
			// 검증
			assertNotNull("customerId must not be null.", customers.getCustomerId());
			
			customers = customersDao.getCustomers(customers.getCustomerId());
			assertEquals("CustomerName must be equals CustomerName", "CustomerName", customers.getCustomerName());
			assertEquals("ContactFirstName must be equals FirstName", "FirstName", customers.getContactFirstname());
			assertEquals("ContactLastName must be equals LastName", "LastName", customers.getContactLastname());
			assertEquals("Address1 must be equals Address", "Address", customers.getAddress1());
			assertEquals("Phone must be equals Phone", "Phone", customers.getPhone());
			assertEquals("City must be equals City", "City", customers.getCity());
			assertEquals("Country must be equals Country", "Country", customers.getCountry());
		} catch (Exception e) {
			e.printStackTrace();
			fail("Exception has occurred.");
		}
	}
	
	@Test
	@Transactional
	@Rollback(value=true)
	public void testUpdateCustomers() {
		// 초기화
		CustomersDto customers = customersDao.getCustomers(103);

		try {
			assertNotEquals("CustomerName must not be equals CustomerName", "CustomerName", customers.getCustomerName());
			assertNotEquals("ContactFirstName must not be equals FirstName", "FirstName", customers.getContactFirstname());
			assertNotEquals("ContactLastName must not be equals LastName", "LastName", customers.getContactLastname());
			assertNotEquals("Address1 must not be equals Address", "Address", customers.getAddress1());
			assertNotEquals("Phone must not be equals Phone", "Phone", customers.getPhone());
			assertNotEquals("City must not be equals City", "City", customers.getCity());
			assertNotEquals("Country must not be equals Country", "Country", customers.getCountry());
			
	    	customers.setCustomerName("CustomerName");
	    	customers.setContactFirstname("FirstName");
	    	customers.setContactLastname("LastName");
	    	customers.setAddress1("Address");
	    	customers.setPhone("Phone");
	    	customers.setCity("City");
	    	customers.setCountry("Country");

			// 테스트
			customersDao.updateCustomers(customers);
			
			// 검증
			customers = customersDao.getCustomers(customers.getCustomerId());
			assertEquals("CustomerName must be equals CustomerName", "CustomerName", customers.getCustomerName());
			assertEquals("ContactFirstName must be equals FirstName", "FirstName", customers.getContactFirstname());
			assertEquals("ContactLastName must be equals LastName", "LastName", customers.getContactLastname());
			assertEquals("Address1 must be equals Address", "Address", customers.getAddress1());
			assertEquals("Phone must be equals Phone", "Phone", customers.getPhone());
			assertEquals("City must be equals City", "City", customers.getCity());
			assertEquals("Country must be equals Country", "Country", customers.getCountry());
		} catch (Exception e) {
			e.printStackTrace();
			fail("Exception has occurred.");
		}
	}
	
	@Test
	@Transactional
	@Rollback(value=true)
	public void testDeleteCustomers() {
		// 초기화
		List<CustomersDto> customersList = customersDao.getCustomersList();
		
		try {
			assertEquals("customersList's size must be 122.", 122, customersList.size());
			
			// 테스트
			customersDao.deleteCustomers(103);
			
			// 검증
			customersList = customersDao.getCustomersList();
			assertEquals("customersList's size must be 121.", 121, customersList.size());
		} catch (Exception e) {
			e.printStackTrace();
			fail("Exception has occurred.");
		}
	}

}
//end of CustomersDaoImplTest.java