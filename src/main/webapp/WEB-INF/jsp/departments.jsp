<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<h3>Departments</h3> 

<div id="addBtn-container">
		<button type="button" id="addBtn" style="width: 45px;">Add</button>	
</div>

<div id="addEntity" style="display:none">
	<fieldset>
		<legend>Add Department</legend>
		<form:form modelAttribute="dept" action="depts" method="post">
			<table>
				<tr>
					<td>Dept Name:</td>
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
			<input type=submit />
			<button id="cancelBtn">Cancel</button>
		</form:form>
	</fieldset>
</div>

<table id="t1"> 
	<tr><!-- <sec:authorize access="hasRole('ROLE_ADMIN')"> -->
		<!-- <th>Task</th></sec:authorize> --> 
		<th>Dept Name</th> <th>Parent Dept</th>
	</tr> 
	<c:forEach items="${depts}" var="dept">
		<tr id="ViewDepts"> 
			<!-- <sec:authorize access="hasRole('ROLE_ADMIN')">
				<td>delete</td>
			</sec:authorize> -->
			<td>${dept.name}</td> 
			<td>${dept.parentDepartment.name}</td>
			<td><button class="editBtn" value="${dept.id}">Edit</button></td>
		</tr>
		<tr id="EditDepts${dept.id}" style="display:none">
			<td><input path="name" name="deptName" value=${dept.name}></td>
			<td><select path="parentDepartment.id" value=${dept.parentDepartment.id}>
				<c:forEach items="${depts}" var="dept2">
					<option value="${dept2.id}" label="${dept2.name}"/>
				</c:forEach>
			</select></td>
		</tr>
	</c:forEach>
</table>

<div id="editEntity" style="display:none">
	<fieldset>
		<legend>Edit Department</legend>
		<form:form modelAttribute="dept" action="depts" method="post">
			<table>
				<tr>
					<td>Dept Name:</td>
					<td><form:input path="name" value="test" /></td>
				</tr>
				<tr>
					<td>Parent Dept:</td>
					<td><form:select path="parentDepartment.id">
							<form:option value="" label="" />
							<form:options items="${depts}" itemValue="id" itemLabel="name" />
					</form:select></td>
				</tr>
			</table>
			<input type=submit />
			<button id="cancelBtn">Cancel</button>
		</form:form>
	</fieldset>
</div>
