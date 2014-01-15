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
		<tr id="ViewDepts${dept.id}"> 
			<!-- <sec:authorize access="hasRole('ROLE_ADMIN')">
				<td>delete</td>
			</sec:authorize> -->
			<td>${dept.name}</td> 
			<td>${dept.parentDepartment.name}</td>
			<td><button class="editBtn" value="${dept.id}">Edit</button></td>
			<td><button class="deleteBtn" value="${dept.id}">Delete</button></td>
		</tr>
		
		<tr id="EditDepts${dept.id}" style="display:none">
			<td><input path="name" id="deptName${dept.id}" value="${dept.name}"></td>
			<td><select path="parentDepartment.id" id="deptParentId${dept.id}">
				<option value="" label=""/>
				<c:forEach items="${depts}" var="dept2">
				<c:choose>
					<c:when test="${dept.parentDepartment.id == dept2.id}">
						<option selected value="${dept2.id}" label="${dept2.name}"/>
					</c:when>
					<c:otherwise>
						<option value="${dept2.id}" label="${dept2.name}"/>
					</c:otherwise>
				</c:choose>
				</c:forEach>
			</select></td>
			<td><button class='saveBtn' value="${dept.id}">Save</button></td>
			<td><button class='cancelEditBtn' value="${dept.id}">Cancel</button></td>
		</tr>
	</c:forEach>
</table>

