<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<h3>
	Job Titles
	<div id="addBtn-container">
		<button type="button" id="addBtn" style="width: 45px;">Add</button>
	</div>
</h3>

<div id="addEntity" style="display: none">
	<fieldset>
		<legend>Add Job Title</legend>
		<form name="newJob" action="jobs" method="post">
			<div>
				<labeL>Job Name:</labeL> <input type="text" name="name" /> <label>*</label>
				<button type="submit">Save</button>
				<button type="reset">Clear</button>

				<footer>
					<br>Required fields marked with a *
				</footer>
			</div>

		</form>
	</fieldset>
</div>




<table id="t1">
	<tr>
		<th>Job Description</th>

	</tr>
	<c:forEach items="${jobtitles}" var="job">
		<tr id="ViewJobs${job.id}">
			<td>${job.name}</td>
			<td>
				<button type="button" class="editBtn" value="${job.id}">Edit</button>
			</td>
		</tr>

		<tr id="EditJobs${job.id}" style="display: none">
			<td><input path="name" id="jobName${job.id}" value="${job.name}"></td>

			<td><button class='saveBtn' value="${job.id}">Save</button></td>
			<td><button class='cancelBtn' value="${job.id}">Cancel</button></td>
		</tr>
	</c:forEach>
</table>

