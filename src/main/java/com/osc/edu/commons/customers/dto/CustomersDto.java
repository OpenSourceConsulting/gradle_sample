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
package com.osc.edu.commons.customers.dto;

import java.io.Serializable;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * <pre>
 * Data Transfer Object Class for Customers table
 * </pre>
 * @author Sang-cheon Park
 * @version 1.0
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "customers")
public class CustomersDto implements Serializable {
	
	private static final long serialVersionUID = -7356398184124345523L;

	@XmlAttribute(required=true)
	private Integer customerId;

	@NotNull
	@Size(min = 1, max = 50)
	@XmlElement(required=true)
	private String customerName;

	@NotNull
	@Size(min = 1, max = 50)
	@XmlElement(name="firstName")
	private String contactLastname;

	@NotNull
	@Size(min = 1, max = 50)
	@XmlElement(name="lastName")
	private String contactFirstname;

	@NotNull
	@Size(min = 8, max = 50)
	@XmlElement
	private String phone;

	@NotNull
	@Size(min = 1, max = 50)
	@XmlElement
	private String address1;

	@Size(max = 50)
	@XmlElement
	private String address2;

	@Size(max = 50)
	@XmlElement
	private String city;

	@Size(max = 50)
	@XmlElement
	private String state;

	@Size(min = 1, max = 15)
	@XmlElement
	private String zipcode;

	@NotNull
	@Size(min = 1, max = 50)
	@XmlElement
	private String country;

	@Digits(integer = 4, fraction = 0)
	@XmlElement
	private Integer employeeId;

	@Digits(integer = 10, fraction = 2)
	@XmlElement
	private Double creditLimit;

	/**
	 * @return the customerId
	 */
	public Integer getCustomerId() {
		return customerId;
	}

	/**
	 * @param customerId the customerId to set
	 */
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	/**
	 * @return the customerName
	 */
	public String getCustomerName() {
		return customerName;
	}

	/**
	 * @param customerName the customerName to set
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	/**
	 * @return the contactLastname
	 */
	public String getContactLastname() {
		return contactLastname;
	}

	/**
	 * @param contactLastname the contactLastname to set
	 */
	public void setContactLastname(String contactLastname) {
		this.contactLastname = contactLastname;
	}

	/**
	 * @return the contactFirstname
	 */
	public String getContactFirstname() {
		return contactFirstname;
	}

	/**
	 * @param contactFirstname the contactFirstname to set
	 */
	public void setContactFirstname(String contactFirstname) {
		this.contactFirstname = contactFirstname;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return the address1
	 */
	public String getAddress1() {
		return address1;
	}

	/**
	 * @param address1 the address1 to set
	 */
	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	/**
	 * @return the address2
	 */
	public String getAddress2() {
		return address2;
	}

	/**
	 * @param address2 the address2 to set
	 */
	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the zipcode
	 */
	public String getZipcode() {
		return zipcode;
	}

	/**
	 * @param zipcode the zipcode to set
	 */
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @return the employeeId
	 */
	public Integer getEmployeeId() {
		return employeeId;
	}

	/**
	 * @param employeeId the employeeId to set
	 */
	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	/**
	 * @return the creditLimit
	 */
	public Double getCreditLimit() {
		return creditLimit;
	}

	/**
	 * @param creditLimit the creditLimit to set
	 */
	public void setCreditLimit(Double creditLimit) {
		this.creditLimit = creditLimit;
	}

	@Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
//end of CustomersDto.java