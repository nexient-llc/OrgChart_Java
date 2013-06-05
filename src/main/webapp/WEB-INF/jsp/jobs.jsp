<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
				<td class="tableData">${job.name}</td>
				<td><button type="button" class="editJob" value="${job.id}">Edit</button></td>
			</tr>
		</c:forEach>

	</table>
	<div id="addJobsBtn-container">
		<button type="button" id="addJobBtn" style="width: 45px;">Add</button>
	</div>
	<div id="addJobsEntity" style="display: none">
		<fieldset>
			<legend id="jobsFormLegend">Add Job Title</legend>
			<form:form id="newJobForm" class="addJob" name="newJob" action="jobs" method="post">
				<div id="jobsFormInputs">
					<labeL>Job Title:</labeL><input type="text" name="name" id="name"/><br>
					<button type="submit" id="submitBtn">Save</button>
					<button type="button" id="cancelJobsBtn">Cancel</button>
				</div>
				<div></div>
			</form:form>
		</fieldset>
	</div>

</body>
</html>