<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

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
			<td class="tableData">${dept.name}</td>
			<td class="tableData">${dept.parentDepartment.name}</td>
			<td><button type="button" class="editDept" value="${dept.departmentId}">Edit</button></td>
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
				<labeL>Dept Name:</labeL><input type="text" name="name" /> <labeL>Parent
					Dept:</label> <select name="parentDepartment.departmentId">
					<option value="">...</option>
					<c:forEach items="${depts}" var="dept">
						<option value="${dept.departmentId}">${dept.name}</option>
					</c:forEach>
				</select></br>
				<button type="submit" id="submitBtn">Save</button>
				<button type="button" id="cancelBtn">Cancel</button>
			</div>
			<div></div>
		</form>
	</fieldset>
</div>

<div id="editEntity" style="display: none">
	<fieldset>
		<legend>Edit Department</legend>
		<form:form name="editDept" action="depts" method="put">
			<div>
				<input type="hidden" name="departmentId" id="deptId">
				<labeL>Dept Name:</labeL><input type="text" name="name" id="deptName"/> <labeL>Parent
					Dept:</label> <select name="parentDepartment.departmentId" id="parentDeptName">
					<option value="">...</option>
					<c:forEach items="${depts}" var="dept">
						<option value="${dept.departmentId}">${dept.name}</option>
					</c:forEach>
				</select></br>
				<button type="submit" class="submitEditBtn">Save</button>
				<button type="button" id="cancelEditBtn">Cancel</button>
			</div>
			<div></div>
		</form:form>
	</fieldset>
</div>
