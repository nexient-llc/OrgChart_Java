<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<link rel="stylesheet" type="text/css"
	href="<c:url value='/styles/employee.css"'/>
	media="
	screen" charset="utf-8" />
<h3>Employee Page</h3>
<div id="filterBtn-container">
	<button type="button" id="filterBtn">Filter</button>
</div>
<div id="filterEntity" style="display: none">
	<fieldset>
		<legend>Filter Employees</legend>
		<form name="filterEmps" action="searchemps" method="get">
			<label>Name:</label><input type="text" name="filterName"
				id="filterBox" /> <label>Department:</label> <select name="deptid">
				<option value="">Search Departments</option>
				<c:forEach items="${depts}" var="dept">
					<option value="${dept.id}">${dept.name}</option>
				</c:forEach>
			</select> <label>Job Title:</label> <select name="jobid">
				<option value="">Search Job Titles</option>
				<c:forEach items="${jobs}" var="job">
					<option value="${job.id}">${job.name}</option>
				</c:forEach>
			</select>
			<button type="submit">Search</button>
			<button type="reset" id="resetFilterBtn" value="reset">Reset</button>
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
				</select> <br>
				<br> <label>Email: *</labeL><input type="text" name="email"
					required /> <label>Skype Name: *</labeL><input type="text"
					name="skypeName" required /> <label>Job Titles:</label> <select
					name="jobTitle.id">
					<c:forEach items="${jobs}" var="job">
						<option value="${job.id}">${job.name}</option>
					</c:forEach>
				</select> <br>
				<br>
				<button type="submit">Save</button>
				<button type="reset" id="cancelBtn" value="reset">Cancel</button>
			</div>
			<br>
			<div>Required Fields indicated with a *</div>
		</form>
	</fieldset>
</div>
<table id="t1">
	<tr>
		<!-- <sec:authorize access="hasRole('ROLE_ADMIN')"> -->
		<!-- <th>Task</th></sec:authorize> -->
		<th>Employee Name</th>
		<th>Deptartment</th>
		<th>Job Title</th>
	</tr>
	<c:forEach items="${emps}" var="emp">
		<tr id="tableRow-${emp.id}">
			<!-- <sec:authorize access="hasRole('ROLE_ADMIN')">
				<td>delete</td>
			</sec:authorize> -->
			<td>${emp.getFullName()}</td>
			<td>${emp.department.name}</td>
			<td>${emp.jobTitle.name }</td>
			<td>
				<div class="editBtn-containerClass" id="editBtn-container-${emp.id}"
					<sec:authorize access="hasRole('ROLE_ADMIN')">style="display:block"</sec:authorize>
					style="display: none">
					<button type="button" class="editBtnClass" id="editBtn-${emp.id}">Edit</button>
				</div>
			</td>
			<td>
				<div id="editEntity-${emp.id}" style="display: none">
					<fieldset>
						<legend>Edit Employee</legend>
						<form name="editEmp" action="updateEmp" id="editForm-${emp.id}"
							method="post">
							<input type="hidden" name="id" value="${emp.id}" /> <input
								type="hidden" id="activeVal-${emp.id}" name="isActive"
								value="true" />
							<div>
								<label>First Name: *</label><input type="text" name="firstName"
									value="${emp.firstName}" required /> <label>Last Name:
									*</label><input type="text" name="lastName" value="${emp.lastName}"
									required /> <label>Middle Initial:</label><input type="text"
									name="middleInitial" value="${emp.middleInitial}" maxlength="1"
									size="1" /> <label>Department:</label> <select
									name="department.id">
									<c:forEach items="${depts}" var="dept">
										<c:choose>
											<c:when test="${emp.department.id == dept.id}">
												<option value="${dept.id}" selected>${dept.name}</option>
											</c:when>
											<c:otherwise>
												<option value="${dept.id}">${dept.name}</option>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</select> <br>
								<br> <label>Email: *</labeL><input type="text" name="email"
									value="${emp.email}" required /> <label>Skype Name: *</labeL><input
									type="text" name="skypeName" value="${emp.skypeName}" required />
								<label>Job Titles:</label> <select name="jobTitle.id">
									<c:forEach items="${jobs}" var="job">
										<c:choose>
											<c:when test="${emp.jobTitle.id == job.id}">
												<option value="${job.id}" selected>${job.name}</option>
											</c:when>
											<c:otherwise>
												<option value="${job.id}">${job.name}</option>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</select> <br>
								<br>
								<button type="submit">Save</button>
								<button type="button" class="deleteBtnClass"
									id="deleteBtn-${emp.id}">Delete</button>
								<button type="reset" class="cancelEditBtnClass"
									id="cancelEditBtn-${emp.id}" value="reset">Cancel</button>
							</div>
							<br>
							<div>Required Fields indicated with a *</div>
						</form>
					</fieldset>
				</div>
			</td>
		</tr>
	</c:forEach>
</table>