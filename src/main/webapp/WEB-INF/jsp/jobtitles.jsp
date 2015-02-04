<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<header>Systems In Motion Organization Chart: Job Titles</header>

<h3>Job Title Page</h3>

<sec:authorize access="hasRole('ROLE_ADMIN')">
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
			<input type="hidden" name="${_csrf.parameterName }"
			value="${_csrf.token }" />
		</form>
	</fieldset>
</div>
</sec:authorize>

<div id="jobTitlesTable-container">
	<table id="t1">
		<tr>
			<th>Job Titles</th>
			<th></th>
		</tr>
		<c:forEach items="${titles}" var="title">
			<tr id="tableRow${title.id}">
				<td>${title.name}</td>
				<sec:authorize access="hasRole('ROLE_ADMIN')">
				<td><button class="editButton" value="${title.id}">Edit</button></td>
				</sec:authorize>
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
			<input type="hidden" name="${_csrf.parameterName }"
			value="${_csrf.token }" />
		</form:form>
	</fieldset>
</div>