<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="sec"uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<header>Systems In Motion Organization Chart</header>
<!-- ADD BUTTON -->
<div id="addBtn-container"><button type="button" id="addBtn" style="width: 45px;">Add</button></div>

<!-- ADD ENTITY FORM -->
<div id="Entity" style="display:none">
	<fieldset>
		<legend>Add Department</legend>
		<form:form id="departmentForm" action="depts" method="post">
			<div>
			<labeL>Department Name:</labeL>
				<input id ="deptName" type="text" name="name"/>
			<labeL>Parent Department:</label>
				<select name="parent_id">
					<option>...</option>
					<c:forEach items="${depts}" var="dept">
						<option id="selectParentId" value="${dept.id}">${dept.name}</option>
					</c:forEach>
				</select>
				<input id ="parentId" type="hidden" name="parentDepartment.id"/>
				<input id ="deptId" type="hidden" name="id"/>
				<button class="submit" type="submit">Save</button>
				<button class = "reset" type="reset">Cancel</button>
			</div>
		</form:form>
	</fieldset>
</div>

<!-- EDIT ENTITY FORM -->
<div id="editEntity" style="display:none">
	<fieldset>
		<legend >Edit Department</legend>
		<form:form id="editDepartmentForm" action="deptsEdit" method="put">
			<div>
			<labeL>Department Name:</labeL>
				<input id ="deptEditName" type="text" name="name"/>
			<labeL>Parent Department:</label>
				<select name="parent_edit_id" id='selectEdit'>
					<option>...</option>
					<c:forEach items="${depts}" var="dept">
						<option id="selectEditParentId" value="${dept.id}">${dept.name}</option>
					</c:forEach>
				</select>
				<input id ="parentEditId" type="hidden" name="parentDepartment.id"/>
				<input id ="deptEditId" type="hidden" name="id"/>
				<button type="submit">Save</button>
				<button class = "reset" type="reset">Cancel</button>	
			</div>
		</form:form>
	</fieldset>
</div>

<!-- DEPARTMENT TABLE -->
<div>
<table id="t1"> 
	<tr><!-- <sec:authorize access="hasRole('ROLE_ADMIN')"> -->
		<!-- <th>Task</th></sec:authorize> --> 
		<th>Department Name</th> 
		<th>Parent Department</th>
		<th>Edit</th> 
		<th>Delete</th>
	</tr> 
	<c:forEach items="${depts}" var="dept">
		<tr> 
			<!-- <sec:authorize access="hasRole('ROLE_ADMIN')">
				<td>delete</td>
			</sec:authorize> -->
			<td>${dept.name}</td> 
			<td>${dept.parentDepartment.name}</td>
			<td><button class ="editBtn" type="button" value='${dept.id}'>Edit</button></td>
			<td><button class ="deleteBtn" type="button" value='${dept.id}'>Delete</button></td>
		</tr>
	</c:forEach>
</table>
</div>

<!-- DELETE FORM -->
<div id="deleteEntity" style="display:none">
	<fieldset>
		<legend>Delete Department</legend>
			<form:form id="deleteForm" action="deptsDelete" method="delete">
			<div>	
					<input type="hidden" id="deptDeleteId" name="id"/>
					<button type="submit">Delete</button>
					<button class = "reset" type="reset">Cancel</button>
			</div>
			</form:form>
	</fieldset>
</div>
