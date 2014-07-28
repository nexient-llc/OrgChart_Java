<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<h3>Job Titles Page</h3>
<div id="addBtn-container">
	<button type="button" id="addBtn" style="width: 45px;">Add</button>
</div>
<div id="addEntity" style="display: none">
	<fieldset>
		<legend>Add New Job Title</legend>
		<form name="newJob" id="newJobTitle" action="newJob" method="post">
			<div>
				<labeL>Job Title: *</labeL><input type="text" name="name" id="newJobName" required />
				<input type="hidden" name="isActive" value="true">
				<br><br>
				<button type="submit">Save</button>
				<button type="reset" id="cancelBtn" value="reset">Cancel</button>
			</div>
			<br><div>Required Fields indicated with a *</div>
		</form>
	</fieldset>
</div>


<table id="t1">
	<tr>
		<!-- <sec:authorize access="hasRole('ROLE_ADMIN')"> -->
		<!-- <th>Task</th></sec:authorize> -->
		<th>Job Title</th>
	</tr>
	<c:forEach items="${jobs}" var="job">
		<tr id="tableRow-${job.id}">
			<!-- <sec:authorize access="hasRole('ROLE_ADMIN')">
				<td>delete</td>
			</sec:authorize> -->
			<td>${job.name}</td>
			<td><div class="editBtn-containerClass"
					id="editBtn-container-${job.id}">
					<button type="button" class="editBtnClass"
						id="editBtn-${job.id}">Edit</button>
				</div></td>
			<td><div id="editEntity-${job.id}" style="display: none">
					<fieldset>
						<legend>Edit Job Title</legend>
						<form name="editJob" class="editFormClass" id="editForm-${job.id}" action="updateJob" method="post">
							<div>
								<labeL>Dept Name: *</labeL><input type="text" name="name" value="${job.name}" id="editName-${job.id}" required />
								<input type="hidden" name="id" value="${job.id}" />
								<input type="hidden" id="activeVal-${job.id}" name="isActive" value="true" />
								<br><br>
								<button type="submit">Save</button>
								<button type="button" class="deleteBtnClass" id="deleteBtn-${job.id}">Delete</button>
								<button type="reset" class="cancelEditBtnClass" id="cancelEditBtn-${job.id}" value="reset">Cancel</button>
							</div>
							<br><div>Required Fields indicated with a *</div>
						</form>
					</fieldset>
				</div></td>
		</tr>
	</c:forEach>
</table>
