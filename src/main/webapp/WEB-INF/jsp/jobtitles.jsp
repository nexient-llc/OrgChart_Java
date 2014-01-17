<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<h3>Job Titles</h3>

<div id="addBtn-container">
		<button type="button" id="addBtn">Add</button>	
</div>
<div id="addEntity" style="display:none">
	<fieldset>
		<legend>Add Job Title</legend>
		<form:form modelAttribute="newJob" action="jobs" method="post">		
			<div>
				<label>Job Title:</label>
				<form:input path="name"  required="true"/>
				<label>Description:</label>
				<form:input path="description"/>
				<input type="submit" value="Save" />
				<button id="cancelAddBtn">Cancel</button>
			</div>
		</form:form>
	</fieldset>
</div>

<table id="t1"> 
	<tr id="th" class="activeTH"><!-- <sec:authorize access="hasRole('ROLE_ADMIN')"> -->
		<!-- <th>Task</th></sec:authorize> --> 
		<th>Job Title</th> <th>Description</th> <th></th> <th></th>
	</tr> 
	<tr id="thEdit" style="display:none"><th>Job Title*</th> <th>Description</th> <th>*=required</th> <th></th></tr>
	<c:forEach items="${jobs}" var="job">
		<tr id="jobRow${job.id}"> 
			<!-- <sec:authorize access="hasRole('ROLE_ADMIN')">
				<td>delete</td>
			</sec:authorize> -->
			<td class="jobName" data-value="${job.name}">${job.name}</td>
			<td class="jobDesc" data-value="${job.description}">${job.description}</td> 
			<td><button class="editBtn" value="${job.id}">Edit</button></td>		
			<td><button class="removeBtn" value="${job.id}">Remove</button>
		</tr>
		
		<tr id="editJobRow${job.id}" style="display:none">
			<td><input name="name" class="editJobName"/></td>
			<td><input name="description" class="editJobDesc"/></td>
			<td><button class="saveBtn" value="${job.id}">Save</button></td>
			<td><button class="cancelEditBtn" value="${job.id}">Cancel</button></td>
		</tr>
	</c:forEach> 
</table>