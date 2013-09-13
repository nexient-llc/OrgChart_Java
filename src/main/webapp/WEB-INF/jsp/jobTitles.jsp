<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<h3>Job Titles</h3>
<table id="t1">
	<tr>
		<th>Job Title Name</th>
		<th></th>
		<th></th>
	</tr>
	<c:forEach items="${jobs}" var="job">
		<tr>
			<td>${job.name}</td>
			<td><button class="editBtn" style="width: 45px;" value="${job.id}">Edit</button></td>
			<td><button class="removeBtn" style="width: 80px;" value="${job.id}">Remove</button></td>
		</tr>
	</c:forEach>
</table>

<div id="addBtn-container">
	<button type="button" id="addBtn" style="width: 45px;">Add</button>
</div>

<div id="editEntity">
	<jsp:include page="">
		<jsp:param name="formType" value="Edit" />
		<jsp:param name="action" value="EditJob" />
		<jsp:param name="method" value="put" />
	</jsp:include>
</div>

<div id="addEntity">
	<jsp:include page="">
		<jsp:param name="formType" value="Create" />
		<jsp:param name="action" value="CreateJob" />
		<jsp:param name="method" value="post" />
	</jsp:include>
</div>

<div id="removeEntity">
	<fieldset>
		<legend>Remove Job Title</legend>
		<form:form id="removeJobTitleForm" modelAttribute="JOB_TITLE" action="removeJob" method="delete">
			<div>
				<label>Are you sure?</label>
				<input id="removeJobTitleId" type="hidden" name="id" />
				<input type="submit" value="Delete" />
			</div>
		</form:form>
	</fieldset>
</div>
<div id="addEntity" style="display: none">
	
</div>