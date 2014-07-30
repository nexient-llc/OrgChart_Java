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
<!-- 	<fieldset> -->
<!-- 		<legend>Add Employee</legend> -->
<!-- 		<form name="newemp" action="emps" method="post"> -->
<!-- 		<div><labeL>emp Name:</labeL><input type="text" name="name"/> -->
<!-- 			<labeL>Parent emp:</label> -->
<!-- 			<select name="parent_id"> -->
<!-- 				<option>...</option> -->
<%-- 				<c:forEach items="${emps}" var="emp"> --%>
<%-- 					<option value="${emp.id}">${emp.name}</option> --%>
<%-- 				</c:forEach> --%>
<!-- 			</select> -->
<!-- 			<button type="submit">Save</button> -->
<!-- 		</div> -->
<!-- 		<div></div> -->
<!-- 		</form> -->
<!-- 	</fieldset> -->
</div>
