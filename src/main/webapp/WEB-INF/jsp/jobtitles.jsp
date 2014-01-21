<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<h3>Job Titles</h3>

<button id="addBtn">Add</button>
<div id="addEntity" class="editRow">
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

<div id="t1"> 
	<div id="th" class="row headers activeTH">
		<div class="inputCol">Job Title</div> <div class="inputCol">Description</div> <div class="buttonCol">&nbsp;</div> <div class="buttonCol">&nbsp;</div>
	</div> 
	<div id="thEdit" class="row editRow headers"><div class="inputCol">Job Title*</div> <div class="inputCol">Description</div> <div class="buttonCol">*=required</div> <div class="buttonCol">&nbsp;</div></div>
	<c:forEach items="${jobs}" var="job">
		<div id="jobRow${job.id}" class="row">
			<div class="jobName inputCol" data-value="${job.name}">${job.name}</div>
			<div class="jobDesc inputCol" data-value="${job.description}">${job.description}<c:if test="${empty job.description}">&nbsp;</c:if></div> 
			<div class="buttonCol"><button class="editBtn" value="${job.id}">Edit</button></div>		
			<div class="buttonCol"><button class="removeBtn" value="${job.id}">Remove</button></div>
		</div>
		
		<div id="editJobRow${job.id}" class="row editRow">
			<div class="inputCol"><input name="name" class="editJobName inputCol"/></div>
			<div class="inputCol"><input name="description" class="editJobDesc inputCol"/></div>
			<div class="buttonCol"><button class="saveBtn" value="${job.id}">Save</button></div>
			<div class="buttonCol"><button class="cancelEditBtn" value="${job.id}">Cancel</button></div>
		</div>
	</c:forEach> 
</div>