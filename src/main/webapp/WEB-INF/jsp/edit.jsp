<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<h3>Edit Employee</h3>
<div id="editEntity" >
	<fieldset>
		<form id="editEmp" name="editEmp" action="emps" method="post">
			<div>
				<input type="hidden" name="employeeId" value="${emp.employeeId}"/>
				<table id="editEmpTable">
					<tr>
						<td><label>First Name:</labeL></td>
						<td><input type="text" name="firstName" value="${emp.firstName}"/></td>
					</tr>
					<tr>
						<td><label>Last Name:</label></td>
						<td><input type="text" name="lastName" value="${emp.lastName}"/></td>
					</tr>
					<tr>
						<td><label>Department:</label></td>
						<td><select name="department_id">
							<option value="${emp.department.departmentId}">${emp.department.name}</option>
							<c:forEach items="${depts}" var="dept">
								<option value="${dept.departmentId}">${dept.name}</option>
							</c:forEach>
							</select></td>
					</tr>
					<tr>
						<td><label>Email:</label></td>
						<td><input type="text" name="email" value="${emp.email}"/></td>
					</tr>
					<tr>
						<td><label>Skype:</label></td>
						<td><input type="text" name="skypeName" value="${emp.skypeName}"/></td>
					</tr>
					<tr>
						<td><labeL>Job Title:</label></td>
						<td><select name="jobTitle_id">
								<option value="${emp.jobTitle.jobTitleId}">${emp.jobTitle.description}</option>
								<c:forEach items="${jobs}" var="title">
									<option value="${title.jobTitleId}">${title.description}</option>
								</c:forEach>
							</select></td>
					</tr>
					<tr>
						<td><label>Check if manager: </labeL></td>
						<td><input type="checkbox" name="isManager" <c:if test='${emp.isManager}'>checked</c:if> style="width: 190px"/></td>
					</tr>
					<tr>
						<td><button type="submit" id="saveBtn">Save</button></td>
						<td>Delete Employee: <input type="checkbox" id="deleteEmployee" name="deleteEmployee" style="width: 40px; "></td>
					</tr>
				</table>
			</div>
		</form>
	</fieldset>
</div>