<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<h3>Employees</h3>

<button id="addBtn">Add</button>
<button id="filterBtn">Filter</button>

<div id="addEntity" class="editRow">
	<fieldset>
		<legend>Add Employee</legend>
		<form:form modelAttribute="newEmp" action="emps" method="post">
			<label>First Name:</label>
			<form:input path="firstName" required="true"/>

			<label>Middle Init.:</label>
			<form:input path="middleInitial" maxlength="1" size="1"/>

			<label>Last Name:</label>
			<form:input path="lastName" required="true"/>

			<label>Email:</label>
			<form:input path="email" required="true"/>

			<label>Skype:</label>
			<form:input path="skypeName" required="true"/>

			<label>Department:</label>
			<form:select path="department.id">
				<option value=""/>
				<c:forEach items="${depts}" var="dept">
					<option label="${dept.name}" value="${dept.id}"/>
				</c:forEach>
			</form:select>

			<label>Job Title:</label>
			<form:select path="jobTitle.id">
				<option value=""/>
				<c:forEach items="${jobs}" var="job">
					<option label="${job.name}" value="${job.id}"/>
				</c:forEach>
			</form:select>

			<input type="submit" value="Save" />
			<button id="cancelAddBtn">Cancel</button>
		</form:form>
	</fieldset>
</div>

<div id="filterEntity" class="editRow">
	<fieldset>
		<legend>Filter Employees</legend>
			<label>Full Name</label>
			<input id="fullName" type="text" name="fullName"/>
			
			<label>Department:</label>
			<select id="deptId" name="deptId">
				<option value=""/>
				<c:forEach items="${depts}" var="dept">
					<option label="${dept.name}" value="${dept.id}"/>
				</c:forEach>
			</select>
			
			<label>Job Title:</label>
			<select id="jobId" name="jobId">
				<option value=""/>
				<c:forEach items="${jobs}" var="job">
					<option label="${job.name}" value="${job.id}"/>
				</c:forEach>
			</select>
			<button id="searchBtn" type="submit">Search</button>
			<button id="resetBtn">Reset</button>
	</fieldset>
</div>

<div id="t1"> 
	<div id="th" class="row headers activeTH">
		<div class="inputCol">Employee</div> <div class="miCol">&nbsp;</div> <div class="inputCol">&nbsp;</div> <div class="emailCol">Email</div> <div class="inputCol">Skype</div> <div class="selectCol">Department</div> <div class="selectCol">Job Title</div> <div class="buttonCol">&nbsp;</div> <div class="buttonCol">&nbsp;</div>
	</div> 
	<div id="thEdit" class="row headers editRow"><div class="inputCol">First Name*</div> <div class="miCol">MI</div> <div class="inputCol">Last Name*</div> <div class="emailCol">Email*</div> <div class="inputCol">Skype*</div> <div class="selectCol">Department</div> <div class="selectCol">Job Title</div> <div class="buttonCol">*=required</div> <div class="buttonCol">&nbsp;</div></div>
	<c:forEach items="${emps}" var="emp">
		<div id="row${emp.id}" class="row">
			<div class="firstName inputCol" data-value="${emp.firstName}">${emp.firstName}</div>
			<div class="middle miCol" data-value="${emp.middleInitial}">${emp.middleInitial}&nbsp;</div>
			<div class="lastName inputCol" data-value="${emp.lastName}">${emp.lastName}</div>
			<div class="email emailCol" data-value="${emp.email}">${emp.email}</div>
			<div class="skype inputCol" data-value="${emp.skypeName}">${emp.skypeName}</div> 
			<div class="dept selectCol" data-value="${emp.department.id}">${emp.department.name}&nbsp;</div> 
			<div class="job selectCol" data-value="${emp.jobTitle.id}">${emp.jobTitle.name}&nbsp;</div> 
			<div class="buttonCol"><button class="editBtn" value="${emp.id}">Edit</button></div>		
			<div class="buttonCol"><button class="removeBtn" value="${emp.id}">Remove</button></div>
		</div>
		
		<div id="editRow${emp.id}" class="row editRow">
			<div class="inputCol"><input name="firstName" class="firstName editInput"/></div>
			<div class="miCol"><input name="middleInitial" class="middle editInput" maxlength="1" size="1"/></div>
			<div class="inputCol"><input name="lastName" class="lastName editInput"/></div>
			<div class="emailCol"><input name="email" class="email editInput"/></div>
			<div class="inputCol"><input name="skypeName" class="skype editInput"/></div>
			<div class="selectCol"><select name="department.id" class="dept editInput">
					<option value="" />
					<c:forEach items="${depts}" var="tDept">
						<option value="${tDept.id}">${tDept.name}</option>
					</c:forEach>
				</select>
			</div>
			<div class="selectCol"><select name="jobTitle.id" class="job editInput">
					<option value="" />
					<c:forEach items="${jobs}" var="tJob">
						<option value="${tJob.id}">${tJob.name}</option>
					</c:forEach>
				</select>
			</div>
			<div class="buttonCol"><button class="saveBtn" value="${emp.id}">Save</button></div>
			<div class="buttonCol"><button class="cancelEditBtn" value="${emp.id}">Cancel</button></div>
		</div>
	</c:forEach> 
</div>