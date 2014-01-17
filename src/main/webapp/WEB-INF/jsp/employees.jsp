<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<h3>Employees</h3>

<button id="filterBtn">Filter</button>
<div id="filterEntity" style="display:none">
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

<div id="addBtn-container">
		<button type="button" id="addBtn">Add</button>	
</div>
<div id="addEntity" style="display:none">
	<fieldset>
		<legend>Add Employee</legend>
		<form:form modelAttribute="newEmp" action="emps" method="post">
			<table>
				<tr>
					<td>
						<label>First Name:</label>
						<form:input path="firstName" required="true"/>
					</td>
					<td>
						<label>Middle Init.:</label>
						<form:input path="middleInitial" maxlength="1" size="1"/>
					</td>
					<td>
						<label>Last Name:</label>
						<form:input path="lastName" required="true"/>
					</td>
				</tr>
				<tr>
					<td>
						<label>Email:</label>
						<form:input path="email" required="true"/>
					</td>
				
				
					<td>
						<label>Skype:</label>
						<form:input path="skypeName" required="true"/>
					</td>
				</tr>
				<tr>
					<td>
						<label>Department:</label>
						<form:select path="department.id">
							<option value=""/>
							<c:forEach items="${depts}" var="dept">
								<option label="${dept.name}" value="${dept.id}"/>
							</c:forEach>
						</form:select>
					</td>
					<td>
						<label>Job Title:</label>
						<form:select path="jobTitle.id">
							<option value=""/>
							<c:forEach items="${jobs}" var="job">
								<option label="${job.name}" value="${job.id}"/>
							</c:forEach>
						</form:select>
					</td>
				</tr>
				<tr>
					<td></td>
					<td class="saveCancel">
						<input type="submit" value="Save" />
						<button id="cancelAddBtn">Cancel</button>
					</td>
				</tr>
			</table>
		</form:form>
	</fieldset>
</div>

<table id="t1"> 
	<tr id="th" class="activeTH"><!-- <sec:authorize access="hasRole('ROLE_ADMIN')"> -->
		<!-- <th>Task</th></sec:authorize> --> 
		<th>Employee</th> <th></th> <th></th> <th>Email</th> <th>Skype</th> <th>Department</th> <th>Job Title</th> <th></th> <th></th>
	</tr> 
	<tr id="thEdit" style="display:none"><th>First Name*</th> <th>MI</th> <th>Last Name*</th> <th>Email*</th> <th>Skype*</th> <th>Department</th> <th>Job Title</th> <th>*=required</th> <th></th></tr>
	<c:forEach items="${emps}" var="emp">
		<tr id="row${emp.id}" class="row"> 
			<!-- <sec:authorize access="hasRole('ROLE_ADMIN')">
				<td>delete</td>
			</sec:authorize> -->
			<td class="firstName" data-value="${emp.firstName}">${emp.firstName}</td>
			<td class="middle" data-value="${emp.middleInitial}">${emp.middleInitial}</td>
			<td class="lastName" data-value="${emp.lastName}">${emp.lastName}</td>
			<td class="email" data-value="${emp.email}">${emp.email}</td>
			<td class="skype" data-value="${emp.skypeName}">${emp.skypeName}</td> 
			<td class="dept" data-value="${emp.department.id}">${emp.department.name}</td> 
			<td class="job" data-value="${emp.jobTitle.id}">${emp.jobTitle.name}</td> 
			<td><button class="editBtn" value="${emp.id}">Edit</button></td>		
			<td><button class="removeBtn" value="${emp.id}">Remove</button>
		</tr>
		
		<tr id="editRow${emp.id}" class="editRow" style="display:none">
			<td><input name="firstName" class="firstName editInput"/></td>
			<td><input name="middleInitial" class="middle editInput" maxlength="1" size="1"/></td>
			<td><input name="lastName" class="lastName editInput"/></td>
			<td><input name="email" class="email editInput"/></td>
			<td><input name="skypeName" class="skype editInput"/></td>
			<td><select name="department.id" class="dept editInput">
					<option value="" />
					<c:forEach items="${depts}" var="tDept">
						<option value="${tDept.id}">${tDept.name}</option>
					</c:forEach>
				</select>
			</td>
			<td><select name="jobTitle.id" class="job editInput">
					<option value="" />
					<c:forEach items="${jobs}" var="tJob">
						<option value="${tJob.id}">${tJob.name}</option>
					</c:forEach>
				</select>
			</td>
			<td><button class="saveBtn" value="${emp.id}">Save</button></td>
			<td><button class="cancelEditBtn" value="${emp.id}">Cancel</button></td>
		</tr>
	</c:forEach> 
</table>