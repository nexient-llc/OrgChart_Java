<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<h3>Departments</h3>
<table id="t1"> 
	<tr><sec:authorize access="hasRole('ROLE_ADMIN')">
		<th>Task</th></sec:authorize> 
		<th>Dept Name</th> <th>Parent Dept</th>
	</tr> 
	<c:forEach items="${depts}" var="dept">
		<tr> 
			<sec:authorize access="hasRole('ROLE_ADMIN')">
				<td>delete</td>
			</sec:authorize> 
			<td>${dept.name}</td> 
			<td>${dept.parentName}</td> </tr>
	</c:forEach> 
</table>

<div id="addBtn-container">
	<sec:authorize access="hasRole('ROLE_ADMIN')">
		<button type="button" id="addBtn">Add</button>
	</sec:authorize>
</div>

<div id="addEntity" style="display:none">
	<fieldset>
		<legend>Add Department</legend>
		<form action="/app/"
		<div><labeL>Dept Name:</labeL><input type="text" name="deptName"/></div>
		<div>
			<labeL>Parent Dept:</label>
			<select name="parentDeptId">
				<option>...</option>
				<c:forEach items="${depts}" var="dept">
					<option value="${dept.deptId}">${dept.name}</option>
				</c:forEach>
			</select>
		</div>
		<div><button type="submit">Save</button></div>
	</fieldset>
</div>
