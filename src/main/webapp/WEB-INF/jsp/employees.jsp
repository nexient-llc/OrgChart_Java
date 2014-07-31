<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<h3>Employees</h3> 
<table id="t1"> 
	<tr><!-- <sec:authorize access="hasRole('ROLE_ADMIN')"> -->
		<!-- <th>Task</th></sec:authorize> --> 
		<th>Employee</th>
		<th>Manages</th>
		<th>Job</th>
		<th>Department</th>
		<th>Manager</th>
		<th>Email</th>
		<th>Skype</th>
	</tr> 
	<c:forEach items="${emps}" var="emp">
		<tr> 
			<!-- <sec:authorize access="hasRole('ROLE_ADMIN')">
				<td>delete</td>
			</sec:authorize> -->
			<td>${emp.getFullName()}</td>
			<td>${emp.isManager == true ? "Yes" : "No"}</td> 
			<td>${emp.jobTitle.name}</td>
			<td>${emp.department.name}</td>
			<td>${emp.manager == null? "None" : emp.manager.getFullName()}</td>
			<td>${emp.email}</td>
			<td>${emp.skypeName}</td></tr>
	</c:forEach> 
</table>

<div id="addBtn-container">
		<button type="button" id="addBtn" style="width: 45px;">Add</button>	
</div>

<div id="addEntity" style="display:none">
	<fieldset>
		<legend>Add Employee</legend>
		<form name="newEmp" action="emps" method="post">
		<div>
			<labeL>First name:</labeL><input type="text" name="firstName"/>
			<labeL>Last name:</labeL><input type="text" name="lastName"/>
			<labeL>Department:</label>
			<select name="department_id">
				<option>...</option>
				<c:forEach items="${depts}" var="dept">
					<option value="${dept.id}">${dept.name}</option>
				</c:forEach>
			</select>
			<labeL>Email:</labeL><input type="text" name="email"/>
			<labeL>Skype name:</labeL><input type="text" name="skypeName"/>
			<labeL>Is a Manager:</labeL><input type="checkbox" name="isManager"/>
			<labeL>Job Title:</label>
			<select name="job_title_id">
				<option>...</option>
				<c:forEach items="${jobs}" var="job">
					<option value="${job.id}">${job.name}</option>
				</c:forEach>
			</select>
			<button type="submit">Save</button>
		</div>
		<div></div>
		</form>
	</fieldset>
</div>
