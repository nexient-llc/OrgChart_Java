<!DOCTYPE HTML>

<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<h3>Employees</h3>

<div id="addBtn-container">
		<button type="button" id="addBtn" style="width: 45px;">Add</button>	
</div>

<table id="t1">
	<tr>
		<th>First Name</th>
		<th>Last Name</th>
		<th>Job Title</th>
		<th>Department Name</th>
	</tr> 
	<c:forEach items="${emps}" var="emp">
		<tr> 
			<td>${emp.firstName}</td> 
			<td>${emp.lastName}</td>
			<td>${emp.jobTitle.name}</td>
			<td>${emp.department.name}</td>
		</tr>
	</c:forEach> 
</table>