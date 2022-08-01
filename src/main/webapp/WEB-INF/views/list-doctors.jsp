
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Doctors List</title>
</head>
<body style="background-color:powderblue;">
<div id="table root">
<table>
<thead>
<tr>
<th>Doctor ID</th>
<th>Doctor Name</th>
<th>Date Of Birth</th>
<th>Speciality</th>
<th>City</th>
<th>Phone Number</th>
<th>Standard Fees</th>
</tr>
</thead>
<tbody>
<c:forEach var="doctor" items="${alldoctors}">
<tr>
<td>${doctor.doctor_id}</td>
<td>${doctor.doctor_name}</td>
<td>${doctor.dob}</td>
<td>${doctor.speciality}</td>
<td>${doctor.city}</td>
<td>${doctor.phone_no}</td>
<td>${doctor.standard_fees}</td>
</tr>
</c:forEach>
</tbody>
</table>
</div>
</body>
</html>