<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<h3>Job Title Page</h3>

<div id="addBtn-container">
	<button type="button" id="addBtn" style="width: 45px;">Add</button>
</div>

<div id="addEntity-container" style="display: none">
	<fieldset>
		<legend>Add New Job Title</legend>
		<form name="newJob" action="title" method="post">
			<div>
				<labeL>*Job Title Name:</labeL>
				<input type="text" name="name" required />
				<button type="submit">Save</button>
				<button type="reset" id="cancelAddButton">Cancel</button>
				Required Fields indicated with a *
			</div>
			<div></div>
		</form>
	</fieldset>
</div>
<div id="jobTitlesTable-container">
	<table id="t1">
		<tr>
			<!-- <sec:authorize access="hasRole('ROLE_ADMIN')"> -->
			<!-- <th>Task</th></sec:authorize> -->
			<th>Job Title</th>
			<th></th>
		</tr>
		<c:forEach items="${titles}" var="title">
			<tr id="tableRow${title.id}">
				<!-- <sec:authorize access="hasRole('ROLE_ADMIN')">
					<td>delete</td>
				</sec:authorize> -->
				<td>${title.name}</td>
				<td><button class="editButton" value="${title.id}">Edit</button></td>
			</tr>
		</c:forEach>
	</table>
</div>
<div id="editEntity-container" style="display: none">
	<fieldset>
		<legend>Edit Job Title</legend>
		<form:form name="editJobs" action="title" method="put">
			<input type="hidden" id="jobTitleId" name="id"/>
			<div>
				<labeL>*Job Title Name:</labeL> 
				<input type="text" name="name" id="jobTitleName" required /> 
				<button type="submit">Save</button>
				<button type="reset" id="cancelEditButton">Cancel</button>
				<button type="button" id="removeButton">Remove</button>
				Required Fields indicated with a *
			</div>
			<div></div>
		</form:form>
	</fieldset>
</div>