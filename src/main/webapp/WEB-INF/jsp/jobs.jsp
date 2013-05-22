<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Job Titles</title>
</head>
<body>
	<h3>Job Titles</h3>
	<table id="jobsTable">
		<tr>
			<th>Name</th>
		</tr>
		<c:forEach items="${jobs}" var="job">
			<tr>
				<td>${job.name}</td>
				<td><button type="button" class="editBtn" name="editJobs"
						value="${job.id}">Edit</button></td>
				<td><form name="dJob" action="deleteJob" method="post">
						<button type="submit" value="${job.id}">X</button></td>
				</form>
			</tr>
		</c:forEach>

	</table>
	<div id="addBtn-container">
		<button type="button" id="addBtn" style="width: 45px;">Add</button>
	</div>
	<div id="addEntity" style="display: none">
		<fieldset>
			<legend>Add Job Title</legend>
			<form name="newJob" action="jobs" method="post">
				<div>
					<labeL>Job Title:</labeL><input type="text" name="name" /></br>
					<button type="submit" id="submitBtn">Save</button>
					<button type="button" id="cancelBtn">Cancel</button>
				</div>
				<div></div>
			</form>
		</fieldset>
	</div>
	<div id="updateEntity" style="display: none">
		<fieldset>
			<legend>Add Job Title</legend>
			<form name="updateJob" action="updateJobs" method="post">
				<div>
					<labeL>Job Title:</labeL><input type="text" name="name" /></br>
					<button type="submit" id="submitBtn">Save</button>
					<button type="button" id="cancelBtn">Cancel</button>
				</div>
				<div></div>
			</form>
		</fieldset>
	</div>
</body>
</html>