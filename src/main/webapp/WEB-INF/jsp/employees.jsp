<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>

<div id="empDisplay">

	<h3>Employees</h3>
	
	<table id="t1" cellpadding="4px"> 
		<tr> 
			<th>Employee Name</th>
			<th>Department</th>
			<th>Job Title</th>
			<th></th>
		</tr> 
		<c:forEach items="${emps}" var="emp">
			<tr name="${emp.empID}">
				<td>
					${emp.firstName} ${emp.lastName}
				</td> 
				
				<td>${emp.dept.name}</td> 
				
				<td>${emp.jobTitle.desc}</td>
				
				<td>
					<button id="${emp.empID}/edit" class="editLink">edit</button>
					<button id="${emp.empID}/delete" class="deleteLink">delete</button>
				</td>
			</tr>
		</c:forEach> 
	</table>

	<form id="editEmpForm" action="edit" method="post">
		<input type="hidden" id="hiddenEditEmpID" name="hiddenEditEmpID" />
	</form>
	
	<form id="deleteEmpForm" action="basicDelete" method="post">
		<input type="hidden" id="hiddenEmpID2" name="hiddenEmpID2" value="test" />
	</form>

	<div id="addBtn-container">
		<button type="button" id="addBtn" style="width: 45px;">Add</button>	
	</div>

</div>

<div id="addChangeEntity">
	
	<c:choose>
	<c:when test="${empty selectedEmp}">
	<h3>Add New Employee</h3>

	<fieldset id="empFields">
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
						<td><select name="departmentId">
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
						<td><select name="jobTitleID">
								<option>...</option>
								<c:forEach items="${jobs}" var="title">
									<option value="${title.jobTitleID}">${title.desc}</option>
								</c:forEach>
							</select></td>
					</tr>
					<tr>
						<td><label>Manager: </labeL></td>
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
	</c:when>
	
	<c:otherwise>
		<script type="text/javascript">
			$(document).ready(
					$('#addChangeEntity').toggle("slow", 
							function()
							{ 
								$('#empDisplay').css('border-width','1px');
								$('#empDisplay').css('border-style', 'none dashed none none');
								$('#empDisplay').css('border-color', '#cfcece');
								$('#empDisplay').css('height', $('#pageBody').height());
							}
					).css('display', 'inline-block')
			);
			
		</script>
		HELLO WORLD
	</c:otherwise>
	</c:choose>
</div>