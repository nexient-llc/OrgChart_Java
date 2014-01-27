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




<div class = "table">
	<div class = "row header">
		<span class = "cell jobName">
			<label>Job Description</label>
		</span>
		<span class = "cell editBtn"/>
	</div>
	<c:forEach items="${jobtitles}" var="job">
	<div class = "row" id="ViewJobs${job.id}">
			<div class="cell jobName">${job.name}</div>
			<div class="cell editBtn">
				<button type="button" class="editBtn" value="${job.id}">Edit</button>
			</div>
	</div>
	<div class = "row" id="EditJobs${job.id}" style="display: none">
			<div class="cell jobName"><input path="name" id="jobName${job.id}" value="${job.name}"></div>

			<div class="cell saveBtn"><button class='saveBtn' value="${job.id}">Save</button></div>
			<div class="cell cancelBtn"><button class='cancelBtn' value="${job.id}">Cancel</button></div>
	</div>
	</c:forEach>
</div>

