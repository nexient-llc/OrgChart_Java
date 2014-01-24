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
				<div>
					<label>Dept Name:</label>
					<form:input class="textBoxClass" path="name" />
				</div>
				<div>
					<label>Parent Dept: </label>
					<form:select path="parentDepartment.id">
							<form:option value="" label="" />
							<form:options items="${depts}" itemValue="id" itemLabel="name" />
					</form:select>
				</div>
			</table>
			<input type=submit />
			<button id="cancelBtn">Cancel</button>
		</form:form>
	</fieldset>
</div>

<div class="divTable">
	<div class="headRow">
		<!-- <sec:authorize access="hasRole('ROLE_ADMIN')"> -->
		<!-- <th>Task</th></sec:authorize> -->
		<div class="divCol">Dept Name</div>
		<div class="divCol">Parent Dept</div>
		<div class="divCol">&nbsp;</div>
	</div>
	<c:forEach items="${depts}" var="dept">
		<div class="divRow" id="ViewDepts${dept.id}">
			<!-- <sec:authorize access="hasRole('ROLE_ADMIN')">
				<td>delete</td>
			</sec:authorize> -->
			<div class="content">${dept.name}</div>
			<div class="content">${dept.parentDepartment.name}&nbsp;</div>
			<div class="content">
				<button class="editBtn" value="${dept.id}">Edit</button><button class="deleteBtn" value="${dept.id}">Remove</button>
			</div>
		</div>

		<div class="divRow" id="EditDepts${dept.id}" style="display: none;">
			<div class="content">
				<input path="name" id="deptName${dept.id}" value="${dept.name}" class="textBoxClass">
			</div>
			<div class="content">
				<select path="parentDepartment.id" id="deptParentId${dept.id}">
					<option value="" label="" />
					<c:forEach items="${depts}" var="dept2">
						<c:choose>
							<c:when test="${dept.parentDepartment.id == dept2.id}">
								<option selected value="${dept2.id}" label="${dept2.name}" />
							</c:when>
							<c:otherwise>
								<option value="${dept2.id}" label="${dept2.name}" />
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</select>
			</div>
			<div class="content rightAlign">
				<button class='saveBtn' value="${dept.id}">Save</button><button class='cancelEditBtn' value="${dept.id}">Cancel</button>
			</div>
		</div>
	</c:forEach>
</div>
