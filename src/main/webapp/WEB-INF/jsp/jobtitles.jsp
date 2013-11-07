<!DOCTYPE html>

<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<h3>Job Title List</h3>
<table id="t1">
	<!--  Heading -->
	<tr>
		<!-- <sec:authorize access="hasRole('ROLE_ADMIN')"> -->
		<!-- <th>Task</th></sec:authorize> -->
		<th>ID</th>
		<th>Name</th>
		<th>Edit/Remove</th>
	</tr>

	<!--  Rows -->
	<c:forEach items="${jobs}" var="job">
		<tr>
			<!-- <sec:authorize access="hasRole('ROLE_ADMIN')">
				<td>delete</td>
			</sec:authorize> -->
			<td>${job.id}</td>
			<td>${job.name}</td>
			<td><input type="submit" value="Edit" class="jobEditBtn"
				data-id="${job.id}"></td>
	</c:forEach>
</table>

<div class="hidden" id="jobEditDialog" title="Add/Edit Job Titles">
	<h1>Edit Area:</h1>
	<fieldset>
		<form:form id="editForm" action="job_titles/edit" method="put">
			<label for="name">Name</label>
			<input type="text" id="name" name="name" />
			<input type="hidden" id="id" name="id" />
			<h1>Press submit to accept changes.</h1>
			<input type="submit" name="editSubmit" value="Submit" />
	</fieldset>
	</form:form>

	<div id="dangerZone">
		<br> <b>DANGER ZONE:</b>
		<form action="job_titles/delete" method="post">
			<fieldset>
				<input type="hidden" name="deleteId" id="deleteId" /> Type the
				title's <b>name</b> (case sensitive) and press remove to erase it
				permanently: <input type="text" name="confirmString" id="deleteName" />
				<br> <input type="submit" value="Remove" id="deleteSubmit" />
			</fieldset>
		</form>
	</div>
</div>

<br>
<button type="button" id="addBtn">Add Job Title</button>
