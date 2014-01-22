<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<title>"Systems in Motion Organization Chart: Department Page"</title>

<h1>"Department Page "</h1>
<a href='<c:url value=""/>'>Login</a>
<br />
<h3>Departments</h3>

<div id="addBtn-container">
	<button type="button" id="addBtn" style="width: 45px;">Add</button>
	<button type="button" id="searchBtn" style="width: 60px;">Search</button>
</div>
<div id="editDeptBtn-container"></div>

<div id="searchBtn-container"></div>

<div id="deleteBtn-container"></div>

<table id="t1">
	<thead>
		<tr>
			<th bgcolor="blue">Department Name</th>
			<th bgcolor="blue">Parent Department</th>
			<th bgcolor="blue">Manager Tools</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${depts}" var="dept">
			<tr>
				<!-- <sec:authorize access="hasRole('ROLE_ADMIN')">
				<td>delete</td>
			</sec:authorize> -->
				<td><input type="hidden" name="deptId" value="${dept.id}" /><input type="hidden" name="isActive" value="${dept.isActive}"/><span
					class="deptName">${dept.name}</span></td>
				<%-- 		    <td><input type="hidden" name="isActive" value="${dept.isActive}"/> </td>  --%>
				<td><input type="hidden" name="parentDeptId"
					value="${dept.parentDepartment.id}" /> <span class="parentDeptName"></span>
					${dept.parentDepartment.name}</td>
				<td><button type="button" class="editBtn" style="width: 45px;">Edit</button>
					<button type="button" class="deleteBtn" style="width: 60px;">Delete</button></td>
			</tr>
		</c:forEach>
	</tbody>
</table>


<div id="addEntity" style="display: none">
	<fieldset>
		<legend>Add Department</legend>
		<legend>* = Required</legend>
		<form:form modelAttribute="dept" id="addForm" action="depts"
			method="post">
			<table>
				<tr>
					<td>* Dept Name:</td>
					<td><form:input path="name" /></td>
				</tr>
				<tr>
					<td>Parent Dept:</td>
					<td><form:select path="parentDepartment.id">
							<form:option value="" label="" />
							<form:options items="${depts}" itemValue="id" itemLabel="name" />
						</form:select></td>
				</tr>
			</table>
			<button type="submit" onclick="save">Save</button>
			<div id="cancelAddBtn-container">
				<button type="button" id="cancelAddBtn" style="width: 60px;">Cancel</button>
			</div>
		</form:form>
	</fieldset>
</div>

<div id="edit-modal" title="Edit Department" style="display: none">
	<form:form modelAttribute="dept" id="editForm" action="dept"
		method="post">
		<input type="hidden" name="id" />
		<div>
			<label for="name">Name : </label><input type="text" name="name"
				value="" />
		</div>
		<div>
			<label for="name">Parent : </label>
			<form:select path="parentDepartment.id">
				<form:option value="" label="" />
				<form:options items="${depts}" itemValue="id" itemLabel="name" />
			</form:select>
		</div>
	</form:form>
</div>

<div id="active-modal" title="Edit Department" style="display: none">
	<form:form modelAttribute="dept" id="editForm" action="dept"
		method="post">
		<input type="hidden" name="id" />
		<div>
			<label for="name">Name : </label><input type="text" name="name"
				value="" disabled="disabled"/>
		</div>

	</form:form>
</div>
