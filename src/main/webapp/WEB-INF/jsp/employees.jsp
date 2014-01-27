<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<h3>Employees</h3>




<div class="filter-container" id="filter-container">
	<label>filter</label>
</div>

<div id="search" style="display:none;">
	<fieldset>
		<legend>Filter Employee</legend>
			<div>
				<label>Full name: (First Last)</label>
				<input type="text" class="autocomplete" maxlength="70" id="empFullName">
				<label>Department:</label>
				<select class="filterBtn" name="empDeptId" id="empDeptId">
					<option value = ""/>
					<c:forEach items="${depts}" var="dept">
						<option value="${dept.id}">${dept.name}</option>
					</c:forEach>
				</select>
				<label>Job Title:</label>
				<select class="filterBtn" name="empJobId" id="empJobId">
					<option value = ""/>
					<c:forEach items="${jobs}" var="job">
						<option value="${job.id}">${job.name}</option>
					</c:forEach>
				</select>
				<button class = "filterBtn">Filter</button>
				<button type="reset">Clear</button>
			</div>
			<div></div>
	</fieldset>
</div>

<div id="addBtn-container">
	<button type="button" id="addBtn" style="width: 45px;">Add</button>
</div>

<div id="addEntity" style="display: none;">
	<fieldset>
		<legend>Add Employee</legend>
		<form:form modelAttribute="emp" action="emps" method="post">
			<div>
				<label>Last Name:*</label>
				<form:input path="lastName" maxlength="50" />
				<label>First Name:*</label>
				<form:input path="firstName" maxlength="20" />
				<label>Middle Initial:</label>
				<form:input path="middleInitial" maxlength="1" size="1" />
				<label>Department:*</labeL>
				<form:select path="department.id">
					<option value="" />
					<c:forEach items="${depts}" var="dept">
						<option value="${dept.id}">${dept.name}</option>
					</c:forEach>
				</form:select>
				<label>Email:*</label><form:input path="email" maxlength="100" />
				<label>Skype:*</label><form:input path="skypeName" maxlength="100" />
				<label>Job Title:*</label>
				<form:select path="jobTitle.id">
					<option value="" />
					<c:forEach items="${jobs}" var="job">
						<option value="${job.id}">${job.name}</option>
					</c:forEach>
				</form:select>
				<button type="submit">Save</button>
				<button type="reset">Clear</button>
			</div>
			<div></div>
		</form:form>

		<footer>
			<br>Required fields marked with a *
		</footer>
	</fieldset>
</div>



<div class="table" id="t1">
	<div class="row header">
		<span class="cell name">Name(Last, First MI.)</span> 
		<span class="cell deptName">Department</span>
		<span class="cell jobName">Job Title</span>
		<span class="cell editBtn"></span>
		<span class="cell deleteBtn"></span>
	</div>
	<c:forEach items="${emps}" var="emp">
		<div class="row view" id="ViewEmps${emp.id}">
			<span class="cell name">${emp.lastName}, ${emp.firstName}
				${emp.middleInitial}. </span> <span class="cell deptName">${emp.department.name}</span>
			<span class="cell jobName">${emp.jobTitle.name}</span>
			<span class="cell editBtn">
				<button type="button" class="editBtn" value="${emp.id}">Edit</button>
			</span>
			<span class="cell deleteBtn">
				<button type="button" class="deleteBtn" value="${emp.id}">Delete</button>
			</span>
		</div>

	<div class="row edit" id="EditEmps${emp.id}" style="display:none">
	<fieldset>
		<legend>Edit Employee</legend>
			<div>
				<label>Last Name:*</label>
				<input path="lastName" maxlength="50" value="${emp.lastName}" id="empLastName${emp.id}">
				<label>First Name:*</label>
				<input path="firstName" maxlength="20" value="${emp.firstName}" id="empFirstName${emp.id}">
				<label>Middle Initial:</label>
				<input path="middleInitial" maxlength="1" size="1" value="${emp.middleInitial}" id="empMidInitial${emp.id}">
				<label>Department:*</labeL>
				<select path="department.id" id="empdeptId${emp.id}">
					<option value="" />
					<c:forEach items="${depts}" var="dept">
						<c:choose>
							<c:when test="${emp.department.id == dept.id}">
								<option selected value="${dept.id}">${dept.name}</option>
							</c:when>
							<c:otherwise>
								<option value="${dept.id}">${dept.name}</option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</select>
				<label>Email:*</label><input path="email" maxlength="100" value="${emp.email}" id="empEmail${emp.id}">
				<label>Skype:*</label><input path="skypeName" maxlength="100" value="${emp.skypeName}" id="empSkypeName${emp.id}">
				<label>Job Title:*</label>
				<select path="jobTitle.id" id="empjobId${emp.id}">
					<option value="" />
					<c:forEach items="${jobs}" var="job">
						<c:choose>
							<c:when test="${emp.jobTitle.id == job.id}">
								<option selected value="${job.id}">${job.name}</option>
							</c:when>
							<c:otherwise>
								<option value="${job.id}">${job.name}</option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</select>
				<button class = "saveBtn" value = "${emp.id}">Save</button>
				<button type="reset">Clear</button>
			</div>
			<div></div>

		<footer>
			<br>Required fields marked with a *
		</footer>
	</fieldset>
		</div>
	</c:forEach>
</div>

















