<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<h3>Employees</h3>
<button type="button" id="filterButton" style="margin-bottom:5px">Filter</button>

<div id="filterEmployees" style="display:none; margin-bottom:5px">
	<fieldset>
		<legend>Filter Employees</legend>
		<form id="filter">
			<label>Full Name:</label>
			<input id="fullName" /><br />
			<label>Department:</label>
			<select id="deptToFind">
				<option value="-1" label="---" />
				<c:forEach items="${depts}" var="dept">
					<option value="${dept.id}" label="${dept.name}" />
				</c:forEach>
			</select><br />
			<label>Job Title:</label>
			<select id="jobToFind">
				<option value="-1" label="---" />
				<c:forEach items="${jobs}" var="job">
					<option value="${job.id}" label="${job.name}" />
				</c:forEach>
			</select><br />
			<button type="button" id="executeFilter">Filter</button>
			<button type="reset" id="resetFilter">Reset</button>
		</form>
	</fieldset>
</div>

<table id="t1"> 
	<tr><!-- <sec:authorize access="hasRole('ROLE_ADMIN')"> -->
		<!-- <th>Task</th></sec:authorize> --> 
		<th>First Name</th> 
		<th>MI</th>
		<th>Last Name</th>
		<th>Department</th>
		<th>Job Title</th>
		<th>Email</th>
		<th>Skype Name</th>
	</tr> 
	<c:forEach items="${emps}" var="emp">
		<tr class="empRow" 
			data-emp-id="${emp.id}"
			data-emp-first-name="${emp.firstName}"
			data-emp-middle-initial="${emp.middleInitial}"
			data-emp-last-name="${emp.lastName}"
			data-emp-full-name="${emp.firstName} <c:if test="${fn:length(emp.middleInitial) > 0}">${emp.middleInitial} </c:if>${emp.lastName}"
			data-emp-department-id="${emp.department.id}"
			data-emp-job-title-id="${emp.jobTitle.id}"
			data-emp-email="${emp.email}"
			data-emp-skype-name="${emp.skypeName}"> 
			<!-- <sec:authorize access="hasRole('ROLE_ADMIN')">
				<td>delete</td>
			</sec:authorize> -->
			<td style="margin-top:3px;margin-bottom:3px">${emp.firstName}</td> 
			<td>${emp.middleInitial}</td>
			<td>${emp.lastName}</td>
			<td>${emp.department.name}</td>
			<td>${emp.jobTitle.name}</td>
			<td>${emp.email}</td>
			<td>${emp.skypeName}</td>
			<td style="padding:0"><button class="editEmployee" style="margin-top:0;margin-bottom:0">Edit</td>
		</tr>
	</c:forEach> 
</table>

<div id="addBtn-container">
		<button type="button" id="addBtn" style="width: 45px;">Add</button>	
</div>

<div id="addEntity" style="display:none">
	<fieldset>
		<legend>Add Employee</legend>
		<form:form modelAttribute="employee" action="emps" method="post">
		<div>
			<label>First Name:</label>
			<form:input path="firstName" />*<br />
			<label>MI:</label>
			<form:input path="middleInitial" size="1" /><br />
			<label>Last Name:</label>
			<form:input path="lastName" />*<br />
			<label>Department:</label>
			<form:select path="department.id">
				<form:option value="-1" label="---" />
				<form:options items="${depts}" itemValue="id" itemLabel="name" />
			</form:select>*<br />
			<label>Job Title:</label>
			<form:select path="jobTitle.id">
				<form:option value="-1" label="---" />
				<form:options items="${jobs}" itemValue="id" itemLabel="name" />
			</form:select>*<br />
			<label>Email:</label>
			<form:input path="email" />*<br />
			<label>Skype Name:</label>
			<form:input path="skypeName" />*<br />
			<button type="submit">Save</button>
			<button type="reset" id="cancelAdd">Cancel</button>
			<br />
			<h5>Required fields indicated with a *</h5>
		</div>
		<div></div>
		</form:form>
	</fieldset>
</div>

<div id="editEntity" style="display:none">
	<fieldset>
		<legend>Edit Employee</legend>
		<form:form modelAttribute="employee" action="emps" method="put">
			<div>
				<form:hidden path="id" />
				<label>First Name:</label>
				<form:input path="firstName" />*<br />
				<label>MI:</label>
				<form:input path="middleInitial" size="1" /><br />
				<label>Last Name:</label>
				<form:input path="lastName" />*<br />
				<label>Department:</label>
				<form:select path="department.id">
					<form:option value="-1" label="---" />
					<form:options items="${depts}" itemValue="id" itemLabel="name" />
				</form:select>*<br />
				<label>Job Title:</label>
				<form:select path="jobTitle.id">
					<form:option value="-1" label="---" />
					<form:options items="${jobs}" itemValue="id" itemLabel="name" />
				</form:select>*<br />
				<label>Email:</label>
				<form:input path="email" />*<br />
				<label>Skype Name:</label>
				<form:input path="skypeName" />*<br />
				<button type="submit">Save</button>
				<button type="reset" id="cancelEdit">Cancel</button>
				<br />
				<h5>Required fields indicated with a *</h5>
			</div>
		</form:form>
	</fieldset>
</div>