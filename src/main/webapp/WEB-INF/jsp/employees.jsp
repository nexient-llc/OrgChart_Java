<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
				<td><form:form id="delEmp${emp.id}" action="emps"
						method="delete">
						<input type="hidden" name="empId" value="${emp.id}" />
						<a href="#" onClick="$('#delEmp${emp.id}').submit();">delete</a>
					</form:form></td>
			</tr>
		</c:forEach>
	</table>

	<div id="addEmpBtn-container">
		<button type="button" id="addEmpBtn">Add</button>
		<button type="button" id="filterEmpBtn">Filter</button>
		<button type="button" id="resetEmpBtn" onClick="window.location = 'emps';">Reset</button>
		
	</div>

	<div id="filterEmpEntity" style="display: none">
		<fieldset>
			<legend>Filter Employee Table</legend>
			<form:form id="filterEmpForm" name="filterEmp" action="empsFilter"
				method="get">
				<div id="filterEmpFormInputs">
					<label>First Name:</label><input type="text" name="firstName"
						id="filterFirstName" /> <br> <label>Last Name:</label><input
						type="text" name="lastName" id="filterLastName" /> <br>
						<label>Department:</label><select
						name="department.departmentId" id="department.departmentId">
						<option value="">...</option>
						<c:forEach items="${depts}" var="dept">
							<option value="${dept.departmentId}">${dept.name}</option>
						</c:forEach>
					</select> <br> <label>Job Title:</label><select name="jobTitle.id"
						id="jobTitle.id">
						<option value="">...</option>
						<c:forEach items="${jobs}" var="job">
							<option value="${job.id}">${job.name}</option>
						</c:forEach>
					</select><br>

					<button type="submit" id="submitFilterEmpBtn">Search</button>
					<button type="button" id="cancelFilterEmpBtn">Cancel</button>
				</div>
			</form:form>
		</fieldset>
	</div>

	<div id="addEmpEntity" style="display: none">
		<fieldset>
			<legend id="empFormLegend">Add Employee</legend>
			<form:form id="newEmpForm" class="addEmp" name="newEmp" action="emps"
				method="post">
				<div id="empFormInputs">
					<label>First Name:</label><input type="text" name="firstName"
						id="firstName" />* <br> <label>Last Name:</label><input
						type="text" name="lastName" id="lastName" />* <br> <label>Email:</label><input
						type="text" name="email" id="email" />* <label>Skype
						Name:</label><input type="text" name="skypeName" id="skypeName" />* <br>
					<label>Is a Manager:</label><input type="checkbox" name="isManager"
						value="TRUE" id="isManager" /> <br> <label>Department:</label><select
						name="department.departmentId" id="department.departmentId">
						<option value="">...</option>
						<c:forEach items="${depts}" var="dept">
							<option value="${dept.departmentId}">${dept.name}</option>
						</c:forEach>
					</select>* <br> <label>Job Title:</label><select name="jobTitle.id"
						id="jobTitle.id">
						<option value="">...</option>
						<c:forEach items="${jobs}" var="job">
							<option value="${job.id}">${job.name}</option>
						</c:forEach>
					</select>*<br>

					<button type="submit" id="submitBtn">Save</button>
					<button type="button" id="cancelEmpBtn">Cancel</button>

				</div>
				<div style="padding-top: 5px">
					<footer>Required Fields indicated with a *</footer>
				</div>
			</form:form>
		</fieldset>
	</div>
</body>
</html>