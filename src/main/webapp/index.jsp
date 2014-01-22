<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<title>Open Source Consulting Sample Page</title>
</head>
<body>
<h2>Spring + MyBatis Example</h2>
<ul>
	<li><a href='<c:url value="/customers/getCustomersList.do" />'>Customers List</a></li>
	<li><a href='<c:url value="/employees/getEmployeesList.do" />'>Employees List</a></li>
	<li><a href='<c:url value="/customers/insertCustomersForm.do" />'>Customer Insert Form</a></li>
	<li><a href='<c:url value="/employees/insertEmployeesForm.do" />'>Employee Insert Form</a></li>
	<li><a href='<c:url value="/customers/insertCustomersList.do" />'>Customer List Insert(Commit)</a></li>
	<li><a href='<c:url value="/employees/insertEmployeesList.do" />'>Employee List Insert(Rollback)</a></li>
</ul>
</body>
</html>