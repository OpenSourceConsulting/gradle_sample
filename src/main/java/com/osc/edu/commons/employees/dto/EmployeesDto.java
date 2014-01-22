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
package com.osc.edu.commons.employees.dto;

import java.io.Serializable;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
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
 * Data Transfer Object Class for Employees table
 * </pre>
 * @author Sang-cheon Park
 * @version 1.0
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "employees")
public class EmployeesDto implements Serializable {

	private static final long serialVersionUID = -4502337008566708279L;

	@XmlAttribute(required=true)
	private Integer employeeId;

	@NotNull
	@Size(min = 1, max = 50)
	@XmlElement(required=true)
	private String lastname;

	@NotNull
	@Size(min = 1, max = 50)
	@XmlElement(required=true)
	private String firstname;

	@NotNull
	@Size(min = 1, max = 10)
	@XmlElement
	private String extension;

	@NotNull
	@Size(min = 1, max = 100)
	@Pattern(regexp = "^[_0-9a-zA-Z-]+@[0-9a-zA-Z]+(.[_0-9a-zA-Z-]+)*$", message = "Invalid email format.")
	@XmlElement
	private String email;

	@NotNull
	@Size(min = 1, max = 10)
	@XmlElement
	private String officeCode;
	
	@XmlElement
	@Digits(integer = 4, fraction = 0)
	private Integer reportsTo;

	@NotNull
	@Size(min = 1, max = 50)
	@XmlElement
	private String jobTitle;

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
	 * @return the lastname
	 */
	public String getLastname() {
		return lastname;
	}

	/**
	 * @param lastname the lastname to set
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	/**
	 * @return the firstname
	 */
	public String getFirstname() {
		return firstname;
	}

	/**
	 * @param firstname the firstname to set
	 */
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	/**
	 * @return the extension
	 */
	public String getExtension() {
		return extension;
	}

	/**
	 * @param extension the extension to set
	 */
	public void setExtension(String extension) {
		this.extension = extension;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the officeCode
	 */
	public String getOfficeCode() {
		return officeCode;
	}

	/**
	 * @param officeCode the officeCode to set
	 */
	public void setOfficeCode(String officeCode) {
		this.officeCode = officeCode;
	}

	/**
	 * @return the reportsTo
	 */
	public Integer getReportsTo() {
		return reportsTo;
	}

	/**
	 * @param reportsTo the reportsTo to set
	 */
	public void setReportsTo(Integer reportsTo) {
		this.reportsTo = reportsTo;
	}

	/**
	 * @return the jobTitle
	 */
	public String getJobTitle() {
		return jobTitle;
	}

	/**
	 * @param jobTitle the jobTitle to set
	 */
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	@Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
//end of EmployeesDto.java