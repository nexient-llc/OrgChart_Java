<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<h3>Departments</h3>
<table id="t1">
	<tr>
		<!-- <sec:authorize access="hasRole('ROLE_ADMIN')"> -->
		<!-- <th>Task</th></sec:authorize> -->
		<th>Dept Name</th>
		<th>Parent Dept</th>
	</tr>
	<c:forEach items="${depts}" var="dept">
		<tr>
			<!-- <sec:authorize access="hasRole('ROLE_ADMIN')">
				<td>delete</td>
			</sec:authorize> -->
			<td>${dept.name}</td>
			<td>${dept.parentDepartment.name}</td>
			<td><div id="editBtn-container${dept.id}">
					<button type="button" class="editBtn" value = "${dept.id}" onclick="editDepartment("${dept.id}")" style="width: 55px;">Edit</button>
				</div></td>
		</tr>
	</c:forEach>
</table>

<div id="addBtn-container">
	<button type="button" id="addBtn" style="width: 45px;">Add</button>
</div>


<div id="addEntity" style="display: none">
	<fieldset>
		<legend>Add Department</legend>
		<form name="newDept" action="depts" method="post">
			<div>
				<labeL>Dept Name:</labeL><input type="text" path="name" name="name" />
				<labeL>Parent Dept:</label> <select name="parentDepartmentId">
					<option></option>
					<c:forEach items="${depts}" var="dept">
						<option value="${dept.id}">${dept.name}</option>
					</c:forEach>
				</select>
				<button type="submit">Save</button>
			</div>
			<div></div>
		</form>
	</fieldset>
</div>
<div id="editEntity" style="display: none" >
	<fieldset>
		<legend>Edit Department</legend>
		<form name="editDept" action="depts/put" method="post">
			<div>
				<input id="editEntityId" type="hidden" name="deptEditId" />
				<labeL>Dept Name:</labeL><input type="text" name="name" />
				<labeL>Parent Dept:</label> <select name="parentDepartmentId">
					<option></option>
					<c:forEach items="${depts}" var="dept">
						<option value="${dept.id}">${dept.name}</option>
					</c:forEach>
				</select>
				<button type="submit">Save</button>
			</div>
			<div></div>
		</form>
	</fieldset>
</div>
