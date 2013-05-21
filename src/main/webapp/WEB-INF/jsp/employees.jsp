<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
			<th>Email</th>
			<th>Skype Name</th>
			<th>Manager</th>
			<th>Department</th>
			<th>Job Title</th>
		</tr>
		<c:forEach items="${emps}" var="emp">
			<tr>
				<td>${emp.firstName}</td>
				<td>${emp.lastName}</td>
				<td>${emp.email}</td>
				<td>${emp.skypeName}</td>
				<td>${emp.isManager}</td>
				<td>${emp.departmentId}</td>
				<td>${emp.jobTitleId}</td>
			</tr>
		</c:forEach>
	</table>
	
	<div id="addBtn-container">
		<button type="button" id="addBtn" style="width: 45px;">Add</button>
	</div>
	
	<div id="addEntity" style="display: none">
		<fieldset>
			<legend>Add Employee</legend>
			<form name="newEmp" action="emps" method="post">
				<div>
					<label>First Name:</label><input type="text" name="firstName" />
					<label>Last Name:</label><input type="text" name="lastName" />  <br>
					<label>Email:</label><input type="text" name="email" /> 
					<label>Skype Name:</label><input type="text" name="skypeName" />  <br>
					<label>Is a Manager:</label><input type="checkbox" name="isManager" value="TRUE"> </br>
					<label>Job Title:</label><select name="parentDepartment.departmentId">
					<option value="">...</option>
					<c:forEach items="${depts}" var="dept">
						<option value="${dept.departmentId}">${dept.name}</option>
					</c:forEach>
				</select>
				<label>Department:</label><select name="parentDepartment.departmentId">
					<option value="">...</option>
					<c:forEach items="${depts}" var="dept">
						<option value="${dept.departmentId}">${dept.name}</option>
					</c:forEach>
				</select></br>
					
					<button type="submit">Save</button>
				</div>
				<div></div>
			</form>
		</fieldset>
	</div>

</body>
</html>