<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Employees</title>
</head>
<body>
	<h3>Employees</h3>
	<table id="empTable">
		<tr>
			<th>First Name</th>
			<th>Last Name</th>
			<th>Department</th>
			<th>Job Title</th>
		</tr>
		<c:forEach items="${emps}" var="emp">
			<tr>
				<td class="tableData">${emp.firstName}</td>
				<td class="tableData">${emp.lastName}</td>
				<td class="tableData">${emp.department.name}</td>
				<td class="tableData">${emp.jobTitle.name}</td>
				<td><button type="button" class="editEmp" value="${emp.id}">Edit</button>
				</td>
				<td><form:form id="delEmp" action="emps" method="delete">
						<input type="hidden" name="empId" value="${emp.id}"/>
						<a href="#" onClick="$('#delEmp').submit();">delete</a>
					</form:form></td>
			</tr>
		</c:forEach>
	</table>

	<div id="addBtn-container">
		<button type="button" id="addBtn" style="width: 45px;">Add</button>
	</div>

	<div id="addEntity" style="display: none">
		<fieldset>
			<legend>Add Employee</legend>
			<form:form name="newEmp" action="emps" method="post">
				<div>
					<label>First Name:</label><input type="text" name="firstName" /> <label>Last
						Name:</label><input type="text" name="lastName" /> <br> <label>Email:</label><input
						type="text" name="email" /> <label>Skype Name:</label><input
						type="text" name="skypeName" /> <br> <label>Is a
						Manager:</label><input type="checkbox" name="isManager" value="TRUE"
						id="isManager"> </br> <label>Department:</label><select
						name="department.departmentId">
						<option value="">...</option>
						<c:forEach items="${depts}" var="dept">
							<option value="${dept.departmentId}">${dept.name}</option>
						</c:forEach>
					</select> </br> <label>Job Title:</label><select name="jobTitle.id">
						<option value="">...</option>
						<c:forEach items="${jobs}" var="job">
							<option value="${job.id}">${job.name}</option>
						</c:forEach>
					</select></br>

					<button type="submit" id="submitBtn">Save</button>
					<button type="button" id="cancelBtn">Cancel</button>

				</div>
				<div></div>
			</form:form>
		</fieldset>
	</div>

	<div id="editEntity" style="display: none">
		<fieldset>
			<legend>Edit Employee</legend>
			<form:form name="editEmp" action="emps" method="put">
				<div>
					<input type="hidden" name="id" id="empId" /><label>First
						Name:</label><input type="text" name="firstName" id="firstName" /> <label>Last
						Name:</label><input type="text" name="lastName" id="lastName" /> <br>
					<label>Email:</label><input type="text" name="email" id="email" />
					<label>Skype Name:</label><input type="text" name="skypeName"
						id="skypeName" /> <br> <label>Is a Manager:</label><input
						type="checkbox" name="isManager" value="TRUE" id="isManager">
					</br> <label>Department:</label><select name="department.departmentId"
						id="department">
						<option value="">...</option>
						<c:forEach items="${depts}" var="dept">
							<option value="${dept.departmentId}">${dept.name}</option>
						</c:forEach>
					</select> </br> <label>Job Title:</label><select name="jobTitle.id" id="jobTitle">
						<option value="">...</option>
						<c:forEach items="${jobs}" var="job">
							<option value="${job.id}">${job.name}</option>
						</c:forEach>
					</select></br>

					<button type="submit" class="submitEditBtn">Save</button>

					<button type="button" id="cancelEditBtn">Cancel</button>

				</div>
				<div></div>
			</form:form>
		</fieldset>
	</div>
</body>
</html>