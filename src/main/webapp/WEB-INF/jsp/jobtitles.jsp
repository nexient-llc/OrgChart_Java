<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<h3>Job Titles</h3> 
<table id="t1"> 
	<tr><!-- <sec:authorize access="hasRole('ROLE_ADMIN')"> -->
		<!-- <th>Task</th></sec:authorize> --> 
		<th>Job Title</th>		
	</tr> 
	<c:forEach items="${jobs}" var="job">
		<tr class="jobRow" data-job-id="${job.id}" data-job-name="${job.name}"> 
			<!-- <sec:authorize access="hasRole('ROLE_ADMIN')">
				<td>delete</td>
			</sec:authorize> -->
			<td style="margin-top:3px;margin-bottom:3px">${job.name}</td> 
			<td style="padding:0"><button class="editJobTitle" style="margin-top:0;margin-bottom:0">Edit</td>
		</tr>
	</c:forEach> 
</table>

<div id="addBtn-container">
		<button type="button" id="addBtn" style="width: 45px;">Add</button>	
</div>

<div id="addEntity" style="display:none">
	<fieldset>
		<legend>Add Job Title</legend>
		<form:form modelAttribute="jobTitle" action="jobs" method="post">
		<div>
			<label>Job Title:</label>
			<form:input path="name" />
			<button type="submit">Save</button>
			<button type="reset" id="cancelAdd">Cancel</button>
		</div>
		<div></div>
		</form:form>
	</fieldset>
</div>

<div id="editEntity" style="display:none">
	<fieldset>
		<legend>Edit Job Title</legend>
		<form:form modelAttribute="jobTitle" action="jobs" method="put">
			<div>
				<form:hidden path="id" />
				<label>Job Title:</label>
				<form:input path="name" />*
				<button type="submit">Save</button>
				<button type="reset" id="cancelEdit">Cancel</button>
				<br />
				<h5>Required fields indicated with a *</h5>
			</div>
		</form:form>
	</fieldset>
</div>