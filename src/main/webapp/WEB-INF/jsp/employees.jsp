<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<h3>Employees Page</h3>

<div id="filterBox">
	<span id="expandFilter" style="cursor:default;" onClick="expandFilter();"><h1>&nbsp;+&nbsp;</h1></span>
</div>
<div id="filterContent" style="display:none;">
	<span id="expandFilter" style="cursor:default;" onClick="expandFilter();"><h1>&nbsp;-&nbsp;</h1></span>
	<br />
	<form id="filterForm" action="emps" method="post">
		<input type="hidden" name="_method" value="PUT">
		<labeL>Full Name:</labeL>
		<input type="text" name="fullName" id="fullName"/>
<!-- 		<select id="autoBox" class="autoComplete"></select> -->
		
		<labeL>Job Title:</labeL>
		<select name="jobTitleId" id="jobTitleId">
			<option value="">...</option>
			<!-- need to change back to the way it was with the multiple lists being returned from the get method -->
			<c:forEach items="${jobs}" var="job">
				<option value="${job.id}">${job.name}</option>
			</c:forEach>
		</select>
		
		<labeL>Department:</labeL>
		<select name="departmentId" id="departmentId">
			<option value="">...</option>
			<c:forEach items="${depts}" var="dept">
				<option value="${dept.id}">${dept.name}</option>
			</c:forEach>
		</select>
		
		<button type="submit" id="filter" name="buttonClicked" value="filter" style="width: 45px;">Filter</button>
		<button type="button" onClick="clearForm();" id="filterReset" style="width: 45px;">Clear</button>
	</form>
</div>

<div id="addBtn-container">
		<button type="button" id="addBtn" style="width: 45px;">Add</button>	
</div>

<div id="addEntity" style="display:none">
	<fieldset>
		<legend>Add New Employee</legend>
		<form name="newEmployee" id="newEmployee" action="emps" method="post">
			<div>
				<labeL>First Name <span style="color:red">*</span>:</labeL>
				<input type="text" name="firstName" id="firstName"/>
				
				<label>Middle Initial</label>
				<input type="text" name="middleInitial" id="middleInitial" />
				
				<labeL>Last Name <span style="color:red">*</span>:</labeL>
				<input type="text" name="lastName" id="lastName"/>
				
				<labeL>Department <span style="color:red">*</span>:</labeL>
				<select name="departmentId" id="departmentId">
					<option value="">...</option>
					<c:forEach items="${depts}" var="dept">
						<option value="${dept.id}">${dept.name}</option>
					</c:forEach>
				</select>
				
				<labeL>Email <span style="color:red">*</span>:</labeL>
				<input type="text" name="email" id="email"/>
				
				<labeL>Skype Name <span style="color:red">*</span>:</labeL>
				<input type="text" name="skypeName" id="skypeName"/>
				
				<labeL>Job Title <span style="color:red">*</span>:</labeL>
				<select name="jobTitleId" id="jobTitleId">
					<option value="">...</option>
					<!-- need to change back to the way it was with the multiple lists being returned from the get method -->
					<c:forEach items="${jobs}" var="job">
						<option value="${job.id}">${job.name}</option>
					</c:forEach>
				</select>
				
				<button type="submit" name="buttonClicked" value="add">Save</button>
				<button type="button" id="rstBtn" onClick="resetForm();">Clear</button>
			</div>
		</form>
		Required Fields indicated with a <span style="color:red">*</span>
	</fieldset>
</div>

<table id="t1"> 
	<tr>
		<th>Name</th> <th>Department</th> <th>Job Title</th> <!-- Remove this --><th id="taskHead">Task</th><!-- Remove this -->
		<!-- ***** Fix this! remove the <th> you created with the value actions and replace it with this! ***** -->
		<!-- <sec:authorize access="hasRole('ROLE_ADMIN')"> -->
		<!-- <th id="taskHead">Task</th></sec:authorize> --> 
	</tr> 
	<c:forEach items="${emps}" var="emp">
		<tr>
			<td>${emp.firstName} ${emp.middleInitial} ${emp.lastName}</td> 
			<td>${emp.department.name}</td>
			<td>${emp.jobTitle.name}</td>
			<td>
				<div id="button-container${emp.id}">
					<button type="button" id="editButton" onClick='editEmployee(${emp.id})'>Edit</button>
					<!-- <sec:authorize access="hasRole('ROLE_ADMIN')">
						<td>delete</td>
					</sec:authorize> -->
				</div>
			</td>
			<td>
				<div id="editEntity${emp.id}" style="display:none">
					<fieldset>
						<legend>Edit Employee ${emp.firstName} ${emp.lastName}</legend>
						<form name="editJob${emp.id}" id="editJob${emp.id}" action="emps" method="post">
							<input type="hidden" name="_method" value="PUT">
							<div>
								<input type="hidden" name="id" value="${emp.id }" />
								<labeL>First Name <span style="color:red">*</span>:</labeL>
								<input type="text" name="firstName" id="editFirstName${emp.id}" value="${emp.firstName}"/>
								
								<labeL>Middle Initial:</labeL>
								<input type="text" name="middleInitial" id="editFirstName${emp.id}" value="${emp.middleInitial}"/>
								
								<labeL>Last Name <span style="color:red">*</span>:</labeL>
								<input type="text" name="lastName" id="editLastName${emp.id}" value="${emp.lastName}"/>
								
								<labeL>Department <span style="color:red">*</span>:</labeL>
								<select name="departmentId" id="departmentId">
									<option value="">...</option>
									<c:forEach items="${depts}" var="dept">
										<c:choose>
											<c:when test="${dept.id == emp.department.id}">
												<option value="${dept.id}" selected>${dept.name}</option>
											</c:when>
											<c:otherwise>
												<option value="${dept.id}">${dept.name}</option>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</select>
								
								<labeL>Email <span style="color:red">*</span>:</labeL>
								<input type="text" name="email" id="email" value="${emp.email}"/>
								
								<labeL>Skype Name <span style="color:red">*</span>:</labeL>
								<input type="text" name="skypeName" id="skypeName" value="${emp.skypeName}"/>
								
								<labeL>Job Title <span style="color:red">*</span>:</labeL>
								<select name="jobTitleId" id="jobTitleId">
									<option value="">...</option>
									<c:forEach items="${jobs}" var="job">
										<c:choose>
											<c:when test="${job.id == emp.jobTitle.id}">
												<option value="${job.id}" selected>${job.name}</option>
											</c:when>
											<c:otherwise>
												<option value="${job.id}">${job.name}</option>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</select>
								
								<button type="submit" name="buttonClicked" value="edit">Save</button>
								<button type="button" onClick="closeEdit(${emp.id});">Clear</button>
							</div>
						</form>
						Required Fields indicated with a <span style="color:red">*</span>
					</fieldset>
				</div>
			</td>
		</tr>
	</c:forEach> 
</table>