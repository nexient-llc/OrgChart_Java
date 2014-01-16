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




<table id="t1">
	<tr>
		<th>Job Description</th>

	</tr>
	<c:forEach items="${jobtitles}" var="job">
		<tr>
			<div id="editBtn-container${job.id}">
				<td>${job.name}</td>
			</div>
		</tr>
	</c:forEach>
</table>

<div id="addEntity" style="display: none">
	<fieldset>
		<legend>Add Job Title</legend>
		<form name="newJob" action="jobs" method="post">
			<div>
				<labeL>Job Name:</labeL> <input type="text" name="name" /> <label>*</label>
				<button type="submit">Save</button>
				<button type="reset">Clear</button>
				
				<footer><br>Required fields marked with a *</footer>
			</div>

		</form>
	</fieldset>
</div>
