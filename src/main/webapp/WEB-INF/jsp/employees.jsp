<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<h3>Employees</h3>
<table id="t1">
	<tr>
		<!-- <sec:authorize access="hasRole('ROLE_ADMIN')"> -->
		<!-- <th>Task</th></sec:authorize> -->
		<th>Employee Id</th>
		<th>First Name</th>
		<th>Last Name</th>
		<th>Middle Initial</th>
		<th>Email Address</th>
		<th>Skype Name</th>
		<th>Is Manager</th>
		<th>Job Title</th>
		<th>Department</th>
		<th>Edit</th>
		<th>Delete</th>
		
		
		
	</tr>
	<c:forEach items="${employees}" var="employee">
		<tr id =${employee.id }>
			<!-- <sec:authorize access="hasRole('ROLE_ADMIN')">
				<td>delete</td>
			</sec:authorize> -->
			<td>${employee.id }</td>
			<td>${employee.firstName}</td>
			<td>${employee.lastName}</td>
			<td>${employee.middleInitial}</td>
			<td>${employee.email}</td>
			<td>${employee.skypeName}</td>
			<td>${employee.isManager}</td>
			<td class="${employee.jobTitle.id}">${employee.jobTitle.name}</td>
			<td class="${employee.department.id}">${employee.department.name}</td>
			<td><button type="button" class="editBtn" style="width: 45px;">Edit</button>	</td>
			<td><form name="removeEmployee" action="removeEmployee" method="post">
					<input type="hidden" name=employeeId value= "${employee.id}"/>
					<button type="submit" id="removeBtn">Remove</button>
				</form>
			</td>
		</tr>
	</c:forEach>
</table>

<div id="addBtn-container">
	<button type="button" id="addBtn" style="width: 45px;">Add</button>
</div>

<div id="addEntity" style="display: none">
	<fieldset>
		<legend>Add Employee</legend>
		<form name="newEmployee" action="employees" method="post">
			<div>
				<label>*Employee First Name:</labeL><input type="text" name="firstName" /> 
				<label>*Employee Last name:</label><input type="text" name="lastName"/>
				<label>Employee Middle Initials:</label><input type="text" name="middleInitial" size="1"/>
				<br />
				<label>*Employee Email Address:</label><input type="text" name="email"/>
				<label>*Employee Skype: </label><input type = "text" name="skype"/>
				<label>Is Manager: </label>
				
					<select name="isManager">
						<option value="true">true</option>
						<option value="false">false</option>
					</select>
				<br />
				<label>*Department</label> <select name="departmentId">
					<c:forEach items="${depts}" var="dept">
						<option value="${dept.id}">${dept.name}</option>
					</c:forEach>
				</select>
				<label>*Job Title</label><select name="jobTitleId">
					<c:forEach items="${jobTitles}" var="jobTitle">
						<option value="${jobTitle.id}">${jobTitle.name}</option>
					</c:forEach>
				</select>
				<label><code>* required fields</code></label>
				
				<button type="submit">Save</button>
				
				
			</div>
			<div></div>
		</form>
		<button type="button" class ="cancelBtn">Cancel</button>
	</fieldset>
</div>
<div id="editEntity" style="display: none">
	<fieldset>
		<legend>Edit Employee</legend>
		<form name="editEmployee" action="editEmployee" method="post">
			<div>
				<input id ="editEmployeeId" type="text" name="employeeId"/>
				<label>Employee First Name:</labeL><input id="editFirstName" type="text" name="firstName" /> 
				<label>Employee Last name:</label><input id="editLastName" type="text" name="lastName"/>
				<label>Employee Middle Initials:</label><input id="editMiddleInitial" type="text" name="middleInitial" size="1"/>
				<br />
				<label>Employee Email Address:</label><input id="editEmail" type="text" name="email"/>
				<label>Employee Skype: </label><input id= "editSkype" type = "text" name="skype"/>
				<label>Is Manager: </label>
					<select id="editIsManager" name="isManager">
						<option value="true">true</option>
						<option value="false">false</option>
					</select>
				<br />
				<label>Department</label> 
					<select id="editDepartment" name="departmentId">
						<c:forEach items="${depts}" var="dept">
							<option value="${dept.id}">${dept.name}</option>
						</c:forEach>
					</select>
				<label>Job Title</label>
					<select id= editJobTitle name="jobTitleId">
						<c:forEach items="${jobTitles}" var="jobTitle">
							<option value="${jobTitle.id}">${jobTitle.name}</option>
						</c:forEach>
					</select>
					
				<button type="submit">Save</button>
				
				
			</div>
			<div></div>
		</form>
		<button type="button" class ="cancelBtn">Cancel</button>
	</fieldset>
</div>