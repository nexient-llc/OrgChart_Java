<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="sec"
        uri="http://www.springframework.org/security/tags"%>

<h3>Job Title Page</h3>

<div id="addBtn-container">
	<button type="button" id="addBtn">Add</button>        
</div>

<div id="editBtn-container">
	<button type="button" id="editBtn">Edit</button>
</div>

<div id="cancelBtn-container" style="display:none">
	<button type="button" id="cancelBtn">Cancel</button>
</div>

<div id="addEntity" style="display:none;">
	<fieldset>
	    <legend>Add Job Title</legend>
		<form name="addJob" action="jobs" method="post">
			<div>
				<label>Job Title:</label><input type="text" name="name"/>
				<button type="submit" id="saveEntity">Save</button>
				<button type="button" id="cancelEntity">Cancel</button>
			</div>
			<div></div>
		</form>
	</fieldset>
</div>

<div class="divContainer"> 
	<div style="overflow:hidden;">
		<!-- <sec:authorize access="hasRole('ROLE_ADMIN')"> -->
		<!-- <th>Task</th>
		</sec:authorize> --> 
		<div class="divHeader">Job Title</div><!-- 
		--><div class="divHeaderHidden" style="display:none">Edit</div>
	</div>
	<div style="overflow:hidden;">
		<c:forEach items="${jobs}" var="job">
			<div id="divRow${job.id}"> 
				<!-- <sec:authorize access="hasRole('ROLE_ADMIN')">
					<td>delete</td>
				</sec:authorize> -->
				<div class="divColumn">${job.name}</div> 
				<div class="divColumnHidden" style="display:none">
					<button type="button" class="editColumnBtn" value="${job.id}">Edit</button>
				</div>
			</div>
			<div id="divEditRow${job.id}" style="display:none">
				<div class="divColumn"><input id="editInputBox${job.id}" value="${job.name}"/></div>
				<div class="divColumn">
					<button type="button" class="submitColumnBtn" value="${job.id}">Submit</button>
					<button type="button" class="removeColumnBtn" value="${job.id}">Remove</button>
					<button type="button" class="cancelColumnBtn" value="${job.id}">Cancel</button>
				</div>
			</div>
		</c:forEach>
	</div>
</div>