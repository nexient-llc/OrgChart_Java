<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<h3>Job Titles Page</h3>
<div id="createdJobTitleContainer" style="display: none">
	<h2>Successfully created new job title "${createdJob.name}"</h2>
</div>
<script type="text/javascript">
	var CREATED_JOB = "${createdJob.name}";
</script>
<div id="addBtn-container"
	<sec:authorize access="hasRole('ROLE_ADMIN')">style="display:block"</sec:authorize>
	style="display: none">
	<button type="button" id="addBtn" style="width: 45px;">Add</button>
</div>
<div id="addEntity" style="display: none">
	<fieldset>
		<legend>Add New Job Title</legend>
		<form name="newJob" id="newJobTitle" action="newJob" method="post">
			<div>
				<labeL>Job Title: *</labeL><input type="text" name="name"
					id="newJobName" required /> <input type="hidden" name="isActive"
					value="true"> <br> <br>
				<button type="submit">Save</button>
				<button type="reset" id="cancelBtn" value="reset">Cancel</button>
			</div>
			<br>
			<div>Required Fields indicated with a *</div>
		</form>
	</fieldset>
</div>

<div id="editContainer" style="display: none">
	<fieldset>
		<legend>Edit Job Title</legend>
		<form name="editJob" class="editFormClass" action="updateJob"
			method="post">
			<div>
				<labeL>Job Title: *</labeL><input type="text" name="name" value=""
					id="editName" required /> <input type="hidden" name="id"
					id="editId" value="" /> <input type="hidden" name="isActive"
					id="editIsActive" value="true" /> <br> <br>
				<button type="submit">Save</button>
				<button type="button" onClick="performDelete()">Delete</button>
				<button type="reset" id="cancelEditBtn" value="reset">Cancel</button>
			</div>
			<br>
			<div>Required Fields indicated with a *</div>
		</form>
	</fieldset>
</div>


<table id="t1">
	<tr>
		<!-- <sec:authorize access="hasRole('ROLE_ADMIN')"> -->
		<!-- <th>Task</th></sec:authorize> -->
		<th>Job Title</th>
		<!-- <sec:authorize access="hasRole('ROLE_ADMIN')">
				<td>delete</td>
			</sec:authorize> -->
	</tr>
</table>
