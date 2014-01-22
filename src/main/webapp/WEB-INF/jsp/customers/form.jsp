<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<title>Customers Form Page</title>
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
	   if (document.customersForm.customerId.value == "") {
	       document.customersForm.action="<c:url value='/customers/insertCustomers.do'/>";
	   } else {
	       document.customersForm.action="<c:url value='/customers/updateCustomers.do'/>";
	   }
	   
       document.customersForm.submit();
   }  
   
   function fncCancel() {
       document.customersForm.action="<c:url value='/customers/getCustomersList.do'/>";
       document.customersForm.submit();
   }
</script>
</head>
<body>
<h3>Customers Form</h3>
<form:form modelAttribute="customers" name="customersForm" method="post" action="insertCustomers.do" enctype="multipart/form-data">
    <form:hidden id="customerId" path="customerId" value="${customers.customerId}" />
	<table class='gridtable'>
		<c:if test="${customers.customerId ne null}">
			<tr>
				<th>CUSTOMER_ID</th>
				<td>
					<a href='<c:url value="/api/customers/getCustomers/${customers.customerId}.json" />'>${customers.customerId}(JSON)</a>,
					<a href='<c:url value="/api/customers/getCustomers/${customers.customerId}.xml" />'>${customers.customerId}(XML)</a>
				</td>
			</tr>
		</c:if>
		<tr>
			<th>CUSTOMER_NAME</th>
			<td><form:input path="customerName" /></td>
		</tr>
		<tr>
			<th>CONTACT_LASTNAME</th>
			<td><form:input path="contactLastname" /></td>
		</tr>
		<tr>
			<th>CONTACT_FIRSTNAME</th>
			<td><form:input path="contactFirstname" /></td>
		</tr>
		<tr>
			<th>PHONE</th>
			<td><form:input path="phone" /></td>
		</tr>
		<tr>
			<th>ADDRESS1</th>
			<td><form:input path="address1" /></td>
		</tr>
		<tr>
			<th>ADDRESS2</th>
			<td><form:input path="address2" /></td>
		</tr>
		<tr>
			<th>CITY</th>
			<td><form:input path="city" /></td>
		</tr>
		<tr>
			<th>STATE</th>
			<td><form:input path="state" /></td>
		</tr>
		<tr>
			<th>ZIPCODE</th>
			<td><form:input path="zipcode" /></td>
		</tr>
		<tr>
			<th>COUNTRY</th>
			<td><form:input path="country" /></td>
		</tr>
		<tr>
			<th>EMPLOYEE_ID</th>
			<td><form:input path="employeeId" /></td>
		</tr>
		<tr>
			<th>CREDIT_LIMIT</th>
			<td><form:input path="creditLimit" /></td>
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
</body>
</html>