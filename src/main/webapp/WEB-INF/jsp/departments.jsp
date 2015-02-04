<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<header>Systems In Motion Organization Chart: Departments</header>

<h3>Department Page</h3>
<sec:authorize access="hasRole('ROLE_ADMIN')">
<div id="addBtn-container">
	<button type="button" id="addBtn" style="width: 45px;">Add</button>
</div>

<div id="addEntity-container" style="display: none">
	<fieldset>
		<legend>Add New Department</legend>
		<form name="newDept" action="depts" method="post">
			<div>
				<labeL>*Department Name:</labeL>
				<input type="text" name="name"required /> 
				<labeL>Parent Department:</label> 
				<select class="deptDropDown" name="parentDepartment.id">
					<option value="">...</option>
					<c:forEach items="${depts}" var="dept">
						<option value="${dept.id}">${dept.name}</option>
					</c:forEach>
				</select>
				<button type="submit">Save</button>
				<button type="reset" id="cancelAddButton">Cancel</button>
				Required Fields indicated with a *
			</div>
			<div></div>
			<input type="hidden" name="${_csrf.parameterName }"
			value="${_csrf.token }" />
		</form>
	</fieldset>
</div>
</sec:authorize>
<div id="deptTable-container">
	<table id="t1">
		<tr>
			<th>Department Name</th>
			<th>Parent Department Name</th>
			<th></th>
		</tr>
		<c:forEach items="${depts}" var="dept">
			<tr id="tableRow${dept.id}">
				<td>${dept.name}</td>
				<td>${dept.parentDepartment.name}</td>
				<sec:authorize access="hasRole('ROLE_ADMIN')">
				<td><button class="editButton" value="${dept.id}">Edit</button></td>
				</sec:authorize>
			</tr>
		</c:forEach>
	</table>
</div>

<div id="editEntity-container" style="display: none">
	<fieldset>
		<legend>Edit Department</legend>
		<form:form name="editDept" action="depts" method="put">
			<input type="hidden" id="departmentId" name="id"/>
			<div>
				<labeL>*Department Name:</labeL> 
				<input type="text" name="name" id="departmentName" required /> 
				<labeL>Parent Department:</label> 
				<select	class="deptDropDown" name="parentDepartment.id" id="parentDepartment">
					<option value="">...</option>
					<c:forEach items="${depts}" var="dept">
						<option value="${dept.id}">${dept.name}</option>
					</c:forEach>
				</select>
				<button type="submit">Save</button>
				<button type="reset" id="cancelEditButton">Cancel</button>
				<button type="button" id="removeButton">Remove</button>
				Required Fields indicated with a *
			</div>
			<div></div>
			
			<input type="hidden" name="${_csrf.parameterName }"
			value="${_csrf.token }" />
			
		</form:form>
	</fieldset>
</div>
