<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<title>Customers List Page</title>
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
<h3>Customers List</h3>
<c:if test="${! empty customers}">
This is customers value using @SessionAttribute.
<ul>
	<li>CUSTOMER_ID : ${customers.customerId}</li>
	<li>CUSTOMER_NAME : ${customers.customerName}</li>
	<li>CONTACT_LASTNAME : ${customers.contactLastname}</li>
	<li>CONTACT_FIRSTNAME : ${customers.contactFirstname}</li>
	<li>PHONE : ${customers.phone}</li>
	<li>ADDRESS1 : ${customers.address1}</li>
	<li>ADDRESS2 : ${customers.address2}</li>
	<li>CITY : ${customers.city}</li>
	<li>STATE : ${customers.state}</li>
	<li>ZIPCODE : ${customers.zipcode}</li>
	<li>COUNTRY : ${customers.country}</li>
	<li>EMPLOYEE_ID : ${customers.employeeId}</li>
	<li>CREDIT_LIMIT : ${customers.creditLimit}</li>
</ul>
<br/>
</c:if>
<table class='gridtable'>
	<thead>
		<tr>
			<th>CUSTOMER_ID</th>
			<th>CUSTOMER_NAME</th>
			<th>CONTACT_LASTNAME</th>
			<th>CONTACT_FIRSTNAME</th>
			<th>PHONE</th>
			<th>ADDRESS1</th>
			<th>ADDRESS2</th>
			<th>CITY</th>
			<th>STATE</th>
			<th>ZIPCODE</th>
			<th>COUNTRY</th>
			<th>EMPLOYEE_ID</th>
			<th>CREDIT_LIMIT</th>
		</tr>
	</thead>
	<tbody>
		<c:choose>
			<c:when test="${empty customersList}">
				<tr>
					<td colspan='13'>No result.</td>
				</tr>
			</c:when>
			<c:otherwise>
				<c:forEach var="customers" items="${customersList}">
					<tr>
						<td>
							<a href='<c:url value="/api/customers/getCustomers/${customers.customerId}.json" />'>${customers.customerId}(JSON)</a>,
							<a href='<c:url value="/api/customers/getCustomers/${customers.customerId}.xml" />'>${customers.customerId}(XML)</a>
						</td>
						<td><a href='<c:url value="/customers/getCustomers.do?customerId=${customers.customerId}" />'>${customers.customerName}</a></td>
						<td>${customers.contactLastname}</td>
						<td>${customers.contactFirstname}</td>
						<td>${customers.phone}</td>
						<td>${customers.address1}</td>
						<td>${customers.address2}</td>
						<td>${customers.city}</td>
						<td>${customers.state}</td>
						<td>${customers.zipcode}</td>
						<td>${customers.country}</td>
						<td>${customers.employeeId}</td>
						<td>${customers.creditLimit}</td>
					</tr>
				</c:forEach>
			</c:otherwise>
		</c:choose>
	</tbody>
</table>
</body>
</html>