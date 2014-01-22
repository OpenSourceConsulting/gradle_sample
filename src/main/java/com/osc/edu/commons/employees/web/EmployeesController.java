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
 * Sang-cheon Park	2014. 1. 8.		First Draft.
 */
package com.osc.edu.commons.employees.web;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.osc.edu.commons.employees.dto.EmployeesDto;
import com.osc.edu.commons.employees.service.EmployeesService;
import com.osc.edu.commons.exception.ResourceNotFoundException;

/**
 * <pre>
 * Employees Controller
 * </pre>
 * @author Sang-cheon Park
 * @version 1.0
 */
@Controller
@RequestMapping("/employees")
@SessionAttributes("employees")
public class EmployeesController {
	
    private static final Logger logger = LoggerFactory.getLogger(EmployeesController.class);
    
    @Autowired
    private EmployeesService employeesService;
    
    @ModelAttribute("messageList")
    public List<String> messageList() throws Exception {
        return Arrays.asList(new String[]{ "This", "is", "sample", "message", "using", "@ModelAttribute." });
    }

    @RequestMapping("/getEmployeesList")
    public ModelAndView getEmployeesList(HttpServletRequest request, HttpServletResponse response) {
    	ModelAndView mav = new ModelAndView("employees/list");
    	mav.addObject("employeesList", employeesService.getEmployeesList());
    	
    	return mav;
    }

    @RequestMapping("/getEmployees")
    public ModelAndView getEmployees(@RequestParam Integer employeeId) {
    	ModelAndView mav = new ModelAndView("employees/form");
    	mav.addObject("employees", employeesService.getEmployees(employeeId));
    	
    	return mav;
    }

    @RequestMapping("/insertEmployeesForm")
    public ModelAndView insertEmployeesForm() {
    	ModelAndView mav = new ModelAndView("employees/form");
    	mav.addObject("employees", new EmployeesDto());
    	
    	return mav;
    }
    
    @RequestMapping("/insertEmployees")
    public String insertEmployees(@RequestParam(value = "imgFile", required = false) MultipartFile imgFile,
    		@ModelAttribute @Valid EmployeesDto employees, BindingResult results, SessionStatus status, HttpSession session) {

		if (results.hasErrors()) {
	    	logger.debug("results : [{}]", results);
			return "employees/form";
		}
		
    	try {
    		if (imgFile != null && !imgFile.getOriginalFilename().equals("")) {
    			String fileName = imgFile.getOriginalFilename();
    			String destDir = session.getServletContext().getRealPath("/upload");

    			File dirPath = new File(destDir);
    			if (!dirPath.exists()) {
    				boolean created = dirPath.mkdirs();
    				if (!created) {
    					throw new Exception("Fail to create a directory for movie image. [" + destDir + "]");
    				}
    			}
    			
    			IOUtils.copy(imgFile.getInputStream(), new FileOutputStream(new File(destDir, fileName)));
    			
    			logger.debug("Upload file({}) saved to [{}].", fileName, destDir);
    		}
    		
    		employeesService.insertEmployees(employees);
    		status.setComplete();
    	} catch (Exception e) {
    		logger.debug("Exception has occurred. ", e);
    	}
    	
    	return "redirect:/employees/getEmployeesList.do";
    }
    
    @RequestMapping("/updateEmployees")
    public String updateEmployees(@RequestParam(value = "imgFile", required = false) MultipartFile imgFile,
    		@ModelAttribute @Valid EmployeesDto employees, BindingResult results, SessionStatus status, HttpSession session) {

		if (results.hasErrors()) {
	    	logger.debug("results : [{}]", results);
			return "employees/form";
		}
		
    	try {
    		if (imgFile != null && !imgFile.getOriginalFilename().equals("")) {
    			String fileName = imgFile.getOriginalFilename();
    			String destDir = session.getServletContext().getRealPath("/upload");

    			File dirPath = new File(destDir);
    			if (!dirPath.exists()) {
    				boolean created = dirPath.mkdirs();
    				if (!created) {
    					throw new Exception("Fail to create a directory for movie image. [" + destDir + "]");
    				}
    			}
    			
    			IOUtils.copy(imgFile.getInputStream(), new FileOutputStream(new File(destDir, fileName)));
    			
    			logger.debug("Upload file({}) saved to [{}].", fileName, destDir);
    		}
    		
    		employeesService.updateEmployees(employees);
    		status.setComplete();
    	} catch (Exception e) {
    		logger.debug("Exception has occurred. ", e);
    	}
    	
    	return "redirect:/employees/getEmployeesList.do";
    }
    
    @RequestMapping("/insertEmployeesList")
    public String insertEmployeesList() {
    	List<EmployeesDto> employeesList = new ArrayList<EmployeesDto>();
    	EmployeesDto employees = null;
    	
    	for (int i = 1; i < 10; i++) {
	    	employees = new EmployeesDto();
	    	employees.setLastname("Lastname" + i);
	    	employees.setFirstname("Firstname" + i);
	    	employees.setExtension("Extension" + i);
	    	employees.setEmail("Email" + i);
	    	employees.setOfficeCode("OfficeCode" + i);
	    	employees.setReportsTo(i);
	    	employees.setJobTitle("JobTitle" + i);
	    	
	    	employeesList.add(employees);
    	}
    	
    	try {
    		employeesService.insertEmployeesList(employeesList);
    	} catch (Exception e) {
    		logger.debug("Exception has occurred. ", e);
    	}
    	
    	return "redirect:/employees/getEmployeesList.do";
    }

    @RequestMapping(value="/getEmployees/{employeeId}", method=RequestMethod.GET)
    @ResponseStatus(value=HttpStatus.OK)
    @ResponseBody
    public EmployeesDto getEmployees(HttpServletRequest request, @PathVariable Integer employeeId) {
    	logger.debug("employeesId : [{}]", employeeId);
    	
    	EmployeesDto employees = employeesService.getEmployees(employeeId);
    	
    	if(employees == null) {
    		throw new ResourceNotFoundException("Resource Not Found at [" + request.getRequestURI() + "]");
        }
    	
    	return employees;
    }

    @ResponseStatus(value=HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException.class)
	public Response handleCustomException(ResourceNotFoundException ex) {
    	logger.error("ResourceNotFoundException has occurred. : ", ex);
		return Response.status(Status.NOT_FOUND).build();
	}

}
//end of EmployeesController.java