<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<h3>Employees</h3>
<table id="t1"> 
	<tr> 
		<th>Task</th>
		<th>Employee Name</th>
		<th>Department</th>
	</tr> 
	<c:forEach items="${emps}" var="emp">
		<tr id="${emp.employeeId}"> 
			<td><button type="button" id="editBtn" style="width: 45px;">edit</button></td>
			<td>${emp.firstName} ${emp.lastName}</td> 
			<td>${emp.department.name}</td> </tr>
	</c:forEach> 
</table>

<div id="addBtn-container">
		<button type="button" id="addBtn" style="width: 45px;">Add</button>	
</div>

<div id="addChangeEntity" style="display:none">
	<fieldset>
		<legend id="addChange-legend">Add Employee</legend>
		<form name="newEmp" action="emps" method="post">
			<div>
				<table id="newEmpTable">
					<tr>
						<td><label>First Name:</labeL></td>
						<td><input type="text" name="firstName"/></td>
					</tr>
					<tr>
						<td><label>Last Name:</label></td>
						<td><input type="text" name="lastName"/></td>
					</tr>
					<tr>
						<td><label>Department:</label></td>
						<td><select name="department_id">
							<option>...</option>
							<c:forEach items="${depts}" var="dept">
								<option value="${dept.departmentId}">${dept.name}</option>
							</c:forEach>
							</select></td>
					</tr>
					<tr>
						<td><label>Email:</label></td>
						<td><input type="text" name="email"/></td>
					</tr>
					<tr>
						<td><label>Skype:</label></td>
						<td><input type="text" name="skypeName"/></td>
					</tr>
					<tr>
						<td><labeL>Job Title:</label></td>
						<td><select name="jobTitle_id">
								<option>...</option>
								<c:forEach items="${jobs}" var="title">
									<option value="${title.jobTitleId}">${title.description}</option>
								</c:forEach>
							</select></td>
					</tr>
					<tr>
						<td><label>Check if manager: </labeL></td>
						<td><input type="checkbox" name="isManager"/></td>
					</tr>
					<tr>
						<td><button type="submit">Save</button></td>
						<td></td>
					</tr>
				</table>
			</div>
		</form>
	</fieldset>
</div>