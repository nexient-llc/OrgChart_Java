
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<link rel="stylesheet" href="//code.jquery.com/ui/1.11.2/themes/smoothness/jquery-ui.css">

<header>Systems In Motion Organization Chart: Employees</header>

<h3>Manage Employee</h3>

<div id="filterBtn-container">
	<table>
		<tr>
			<td>
				<button type="button" id="filterBtn">Filter</button>
			</td>
			<td>
				<div id="filter-container" style="display: none">
					<form:form id="filterForm" name="filterEmp" action="empsFilter"
						method="get">
						<label>Name(First or Last):</label>
						<input type="text" name="firstName" id="filterName">
						<labeL>Departments:</label>
						<select name="department.id" id="filterEmpDepartmentId" >
							<option value="">...</option>
							<c:forEach items="${depts}" var="dept">
								<option value="${dept.id}">${dept.name}</option>
							</c:forEach>
						</select>
						<label id="jobTitleLabel">Job Title:</label>
						<select name="jobTitle.id" id="filterEmpJobTitleId">
							<option value="">...</option>
							<c:forEach items="${titles}" var="title">
								<option value="${title.id}">${title.name}</option>
							</c:forEach>
						</select>
						<button type="submit">Apply</button>
						<button type="reset" value="reset" id="cancelFilterBtn">Cancel</button>
					</form:form>
				</div>
			</td>
		</tr>
	</table>
</div>

<br>

<sec:authorize access="hasRole('ROLE_ADMIN')">
<div id="addBtn-container">
	<button type="button" id="addBtn" style="width: 45px;">Add</button>
</div>

<div id="addEntity-container" style="display: none">
	<fieldset>
		<legend>Add New Employee</legend>
		<form name="newEmp" action="emps" method="post">
			<div>
				<label>*First Name:</label> <input type="text" name="firstName"
					required /> <label>*Last Name:</label> <input type="text"
					name="lastName" required /> <label>Middle Initial:</label> <input
					type="text" name="middleInitial" /> <label>*Departments:</label> <select
					name="department.id">
					<c:forEach items="${depts}" var="dept">
						<option value="${dept.id}">${dept.name}</option>
					</c:forEach>
				</select><br> <label>*Email:</label> <input type="text" name="email"
					required /> <label>*Skype Name:</label> <input type="text"
					name="skypeName" required /> <label>*Job Title:</label> <select
					name="jobTitle.id">
					<c:forEach items="${titles}" var="title">
						<option value="${title.id}">${title.name}</option>
					</c:forEach>
				</select>
				<button type="submit">Save</button>
				<button type="reset" value="reset" id="cancelAddButton">Cancel</button>
				<br> Required Fields indicated with a *
			</div>
			<div></div>
		</form>
	</fieldset>
</div>
</sec:authorize>

<div id="employeesTable-container">
	<table>
		<tr>
			<th>Employee Name</th>
			<th>Department Name</th>
			<th>Job Title</th>
			<th></th>
		</tr>
		<c:forEach items="${emps}" var="emp">
			<tr id="tableRow${emp.id}">
				<td>${emp}</td>
				<td>${emp.department.name}</td>
				<td>${emp.jobTitle.name}</td>
				<sec:authorize access="hasRole('ROLE_ADMIN')">
				<td><button class="editButton" value="${emp.id}">Edit</button></td>
				</sec:authorize>
			</tr>
		</c:forEach>
	</table>
</div>
<div id="editEntity-container" style="display: none">
	<fieldset>
		<legend>Edit Employee</legend>
		<form:form name="editEmp" action="emps" method="put">
			<input type="hidden" id="employeeId" name="id" />
			<div>
				<label>*First Name:</label><input type="text" name="firstName"
					id="empFirstName" required /> <labeL>*Last Name:</labeL><input
					type="text" name="lastName" id="empLastName" required /> <labeL>Middle
					Initial:</labeL><input type="text" name="middleInitial"
					id="empMiddleInitial" /> <labeL>*Departments:</label> <select
					name="department.id" id="empDepartmentId">
					<c:forEach items="${depts}" var="dept">
						<option value="${dept.id}">${dept.name}</option>
					</c:forEach>
				</select><br> <labeL>*Email:</labeL><input type="text" name="email"
					id="empEmail" required /> <labeL>*Skype Name:</labeL><input
					type="text" name="skypeName" id="empSkypeName" required /> <labeL>*Job
					Title:</label> <select name="jobTitle.id" id="empJobTitleId">
					<c:forEach items="${titles}" var="title">
						<option value="${title.id}">${title.name}</option>
					</c:forEach>
				</select>
				<button type="submit">Save</button>
				<button type="reset" value="reset" id="cancelEditButton">Cancel</button>
				<button type="button" id="removeButton">Remove</button>
				<br> Required Fields indicated with a *
			</div>
			<div></div>
			<input type="hidden" name="${_csrf.parameterName }"
			value="${_csrf.token }" />
		</form:form>
	</fieldset>
</div>