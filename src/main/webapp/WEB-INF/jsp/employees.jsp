<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<link rel="stylesheet" type="text/css"
	href="<c:url value='/styles/employee.css"'/>
	media="
	screen" charset="utf-8" />
<h3>Employee Page</h3>
<div id="createdEmployeeContainer" style="display: none">
	<h2>Successfully created new department
		"${createdEmployee.fullName}"</h2>
</div>
<script type="text/javascript">
	var CREATED_EMPLOYEE = "${createdEmployee.fullName}";
	var page = 0;
	var secure = false;
</script>
<sec:authorize access="hasRole('ROLE_ADMIN')">
	<script>secure = true</script>
</sec:authorize>
<div id="filterBtn-container">
	<button type="button" id="filterBtn">Filter</button>
</div>
<div id="filterEntity" style="display: none">
	<fieldset>
		<legend>Filter Employees</legend>
		<form name="filterEmps" id="filterEmps">
			<label>Name:</label><input type="text" name="filterName"
				id="filterBox" /> <label>Department:</label> <select name="deptid"
				id="filterDept">
				<option value="">Search Departments</option>
				<c:forEach items="${depts}" var="dept">
					<option value="${dept.id}">${dept.name}</option>
				</c:forEach>
			</select> <label>Job Title:</label> <select name="jobid" id="filterJob">
				<option value="">Search Job Titles</option>
				<c:forEach items="${jobs}" var="job">
					<option value="${job.id}">${job.name}</option>
				</c:forEach>
			</select>
			<button type="button" onClick="get_table_data()">Search</button>
			<button type="button" onclick="reset_filter()">Reset</button>
		</form>
	</fieldset>
</div>
<div id="addBtn-container"
	<sec:authorize access="hasRole('ROLE_ADMIN')">style="display:block"</sec:authorize>
	style="display: none">
	<button type="button" id="addBtn" style="width: 45px;">Add</button>
</div>
<div id="addEntity" style="display: none">
	<fieldset>
		<legend>Add Employee</legend>
		<form name="newEmp" id="newEmployee" action="newEmp" method="post">
			<div>
				<label>First Name: *</label><input type="text" name="firstName"
					required /> <label>Last Name: *</label><input type="text"
					name="lastName" required /> <label>Middle Initial:</label><input
					type="text" name="middleInitial" maxlength="1" size="1" /> <label>Department:</label>
				<select name="department.id">
					<c:forEach items="${depts}" var="dept">
						<option value="${dept.id}">${dept.name}</option>
					</c:forEach>
				</select> <br> <br> <label>Email: *</label><input type="text"
					name="email" required /> <label>Skype Name: *</label><input
					type="text" name="skypeName" required /> <label>Job
					Titles:</label> <select name="jobTitle.id">
					<c:forEach items="${jobs}" var="job">
						<option value="${job.id}">${job.name}</option>
					</c:forEach>
				</select> <br> <br>
				<button type="submit">Save</button>
				<button type="reset" id="cancelBtn" value="reset">Cancel</button>
			</div>
			<br>
			<div>Required Fields indicated with a *</div>
		</form>
	</fieldset>
</div>
<div id="editContainer" style="display: none">
	<fieldset>
		<legend>Edit Employee</legend>
		<form name="editEmp" action="updateEmp" id="editEmp" method="post">
			<input type="hidden" name="id" id="editId" value="" /> <input
				type="hidden" id="activeVal" name="isActive" value="true" />
			<div>
				<label>First Name: *</label><input type="text" name="firstName"
					value="" id="editFirstName" required /> <label>Last Name:
					*</label><input type="text" name="lastName" value="" id="editLastName"
					required /> <label>Middle Initial:</label><input type="text"
					name="middleInitial" value="" maxlength="1" size="1"
					id="editMidInit" /> <label>Department:</label> <select
					name="department.id" id="editDept">
					<c:forEach items="${depts}" var="dept">
						<option value="${dept.id}">${dept.name}</option>
					</c:forEach>
				</select> <br> <br> <label>Email: *</label><input type="text"
					name="email" value="" id="editEmail" required /> <label>Skype
					Name: *</labeL><input type="text" name="skypeName" value="" id="editSkype"
					required /> <label>Job Titles:</label> <select name="jobTitle.id"
					name="department.id" id="editJob">
					<c:forEach items="${jobs}" var="job">
						<option value="${job.id}">${job.name}</option>
					</c:forEach>
				</select> <br> <br>
				<button type="submit">Save</button>
				<button type="button" onClick="performDelete()">Delete</button>
				<button type="reset" id="cancelEditBtn" value="reset">Cancel</button>
			</div>
			<br>
			<div>Required Fields indicated with a *</div>
		</form>
	</fieldset>
</div>
<br>
<ul class="paginationClass" id="pagination"></ul>
<table id="t1">
	<tr>
		<!-- <sec:authorize access="hasRole('ROLE_ADMIN')"> -->
		<!-- <th>Task</th></sec:authorize> -->
		<th>Employee Name</th>
		<th>Deptartment</th>
		<th>Job Title</th>
	</tr>
</table>