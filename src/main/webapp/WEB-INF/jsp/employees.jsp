<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>

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
		
	</fieldset>
</div>

<div id="addEntity" style="display:none">
	<fieldset>
		<legend>Add Employee</legend>
		<form id="newEmployeeForm" name="newEmployee" action="emps" method="post">
			*First Name: <input type="text" name="firstName" required />
			Middle Initial: <input type="text" name="middleInitial" maxlength="1" style="width: 50px; " />
			*Last Name: <input type="text" name="lastName" required />
			*Department: 
			<select id="addDepartmentSelect" name="department.id">
				<option value=""></option>
				<c:forEach items="${depts}" var="dept">
					<option value="${dept.id}">${dept.name}</option>
				</c:forEach>
			</select>
			
			<br><br>
			
			*Email: <input type="text" name="email" required />
			*Skype Name: <input type="text" name="skypeName" required />
			*Job Title: 
			<select id="addJobTitleSelect" name="jobTitle.id">
				<option value=""></option>
				<c:forEach items="${titles}" var="title">
					<option value="${title.id}">${title.name}</option>
				</c:forEach>
			</select>
			
			<input type="hidden" name="isActive" value="true" />
			
			<br><br>
			
			<button type="submit">Save</button> <button id="cancelButton" type="reset" value="Reset">Cancel</button>
			
			<p>Fields indicated with a * are required</p>
		</form>
	</fieldset>
</div>

<br/><br/>

<table class="sortable">
	<thead>
		<th>Name</th><th>Dept</th><th>Job Title</th><th>Edit</th><th>Delete</th>
	</thead>
	<c:forEach items="${emps}" var="employee">
		<tr id="empRow${employee.getId()}">
			<td>${employee.getFullName()}</td>
			<td>${employee.getDepartment().getName()}</td>
			<td>${employee.getJobTitle().getName()}</td>
			<td>
				<button onclick="openEditForm('${employee.getId()}')">Edit</button>
				<div id="container-${employee.getId()}" style="display: none; ">
					<fieldset>
						<h2>${employee.getFullName()}</h2>
						<form id="form-${employee.getId()}" action="emps" method="post">
							*First Name: <input type="text" name="firstName" value="${employee.getFirstName()}" required />
							Middle Initial: <input type="text" name="middleInitial" value="${employee.getMiddleInitial()}" maxlength="1" style="width: 50px; " />
							*Last Name: <input type="text" name="lastName" value="${employee.getLastName()}" required />
							*Department: 
							<select id="editDepartmentSelect" name="department.id">
								<option value=""></option>
								
								<c:forEach items="${depts}" var="dept">
									<c:choose>
										<c:when test="${employee.getDepartment().id == dept.id}">
											<option value="${dept.id}" selected>${dept.name}</option>
										</c:when>
										<c:otherwise>
											<option value="${dept.id}">${dept.name}</option>
										</c:otherwise>
									</c:choose>
									
								</c:forEach>
							</select>
							
							<br><br>
							
							*Email: <input type="text" name="email" value="${employee.getEmail()}" required />
							*Skype Name: <input type="text" name="skypeName" value="${employee.getSkypeName()}" required />
							*Job Title: 
							<select id="editJobTitleSelect" name="jobTitle.id">
								<option value=""></option>
								<c:forEach items="${titles}" var="title">
									<c:choose>
										<c:when test="${employee.getJobTitle().id == title.id}">
											<option value="${title.id}" selected>${title.getName()}</option>
										</c:when>
										<c:otherwise>
											<option value="${title.id}">${title.getName()}</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</select>
							
							<input type="hidden" name="id" value="${employee.getId()}" />
							<input type="hidden" name="isActive" value="true" />
							
							<button type="submit">Save</button>
							
						</form>
					</fieldset>
				</div>
				
			</td>
			<td>
				<button onclick="removeEmployee('${employee.getId()}')">Delete</button>
			</td>
		</tr>
	</c:forEach>
</table>