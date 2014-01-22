<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<title>Employees List Page</title>
<style type="text/css">
table.gridtable {
	font-family: verdana,arial,sans-serif;
	font-size:11px;
	color:#333333;
	border-width: 1px;
	border-color: #666666;
	border-collapse: collapse;
	text-align: center;
	
}
table.gridtable th {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #666666;
	background-color: #dedede;
}
table.gridtable td {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #666666;
	background-color: #ffffff;
}
</style>
</head>
<body>
<h3>Employees List</h3>
<c:if test="${! empty employees}">
This is employees value using @SessionAttribute.
<ul>
	<li>EMPLOYEE_ID : ${employees.employeeId}</li>
	<li>LASTNAME : ${employees.lastname}</li>
	<li>FIRSTNAME : ${employees.firstname}</li>
	<li>EXTENSION : ${employees.extension}</li>
	<li>EMAIL : ${employees.email}</li>
	<li>OFFICE_CODE : ${employees.officeCode}</li>
	<li>REPORTS_TO : ${employees.reportsTo}</li>
	<li>JOB_TITLE : ${employees.jobTitle}</li>
</ul>
<br/>
</c:if>
<table class='gridtable'>
	<thead>
		<tr>
			<th>EMPLOYEE_ID</th>
			<th>LASTNAME</th>
			<th>FIRSTNAME</th>
			<th>EXTENSION</th>
			<th>EMAIL</th>
			<th>OFFICE_CODE</th>
			<th>REPORTS_TO</th>
			<th>JOB_TITLE</th>
		</tr>
	</thead>
	<tbody>
		<c:choose>
			<c:when test="${empty employeesList}">
				<tr>
					<td colspan='8'>No result.</td>
				</tr>
			</c:when>
			<c:otherwise>
				<c:forEach var="employees" items="${employeesList}">
					<tr>
						<td>
							<a href='<c:url value="/api/employees/getEmployees/${employees.employeeId}.json" />'>${employees.employeeId}(JSON)</a>,
							<a href='<c:url value="/api/employees/getEmployees/${employees.employeeId}.xml" />'>${employees.employeeId}(XML)</a>
						</td>
						<td><a href='<c:url value="/employees/getEmployees.do?employeeId=${employees.employeeId}" />'>${employees.lastname}</a></td>
						<td>${employees.firstname}</td>
						<td>${employees.extension}</td>
						<td>${employees.email}</td>
						<td>${employees.officeCode}</td>
						<td>${employees.reportsTo}</td>
						<td>${employees.jobTitle}</td>
					</tr>
				</c:forEach>
			</c:otherwise>
		</c:choose>
	</tbody>
</table>
</body>
</html>