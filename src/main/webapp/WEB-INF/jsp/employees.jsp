<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<h3>Employees</h3>
<table id="t1">
	<tr>
		<sec:authorize access="hasRole('ROLE_ADMIN')">
			<!-- <th>Task</th>--></sec:authorize> 
			<th>Employee First Name</th>
			<th>Employee Last Name</th>
	</tr>
	<c:forEach items="${emps}" var="emp">
		<tr>
			<!-- <sec:authorize access="hasRole('ROLE_ADMIN')"><td>delete</td> </sec:authorize> -->
			<td>${emp.firstName}</td>
			<td>${emp.lastName}</td>
		</tr>
	</c:forEach>
</table>

<div id="addBtn-container">
	<button type="button" id="addBtn" style="width: 45px;">Add</button>
</div>

<div id="addEntity" style="display: none">
	<fieldset>
		<legend>Add Employee</legend>
		
		<form:form modelAttribute="emp" action="emps" method="post">
			<table>
				<tr>
					<td>Employee First Name :</td>
					<td><form:input path="firstName" /></td>
				</tr>
				<tr>
					<td>Employee Last Name :</td>
					<td><form:input path="lastName" /></td>
				</tr>
			</table>
			<input type=submit />
		</form:form>
	</fieldset>
</div>
