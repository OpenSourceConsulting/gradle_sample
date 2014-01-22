<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<title>Employees Form Page</title>
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
<script type="text/javascript">   
   function fncSubmit() {
	   if (document.employeesForm.employeeId.value == "") {
	       document.employeesForm.action="<c:url value='/employees/insertEmployees.do'/>";
	   } else {
	       document.employeesForm.action="<c:url value='/employees/updateEmployees.do'/>";
	   }
	   
       document.employeesForm.submit();
   }
   
   function fncCancel() {
       document.employeesForm.action="<c:url value='/employees/getEmployeesList.do'/>";
       document.employeesForm.submit();
   }
</script>
</head>
<body>
<h3>Employees Form</h3>
<form:form modelAttribute="employees" name="employeesForm" method="post" action="insertEmployees.do" enctype="multipart/form-data">
    <form:hidden id="employeeId" path="employeeId" value="${employees.employeeId}" />
	<table class='gridtable'>
		<c:if test="${employees.employeeId ne null}">
			<tr>
				<th>EMPLOYEE_ID</th>
				<td>
					<a href='<c:url value="/api/employees/getEmployees/${employees.employeeId}.json" />'>${employees.employeeId}(JSON)</a>,
					<a href='<c:url value="/api/employees/getEmployees/${employees.employeeId}.xml" />'>${employees.employeeId}(XML)</a>
				</td>
			</tr>
		</c:if>
		<tr>
			<th>LASTNAME</th>
			<td><form:input path="lastname" /></td>
		</tr>
		<tr>
			<th>FIRSTNAME</th>
			<td><form:input path="firstname" /></td>
		</tr>
		<tr>
			<th>EXTENSION</th>
			<td><form:input path="extension" /></td>
		</tr>
		<tr>
			<th>EMAIL</th>
			<td><form:input path="email" /></td>
		</tr>
		<tr>
			<th>OFFICE_CODE</th>
			<td><form:input path="officeCode" /></td>
		</tr>
		<tr>
			<th>REPORTS_TO</th>
			<td><form:input path="reportsTo" /></td>
		</tr>
		<tr>
			<th>JOB_TITLE</th>
			<td><form:input path="jobTitle" /></td>
		</tr>
		<tr>
			<th>File Upload</th>
			<td><input type="file" name="imgFile"/></td>
		</tr>
	</table>
	<br/>
	<input type="button" value="submit" onclick="javascript:fncSubmit();" />
	<input type="button" value="cancel" onclick='javascript:fncCancel();' />
</form:form>
<br/>
<c:if test="${! empty messageList}">
	<c:forEach var="message" items="${messageList}">
		<c:out value="${message}" />&nbsp;
	</c:forEach>
</c:if>
</body>
</html>