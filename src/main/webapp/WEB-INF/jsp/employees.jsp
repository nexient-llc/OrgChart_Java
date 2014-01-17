<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<h3>Employees</h3>

<div id="filterBtn-container">
	<button type="button" id="filterBtn">Filter</button>
</div>

<div id="filterEntity" style="display: none">
</div>

<div id="addBtn-container">
	<button type="button" id="addBtn" style="width: 45px;">Add</button>
</div>

<div id="addEntity" style="display: none">
	<fieldset>
		<legend>Add Employee</legend>
		<form:form modelAttribute="emp" action="emps" method="post">
			<table>
				<tr>
					<td>First Name*:</td>
					<td><form:input path="firstName" maxlength="20" /></td>
					<td>Middle Initial:</td>
					<td><form:input path="middleInitial" maxlength="1" size="1" /></td>
					<td>Last Name*:</td>
					<td><form:input path="lastName" maxlength="50" /></td>
				</tr>
				<tr>
					<td>Department*:</td>
					<td><form:select path="department.id">
							<option value="" />
							<c:forEach items="${depts}" var="depart">
								<option label="${depart.name}" value="${depart.id}" />
							</c:forEach>
						</form:select></td>
					<td>Job Title*:</td>
					<td><form:select path="jobTitle.id">
							<option value="" />
							<c:forEach items="${jobs}" var="userJob">
								<option label="${userJob.name}" value="${userJob.id}" />
							</c:forEach>
						</form:select></td>
				</tr>
				<tr>
					<td>Email*:</td>
					<td><form:input path="email" maxlength="100" /></td>
					<td>Skype Name*:</td>
					<td><form:input path="skypeName" maxlength="100" /></td>
				</tr>
				<tr>
					<i>*Required Field</i>
				</tr>
			</table>
			<input type=submit />
			<button id="cancelBtn">Cancel</button>
		</form:form>
	</fieldset>
</div>

<div class="divTable">
	<div class="headRow" id="preEditViewBar">
		<!-- <sec:authorize access="hasRole('ROLE_ADMIN')"> -->
		<!-- <th>Task</th></sec:authorize> -->
		<div class="divCol">Employee Name</div>
		<div class="divCol">Department</div>
		<div class="divCol">Job Title</div>
	</div>
	<div class="headRow" id="postEditViewBar" style="display: none">
		<div class="divCol">First Name</div>
		<div class="divCol">Middle Initial</div>
		<div class="divCol">Last Name</div>
		<div class="divCol">Email</div>
		<div class="divCol">Skype Name</div>
		<div class="divCol">Department</div>
		<div class="divCol">Job Title</div>
	</div>
	<c:forEach items="${emps}" var="employee">
		<div class="divRow" id="ViewEmps${employee.id}">
			<!-- <sec:authorize access="hasRole('ROLE_ADMIN')">
				<td>delete</td>
			</sec:authorize> -->
			<div class="content">${employee.firstName} ${employee.lastName}</div>
			<div class="content">${employee.department.name}</div>
			<div class="content">${employee.jobTitle.name }</div>
			<div class="content"><button class="editBtn" value="${employee.id}">Edit</button><button class="deleteBtn" 
									value="${employee.id}">Remove</button></div>
		</div>

		<div class="divRow" id="EditEmps${employee.id}" style="display: none">
			<div class="content"><input path="firstName" id="empFirstName${employee.id}"
				maxlength="20" value="${employee.firstName}" class="textBoxClass"></div>
			<div class="content"><input path="middleInitial" id="empMidInitial${employee.id}"
				maxlength="1" value="${employee.middleInitial}" class="textBoxClass"></div>
			<div class="content"><input path="lastName" id="empLastName${employee.id}"
				maxlength="50" value="${employee.lastName}" class="textBoxClass"></div>
			<div class="content"><input path="email" id="empEmail${employee.id}"
				value="${employee.email}" class="textBoxClass"></div>
			<div class="content"><input path="skypeName" id="empSkypeName${employee.id}"
				value="${employee.skypeName}" class="textBoxClass"></div>
			<div><select path="department.id" id="deptId${employee.id}">
					<option value="" />
					<c:forEach items="${depts}" var="depart">
						<c:choose>
							<c:when test="${employee.department.id == depart.id}">
								<option selected label="${depart.name}" value="${depart.id}" />
							</c:when>
							<c:otherwise>
								<option label="${depart.name}" value="${depart.id}" />
							</c:otherwise>
						</c:choose>
					</c:forEach>
			</select></div>
			<div class="content"><select path="jobTitle.id" id="jobId${employee.id}">
					<option value="" />
					<c:forEach items="${jobs}" var="userJob">
						<c:choose>
							<c:when test="${employee.jobTitle.id == userJob.id}">
								<option selected label="${userJob.name}" value="${userJob.id}" />
							</c:when>
							<c:otherwise>
								<option label="${userJob.name}" value="${userJob.id}" />
							</c:otherwise>
						</c:choose>
					</c:forEach>
			</select></div>
			<div class="content"><button class='saveBtn' value="${employee.id}">Save</button><button class='cancelEditBtn' value="${employee.id}">Cancel</button></div>
		</div>
	</c:forEach>
</div>