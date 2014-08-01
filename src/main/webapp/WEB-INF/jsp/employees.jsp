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
	<button class="pure-button" id="addBtn">Add</button>
	<button class="pure-button" id="toggleFilter">Filter</button>
</div>

<br>

<form action="emps" method="GET">
	<input type="hidden" value="0" name="pageNum">
	<button class="pure-button" id="showAll">Show All (unpaged)</button>
</form>

<div id="filterContainer" style="display: none; ">
	<br><br>
	<fieldset>
		<br>
		<legend>Filter employees</legend>
		<form class="pure-form pure-form-aligned" id="filterEmployees" name="filteredEmployee" action="emps" method="get">
			<div class="pure-control-group">
				<label for="autocompleteText">Name</label>
				<input id="autocompleteText" name="nameParam" />
			</div>
			
			<div class="pure-control-group">
				<label for="departmentSelect">Department</label>
				<select id="departmentSelect" name="departmentParam">
					<option value=""></option>
					<c:forEach items="${depts}" var="dept">
						<option value="${dept.id}">${dept.name}</option>
					</c:forEach>
				</select>
			</div>
			
			<div class="pure-control-group">
				<label for="jobTitleSelect">Job Title</label>
				<select id="jobTitleSelect" name="jobTitleParam">
					<option value=""></option>
					<c:forEach items="${titles}" var="title">
						<option value="${title.id}">${title.name}</option>
					</c:forEach>
				</select>
			</div>
			
			<button class="pure-button pure-button-primary" type="submit">Submit</button>
			<button class="pure-button" type="reset" id="cancelFilter">Cancel</button>
			
		</form>
		
		<br>
		<form name="inactiveEmployees" action="getInactiveEmployees" method="get">
			<button class="pure-button pure-button-primary" type="submit">Get Inactive Employees</button>
		</form>
	</fieldset>
</div>

<div id="addEntity" style="display:none">
	<form class="pure-form pure-form-aligned" id="newEmployeeForm" action="emps" method="post">
		<fieldset>
			<legend>Add Employee</legend>
			<div class="pure-control-group">
				<label for="addFirstName">*First Name</label>
				<input id="firstName" type="text" name="firstName" maxlength="20" required >
			</div>
			
			<div class="pure-control-group">
				<label for="addMiddleInitial">Middle Initial</label>
				<input id="addMiddleInitial" type="text" name="middleInitial" maxlength="1" value="" >
			</div>
			
			<div class="pure-control-group">
				<label for="addLastName">*Last Name</label>
				<input type="text" name="lastName" id="addLastName" maxlength="50" required >
			</div>
			
			<div class="pure-control-group">
				<label for="newEmail">*Email</label>
				<input id="newEmail" type="text" name="email" maxlength="100" required >
			</div>
			 
			<div class="pure-control-group">
				<label for="newSkypeName">*Skype Name</label>
				<input id="newSkypeName" type="text" name="skypeName" maxlength="100" required >
			</div>
			 
			<div class="pure-control-group">
				<label for="addDepartmentSelect">*Department</label>
				<select id="addDepartmentSelect" name="department.id">
					<option value=""></option>
					<c:forEach items="${depts}" var="dept">
						<option value="${dept.id}">${dept.name}</option>
					</c:forEach>
				</select>
			</div>
			 
			<div class="pure-control-group">
				<label for="addJobTitleSelect">*Job Title</label>
				<select id="addJobTitleSelect" name="jobTitle.id">
					<option value=""></option>
					<c:forEach items="${titles}" var="title">
						<option value="${title.id}">${title.name}</option>
					</c:forEach>
				</select>
			</div>
			
			<input type="hidden" name="isActive" id="addIsActive" value="true" />
			
			<br>
			
			<button class="pure-button pure-button-primary">Save</button> <button class="pure-button" id="cancelButton" type="reset" value="Reset">Cancel</button>
			
			<p>Fields indicated with a * are required</p>
		</fieldset>
	</form>
</div>

<br><br>

${(param.pageNum == 0) ? "<p>Click headers to sort</p>" : ""}
<button class="pure-button" onclick="page('previous')">Previous</button>
<span id="currentPage" style="padding-right: 20px; padding-left: 20px; ">1</span>
<button class="pure-button" onclick="page('next')">Next</button>

<br>

<table id="empTable" class="pure-table pure-table-horizontal sortable">
	<thead>
		<th>Name</th><th>Dept</th><th>Job Title</th><th>Edit</th><th>Delete</th>
	</thead>
	<tbody id="container">
		<c:forEach items="${emps}" var="employee">
			<tr id="empRow${employee.getId()}">
				<td>${employee.getFullName()}</td>
				<td>${employee.getDepartment().getName()}</td>
				<td>${employee.getJobTitle().getName()}</td>
				<td>
					<button class="pure-button" onclick="openEditForm('${employee.getId()}', '${employee.getFirstName()}', '${employee.getMiddleInitial()}', '${employee.getLastName()}', 
												  '${employee.getDepartment().getId()}', '${employee.getEmail()}', '${employee.getSkypeName()}', '${employee.getJobTitle().getId()}')">
					Edit</button>
				</td>
				<td>
					<c:choose>
						<c:when test="${employee.getIsActive() == true}">
							<button class="pure-button" onclick="removeEmployee('${employee.getId()}')">Delete</button>
						</c:when>
						<c:when test="${employee.getIsActive() == false}">
							<button class="pure-button" onclick="reenableEmployee('${employee.getId()}')">Enable</button>
						</c:when>
					</c:choose>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>

<br><br>

<div id="editContainer" style="display: none;">
	<form class="pure-form pure-form-aligned" id="editForm" action="emps" method="post">
		<fieldset>
			<legend>Edit Employee</legend>
			<input id="editEmpId" type="hidden" name="id" />
			<input type="hidden" name="isActive" value="true" />
			
			<div class="pure-control-group">
				<label for="editFirstName">*First Name</label>
				<input id="editFirstName" type="text" name="firstName" required >
			</div>
			
			<div class="pure-control-group">
				<label for="editMiddleInitial">Middle Initial</label>
				<input id="editMiddleInitial" type="text" name="middleInitial" maxlength="1" >
			</div>
			
			<div class="pure-control-group">
				<label for="editLastName">*Last Name</label>
				<input id="editLastName"type="text" name="lastName" required >
			</div>
			
			<div class="pure-control-group">
				<label for="editEmail">*Email</label>
				<input id="editEmail" type="text" name="email" required >
			</div>
			
			<div class="pure-control-group">
				<label for="editSkype">*Skype Name</label>
				<input id="editSkype" type="text" name="skypeName" required >
			</div>
			
			<div class="pure-control-group">
				<label for="editDepartmentSelect">*Department</label>
				<select id="editDepartmentSelect" name="department.id">
					<option value=""></option>		
					<c:forEach items="${depts}" var="dept">
						<option value="${dept.id}">${dept.name}</option>
					</c:forEach>
				</select>
			</div>
			
			<div class="pure-control-group">
				<label for="editJobTitleSelect">*Job Title</label>
				<select id="editJobTitleSelect" name="jobTitle.id">
					<option value=""></option>
					<c:forEach items="${titles}" var="title">
						<option value="${title.id}">${title.getName()}</option>
					</c:forEach>
				</select>
			</div>
			
			<br>
			
			<button class="pure-button pure-button-primary" type="submit">Save</button>
		</fieldset>	
	</form>
</div>