<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<h3>Job Titles</h3>

<div id="addBtn-container">
		<button type="button" id="addBtn" style="width: 45px;">Add</button>	
</div>

<div id="addEntity" style="display:none">
	<fieldset>
		<legend>Add Job Title</legend>
		<form:form modelAttribute="job" action="jobs" method="post">
			<table>
				<tr>
					<td>Job Title Name:</td>
					<td><form:input path="name" /></td>
				</tr>
				<tr>
					<td>Description:</td>
					<td><form:input path="description"></form:input></td>
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
		<th>Job Title</th> <th>Description</th>
	</tr> 
	<c:forEach items="${jobs}" var="job">
		<tr id="ViewJobs${job.id}"> 
			<!-- <sec:authorize access="hasRole('ROLE_ADMIN')">
				<td>delete</td>
			</sec:authorize> -->
			<td>${job.name}</td> 
			<td>${job.description}</td>
			<td><button class="editBtn" value="${job.id}">Edit</button></td>
		</tr>
		
		<tr id="EditJobs${job.id}" style="display:none">
			<td><input path="name" id="jobName${job.id}" value="${job.name}"></td>
			<td><input path="description" id="jobDescript${job.id}" value="${job.description}"></td>
			<td><button class='saveBtn' value="${job.id}">Save</button></td>
			<td><button class='cancelEditBtn' value="${job.id}">Cancel</button></td>
		</tr>
	</c:forEach>
</table>