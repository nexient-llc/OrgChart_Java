<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>

<style type="text/css">
	.ui-menu {
		background: white;
		margin: auto;
		padding: 0;
		width: 150px;
		list-style-type: none;
		border: 1px solid;
	}
</style>

<h3>Employees</h3>

<div id="addBtn-container">
	<button type="button" id="addBtn">Add</button>
	<button id="toggleFilter">Filter</button>
</div>

<div id="filterContainer" style="display: none; ">
	<br><br>
	<fieldset>
		<br>
		<legend>Filter employees</legend>
		<form id="filterEmployees" name="filteredEmployee" action="emps" method="get">
			Name: <input id="autocompleteText" name="nameParam" />
			Department: <select id="departmentSelect" name="departmentParam">
				<option value=""></option>
				<c:forEach items="${depts}" var="dept">
					<option value="${dept.id}">${dept.name}</option>
				</c:forEach>
			</select>
			Job Title: <select id="jobTitleSelect" name="jobTitleParam">
				<option value=""></option>
				<c:forEach items="${titles}" var="title">
					<option value="${title.id}">${title.name}</option>
				</c:forEach>
			</select>
			<button type="submit">Submit</button>
			<button type="reset" id="cancelFilter">Cancel</button>
		</form>
		<br>
		<form name="inactiveEmployees" action="getInactiveEmployees" method="get">
			<button type="submit">Get Inactive Employees</button>
		</form>
	</fieldset>
</div>

<div id="addEntity" style="display:none">
	<fieldset>
		<legend>Add Employee</legend>
		<form id="newEmployeeForm">
			*First Name: <input type="text" name="firstName" id="addFirstName" required />
			Middle Initial: <input type="text" name="middleInitial" id="addMiddleInitial" maxlength="1" style="width: 50px; "  value="" />
			*Last Name: <input type="text" name="lastName" id="addLastName" required />
			*Department: 
			<select id="addDepartmentSelect" name="department.id">
				<option value=""></option>
				<c:forEach items="${depts}" var="dept">
					<option value="${dept.id}">${dept.name}</option>
				</c:forEach>
			</select>
			
			<br><br>
			
			*Email: <input type="text" name="email" id="newEmail" required />
			*Skype Name: <input type="text" name="skypeName" id="newSkypeName" required />
			*Job Title: 
			<select id="addJobTitleSelect" name="jobTitle.id">
				<option value=""></option>
				<c:forEach items="${titles}" var="title">
					<option value="${title.id}">${title.name}</option>
				</c:forEach>
			</select>
			
			<input type="hidden" name="isActive" id="addIsActive" value="true" />
			
			<br><br>
			
			<button>Save</button> <button id="cancelButton" type="reset" value="Reset">Cancel</button>
			
			<p>Fields indicated with a * are required</p>
		</form>
	</fieldset>
</div>

<br/><br/>

<table class="sortable" id="empTable">
	<thead>
		<th>Name</th><th>Dept</th><th>Job Title</th><th>Edit</th><th>Delete</th>
	</thead>
	<c:forEach items="${emps}" var="employee">
		<tr id="empRow${employee.getId()}">
			<td>${employee.getFullName()}</td>
			<td>${employee.getDepartment().getName()}</td>
			<td>${employee.getJobTitle().getName()}</td>
			<td>
				<button onclick="openEditForm('${employee.getId()}', '${employee.getFirstName()}', '${employee.getMiddleInitial()}', '${employee.getLastName()}', 
											  '${employee.getDepartment().getId()}', '${employee.getEmail()}', '${employee.getSkypeName()}', '${employee.getJobTitle().getId()}')">
				Edit</button>
			</td>
			<td>
				<c:choose>
					<c:when test="${employee.getIsActive() == true}">
						<button onclick="removeEmployee('${employee.getId()}')">Delete</button>
					</c:when>
					<c:when test="${employee.getIsActive() == false}">
						<button onclick="reenableEmployee('${employee.getId()}')">Enable</button>
					</c:when>
				</c:choose>
			</td>
		</tr>
	</c:forEach>
</table>

<div id="editContainer" style="display: none;">
	<fieldset>
		<h2></h2>
		<form id="editForm" action="emps" method="post">
			<input id="editEmpId" type="hidden" name="id" />
			<input type="hidden" name="isActive" value="true" />
			*First Name: <input id="editFirstName" type="text" name="firstName" required />
			Middle Initial: <input id="editMiddleInitial" type="text" name="middleInitial" maxlength="1" style="width: 50px; "/>
			*Last Name: <input id="editLastName"type="text" name="lastName" required />
			*Department: 
			<select id="editDepartmentSelect" name="department.id">
				<option value=""></option>		
				<c:forEach items="${depts}" var="dept">
					<option value="${dept.id}">${dept.name}</option>
				</c:forEach>
			</select>
			<br><br>
			*Email: <input id="editEmail" type="text" name="email" required />
			*Skype Name: <input id="editSkype" type="text" name="skypeName" required />
			*Job Title: 
			<select id="editJobTitleSelect" name="jobTitle.id">
				<option value=""></option>
				<c:forEach items="${titles}" var="title">
					<option value="${title.id}">${title.getName()}</option>
				</c:forEach>
			</select>
			<button type="submit">Save</button>
		</form>
	</fieldset>
</div>