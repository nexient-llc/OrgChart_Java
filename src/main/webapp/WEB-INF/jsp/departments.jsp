<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<h3>Departments</h3> 
<table id="t1"> 
	<tr><!-- <sec:authorize access="hasRole('ROLE_ADMIN')"> -->
		<!-- <th>Task</th></sec:authorize> --> 
		<th>Dept Name</th> 
		<th>Parent Dept</th>
	</tr> 
	<c:forEach items="${depts}" var="dept">
		<tr class="deptRow" data-dept-id="${dept.id}" data-dept-name="${dept.name}" data-parent-dept-id="${dept.parentDepartment.id}"> 
			<!-- <sec:authorize access="hasRole('ROLE_ADMIN')">
				<td>delete</td>
			</sec:authorize> -->
			<td style="margin-top:3px;margin-bottom:3px">${dept.name}</td> 
			<td>${dept.parentDepartment.name}</td>
			<td style="padding:0"><button class="editDepartment" style="margin-top:0;margin-bottom:0">Edit</td>
		</tr>
	</c:forEach> 
</table>

<div id="addBtn-container">
		<button type="button" id="addBtn" style="width: 45px;">Add</button>	
</div>

<div id="addEntity" style="display:none">
	<fieldset>
		<legend>Add Department</legend>
		<form:form modelAttribute="department" action="depts" method="post">
		<div>
			<label>Dept Name:</label>
			<form:input path="name" />*<br />
			<label>Parent Dept:</label>
			<form:select path="parentDepartment.id">
				<form:option value="-1" label="---" />
				<form:options items="${depts}" itemValue="id" itemLabel="name" />
			</form:select><br />
			<button type="submit">Save</button>
			<button type="reset" id="cancelAdd">Cancel</button>
			<br />
			<h5>Required fields indicated with a *</h5>
		</div>
		<div></div>
		</form:form>
	</fieldset>
</div>

<div id="editEntity" style="display:none">
	<fieldset>
		<legend>Edit Department</legend>
		<form:form modelAttribute="department" action="depts" method="put">
			<div>
				<form:hidden path="id" />
				<form:hidden path="isInactive" />
				<label>Dept Name:</label>
				<form:input path="name" />*<br />
				<label>Parent Dept:</label>
				<form:select path="parentDepartment.id">
					<form:option value="-1" label="---" />
					<form:options items="${depts}" itemValue="id" itemLabel="name" />
				</form:select>*<br />
				<button type="submit">Save</button>
				<button type="reset" id="cancelEdit">Cancel</button>
				<button id="removeDept" type="button">Remove Department</button>
				<br />
				<h5>Required fields indicated with a *</h5>
			</div>
		</form:form>
	</fieldset>
</div>
