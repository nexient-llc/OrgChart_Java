<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<h3>Job Titles</h3>

<div id="addBtn-container">
	<button type="button" id="addBtn" style="width: 45px;">Add</button>
</div>

<div id="addEntity" style="display: none">
	<fieldset>
		<legend>Add Job Title</legend>
		<form:form modelAttribute="job" action="jobs" method="post">
				<div>
					<label>Name:</label>
					<form:input class="textBoxClass" path="name" />
				</div>
				<div>
					<label>Description:</label>
					<form:input class="textBoxClass" path="description"></form:input>
				</div>
			<input type=submit />
			<button id="cancelBtn">Cancel</button>
		</form:form>
	</fieldset>
</div>

<div class="divTable">
	<div class="headRow">
		<!-- <sec:authorize access="hasRole('ROLE_ADMIN')"> -->
		<!-- <th>Task</th></sec:authorize> -->
		<div class="divCol">Job Title</div>
		<div class="divCol">Description</div>
		<div class="divCol">&nbsp;</div>
	</div>
	<c:forEach items="${jobs}" var="job">
		<div class="divRow" id="ViewJobs${job.id}">
			<!-- <sec:authorize access="hasRole('ROLE_ADMIN')">
				<td>delete</td>
			</sec:authorize> -->
			<div class="content">${job.name}</div>
			<div class="content">${job.description}</div>
			<div class="content">
				<button class="editBtn" value="${job.id}">Edit</button>
			</div>
		</div>

		<div class="divRow" id="EditJobs${job.id}" style="display: none">
			<div class="content">
				<input path="name" id="jobName${job.id}" value="${job.name}"
					class="textBoxClass">
			</div>
			<div class="content">
				<input path="description" id="jobDescript${job.id}"
					value="${job.description}" class="textBoxClass">
			</div>
			<div class="content">
				<button class='saveBtn' value="${job.id}">Save</button>
				<button class='cancelEditBtn' value="${job.id}">Cancel</button>
			</div>
		</div>
	</c:forEach>
</div>