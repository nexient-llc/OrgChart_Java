<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<h3>Departments</h3> 
<table id="t1"> 
	<tr><!-- <sec:authorize access="hasRole('ROLE_ADMIN')"> -->
		<!-- <th>Task</th></sec:authorize> --> 
		<th>Dept Name</th> 
		<th>Parent Dept</th>
		<th>Edit/Remove</th>
	</tr> 
	<c:forEach items="${depts}" var="dept">
		<tr> 
			<!-- <sec:authorize access="hasRole('ROLE_ADMIN')">
				<td>delete</td>
			</sec:authorize> -->
			<td>${dept.name}</td> 
			<td>${dept.parentDepartment.name}</td> 
			<td>
				<input type="submit" value="Edit" class="depEditBtn" data-id="${dept.id}"/>
			</td>
		</tr>
	</c:forEach> 
</table>

<div class="hidden" id="depEditDialog" title="Add/Edit Departments">
	<h1>Edit Area:</h1>
	<fieldset>
	<form:form id="editForm" action="departments/edit" method="put">
		<label for="name">Name</label>
		<input type="text" id="name" name="name"/>
		<input type="hidden" id="id" name="id"/>
		<label for="parentDepartment">Department</label>
		<select name="parentDepartment.id" id="parentDepartment">
			<option value="">None</option>
			<c:forEach items="${depts}" var="dept">
				<option value="${dept.id}" >${dept.name}</option>
			</c:forEach>
		</select>
		<br><br>
    	<h1> Press submit to accept changes. </h1>
		<input type="submit" name="editSubmit" value="Submit"/>
	</fieldset>
	</form:form>
	
	<div id="dangerZone">
	<br>
	<b>DANGER ZONE:</b>
	<form action="departments/delete" method="post">
		<fieldset>
		<input type="hidden" name="deleteId" id="deleteId"/>
		Type the department's <b>name</b> (case sensitive) and press remove to erase them permanently:
		<input type="text" name="confirmString" id="deleteName"/>
		<br>
		<input type="submit" value="Remove" id="deleteSubmit"/>
		</fieldset>
	</form>
	</div>
</div>

<button type="button" id="addBtn">Add New Department</button>	

