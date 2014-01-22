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
package com.osc.edu.commons.customers.web;

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

import com.osc.edu.commons.customers.dto.CustomersDto;
import com.osc.edu.commons.customers.service.CustomersService;
import com.osc.edu.commons.exception.ResourceNotFoundException;

/**
 * <pre>
 * Customers Controller
 * </pre>
 * @author Sang-cheon Park
 * @version 1.0
 */
@Controller
@RequestMapping("/customers")
@SessionAttributes("customers")
public class CustomersController {
	
    private static final Logger logger = LoggerFactory.getLogger(CustomersController.class);
    
    @Autowired
    private CustomersService customersService;
    
    @ModelAttribute("messageList")
    public List<String> messageList() throws Exception {
        return Arrays.asList(new String[]{ "This", "is", "sample", "message", "using", "@ModelAttribute." });
    }

    @RequestMapping("/getCustomersList")
    public ModelAndView getCustomersList(HttpServletRequest request, HttpServletResponse response) {
    	ModelAndView mav = new ModelAndView("customers/list");
    	mav.addObject("customersList", customersService.getCustomersList());
    	
    	return mav;
    }

    @RequestMapping("/getCustomers")
    public ModelAndView getCustomers(@RequestParam Integer customerId) {
    	ModelAndView mav = new ModelAndView("customers/form");
    	mav.addObject("customers", customersService.getCustomers(customerId));
    	
    	return mav;
    }

    @RequestMapping("/insertCustomersForm")
    public ModelAndView insertCustomersForm() {
    	ModelAndView mav = new ModelAndView("customers/form");
    	mav.addObject("customers", new CustomersDto());
    	
    	return mav;
    }
    
    @RequestMapping("/insertCustomers")
    public String insertCustomers(@RequestParam(value = "imgFile", required = false) MultipartFile imgFile,
    		@ModelAttribute @Valid CustomersDto customers, BindingResult results, SessionStatus status, HttpSession session) {
    	
		if (results.hasErrors()) {
	    	logger.debug("results : [{}]", results);
			return "customers/form";
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
    		
    		customersService.insertCustomers(customers);
    		status.setComplete();
    	} catch (Exception e) {
    		logger.debug("Exception has occurred. ", e);
    	}
    	
    	return "redirect:/customers/getCustomersList.do";
    }
    
    @RequestMapping("/updateCustomers")
    public String updateCustomers(@RequestParam(value = "imgFile", required = false) MultipartFile imgFile,
    		@ModelAttribute @Valid CustomersDto customers, BindingResult results, SessionStatus status, HttpSession session) {
    	
		if (results.hasErrors()) {
	    	logger.debug("results : [{}]", results);
			return "customers/form";
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
    		customersService.updateCustomers(customers);
    		status.setComplete();
    	} catch (Exception e) {
    		logger.debug("Exception has occurred. ", e);
    	}

    	return "redirect:/customers/getCustomersList.do";
    }
    
    @RequestMapping("/insertCustomersList")
    public String insertCustomersList() {
    	List<CustomersDto> customersList = new ArrayList<CustomersDto>();
    	CustomersDto customers = null;
    	
    	for (int i = 1; i < 10; i++) {
	    	customers = new CustomersDto();
	    	customers.setCustomerName("CustomerName" + i);
	    	customers.setContactFirstname("FirstName" + i);
	    	customers.setContactLastname("LastName" + i);
	    	customers.setAddress1("Address" + i);
	    	customers.setPhone("Phone" + i);
	    	customers.setCity("City" + i);
	    	customers.setCountry("Country" + i);
	    	
	    	customersList.add(customers);
    	}
    	
		customersService.insertCustomersList(customersList);
    	
    	return "redirect:/customers/getCustomersList.do";
    }

    @RequestMapping(value="/getCustomers/{customerId}", method=RequestMethod.GET)
    @ResponseStatus(value=HttpStatus.OK)
    @ResponseBody
    public CustomersDto getCustomers(HttpServletRequest request, @PathVariable Integer customerId) {
    	logger.debug("customersId : [{}]", customerId);
    	
    	CustomersDto customers = customersService.getCustomers(customerId);
    	
    	if(customers == null) {
    		throw new ResourceNotFoundException("Resource Not Found at [" + request.getRequestURI() + "]");
        }
    	
    	return customers;
    }

    @ResponseStatus(value=HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException.class)
	public Response handleCustomException(ResourceNotFoundException ex) {
    	logger.error("ResourceNotFoundException has occurred. : ", ex);
		return Response.status(Status.NOT_FOUND).build();
	}
}
//end of CustomersController.java