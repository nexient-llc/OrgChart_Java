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

<div id="addDeptBtn-container">
	<button type="button" id="addDeptBtn" style="width: 45px;">Add</button>
</div>

<div id="addDeptEntity" style="display: none">
	<fieldset>
		<legend id="deptFormLegend">Add Department</legend>
		<form:form id="newDeptForm" class="addDept" name="newDept" action="depts" method="post">
			<div id="deptFormInputs">
				<label for="name">Dept Name:</label><input type="text" name="name" id="name"/>* <br><labeL>Parent
					Dept:</label> <select name="parentDepartment.departmentId" id="parentDepartment.departmentId">
					<option value="">...</option>
					<c:forEach items="${depts}" var="dept">
						<option value="${dept.departmentId}">${dept.name}</option>
					</c:forEach>
				</select><br>
				<button type="submit" id="submitBtn">Save</button>
				<button type="button" id="cancelDeptBtn">Cancel</button>
			</div>
			<div style="padding-top:5px"><footer>Required Fields indicated with a *</footer></div>
		</form:form>
	</fieldset>
</div>