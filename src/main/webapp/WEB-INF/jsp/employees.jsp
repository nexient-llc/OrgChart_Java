<!DOCTYPE html>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="sec"
uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<h3>Manage Employees</h3> 
<br/>

<div id="addBtn-container">
		<button type="button" id="addBtn" style="width: 45px;">Add</button>	
</div>
<div id="addEntity" style="display:none">
	<fieldset>
		<legend>Add Employee</legend>
		<form:form id="newEmployee" name="New Employee" action="emps" method="post">
		First Name: <input type="text" name="firstName" required />
		Last  Name: <input type="text" name="lastName" required/>
		Middle    : <input type="text" name="middleInitial"  style="width: 20px;" maxlength="1"/>
		Email: <input type="text" name="email" required>
		Skype Name: <input type="text" name="skypeName" required><br></br>
	    Department:
			<select id="depart" name="department.id" required>
				<option value=""> </option>
				<c:forEach items="${depts}" var="dept">  
					<option value="${dept.getId()}">${dept.name}</option>
				</c:forEach>
			</select>
		Job Title:
			<select id="job" name="jobTitle.id">
				<option value=""> </option>
				<c:forEach items="${titles}" var="title">  
					<option value="${title.getId()}"> ${title.name}</option>
				</c:forEach>
			</select>
		<button type="submit">Save</button>
		<button id="cancel" type="reset" value="reset" > Cancel</button>
		</form:form>
		</fieldset> 
</div>
<div class="editClass" id="editEntity" style="display: none">
	<fieldset>
		<legend>Edit Employee</legend>
		<form:form name="Edit Employee" action="emps" method="post">
			<div>  
				<input type="hidden" id="empId" name="id"/>
				 *First Name <input id="first-edit" type="text" name="firstName" />
				 Middle <input id="empMid-edit" type="text" name="middleInitial" style="width:15px" />
				 *Last Name<input id="last-edit" type="text" name="lastName" />
				 *Email<input id="empEmail-edit" type="text" name="email" />
				 *Skype Name<input id="empSkype-edit" type="text" name="skypeName" />
				 *Department:
			<select id="depart-edit" name="department.id">
				<option value=""> </option>
				<c:forEach items="${depts}" var="dept">  
					<option value="${dept.getId()}">${dept.name}</option>
				</c:forEach>
			</select>
				*Job Title:
			<select id="job-edit" name="jobTitle.id">
				<option value=""> </option>
				<c:forEach items="${titles}" var="title">  
					<option value="${title.getId()}"> ${title.name}</option>
				</c:forEach>
			</select>
				<button type="submit">Save</button>
				<button id="cancelEdit" type="reset" value="reset" >Cancel</button>
			</div>
			<div></div>
		</form:form>
	</fieldset>
</div>
<div id="searchBtn-container">
<button type="button" id="searchBtn" style="width: 45px;">Filter</button>	
</div>

<div class="searchClass" id="searchEntity" style="display: none">
	<fieldset>
		<legend>Edit Employee</legend>
		<form:form name="Search Employee" action="emps" method="post">
			<div>  
				<input type="hidden" id="empId" name="id"/>
				 Full Name <input id="searchFullName" type="text" name="firstName" />
				 Department:
			<select id="searchDepartment" name="department.id">
				<option value=""> </option>
				<c:forEach items="${depts}" var="dept">  
					<option value="${dept.getId()}">${dept.name}</option>
				</c:forEach>
			</select>
				*Job Title:
			<select id="searchJobTitle" name="jobTitle.id">
				<option value=""> </option>
				<c:forEach items="${titles}" var="title">  
					<option value="${title.getId()}"> ${title.name}</option>
				</c:forEach>
			</select>
				<button type="submit">Search</button>
				<button id="searchEdit" type="reset" value="reset" >Cancel</button>
			</div>
			<div></div>
		</form:form>
	</fieldset>
</div>

<table id="t1" style="width:400px;"> 
	<tr><!-- <sec:authorize access="hasRole('ROLE_ADMIN')"> -->
		<!-- <th>Task</th></sec:authorize> --> 
   <th> Name</th>
	<th>Department</th> <th>Job Title</th>
	</tr> 
	</table>
	<table id="t1"> 
	<tr><!-- <sec:authorize access="hasRole('ROLE_ADMIN')"> -->
		<!-- <th>Task</th></sec:authorize> --> 
	<c:forEach items="${emps}" var="emps">
		<tr class="EmployeeTable"> 
			<!-- <sec:authorize access="hasRole('ROLE_ADMIN')">
				<td>delete</td>
			</sec:authorize> -->
			<td style="width:135px" align="left" > ${emps}</td>
			<td style="width:150px" align="left" > ${emps.department.name}</td>
			<td style="width:100px" align="left" > ${emps.jobTitle.name}</td>
			<td>
			<button class="editButton" value="${emps.id}"  id="editEntity" style="width: 60px;">Edit</button>
			</td> 			
		</tr>
	</c:forEach> 
</table>