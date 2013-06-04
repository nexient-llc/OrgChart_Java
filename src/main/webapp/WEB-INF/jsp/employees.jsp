<!DOCTYPE html>
<%@ taglib prefix="c"    uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="sec"  uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<h3>Employees</h3> 
<table id="t1"> 
	<tr><!-- <sec:authorize access="hasRole('ROLE_ADMIN')"> -->
		<!-- <th>Task</th></sec:authorize> --> 
		<th>First Name</th>
		<th>Last Name</th>
		<th>Middle Initial</th>
		<th>Email</th>
		<th>Skype Name</th>
		<th>Job Title</th>
		<th>Department</th>
	</tr> 
	<c:forEach items="${emps}" var="emp">
		<tr> 
			<!-- <sec:authorize access="hasRole('ROLE_ADMIN')">
				<td>delete</td>
			</sec:authorize> -->
			<td>${emp.firstName}</td> 
			<td>${emp.lastName}</td> 
			<td>${emp.middleInitial}</td> 
			<td>${emp.email}</td> 
			<td>${emp.skypeName}</td> 
			<td>${emp.jobTitle.name}</td>
			<td>${emp.department.name}</td> </tr>
	</c:forEach> 
</table>

<div id="addBtn-container">
		<button type="button" id="addBtn" style="width: 45px;">Add</button>	
</div>

<div id="addEntity" style="display:none">
	<fieldset>
		<legend>Add Employee</legend>
		<form:form modelAttribute="newEmp" action="emps" method="post">
		<div>
			<form:input type="hidden" path="id" value="-1" />
			<labeL>First Name:</labeL><form:input path="firstName" type="text" />
			<labeL>Last Name:</labeL><form:input path="lastName" type="text" />
			<labeL>Middle Initial:</labeL><form:input path="middleInitial" type="text" />
			<labeL>Email:</labeL><form:input path="email" type="email" />
			<labeL>Skype Name:</labeL><form:input path="skypeName" type="text" />
			<labeL>Job Title:</label>
			<form:select path="jobTitle">
				<option>...</option>
				<c:forEach items="${jobs}" var="job">
					<option value="${job.id}">${job.name}</option>
				</c:forEach>
			</form:select>
			<labeL>Department:</label>
			<form:select path="department">
				<option>...</option>
				<c:forEach items="${depts}" var="dept">
					<option value="${dept.id}">${dept.name}</option>
				</c:forEach>
			</form:select>
			<button type="submit">Save</button>
		</div>
		</form:form>
	</fieldset>
</div>
