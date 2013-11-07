<!DOCTYPE html>

<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<h3>Employee List</h3> 
<table id="t1"> 
	<!--  Heading -->
	<tr>
		<!-- <sec:authorize access="hasRole('ROLE_ADMIN')"> -->
		<!-- <th>Task</th></sec:authorize> --> 
		<th>ID</th> 
		<th>Name</th>
		<th>Job Title</th>
		<th> Department </th>
 		<th> Overseeing Manager </th>  
		<th> Role </th> <!--  Manager or Employee -->
 		<th> Skype </th> 
 		<th> Email </th>
 		<th> Edit/Remove </th>
	</tr> 
	
	<!--  Rows -->
	<c:forEach items="${emps}" var="emp">
		<tr> 
			<!-- <sec:authorize access="hasRole('ROLE_ADMIN')">
				<td>delete</td>
			</sec:authorize> -->
			<td>${emp.id}</td> 
			<td>${emp.firstName} ${emp.lastName}</td>
			<td>
				<!--  Display the employees department if they have one  -->
				<c:catch var="exception">${emp.jobTitle.name}</c:catch>
				<c:if test="${not empty exception}"> None </c:if>
			</td>
			<td>
				<!--  Display the employees department if they have one  -->
				<c:catch var="exception">${emp.department.name}</c:catch>
				<c:if test="${not empty exception}"> None </c:if>
			</td>
	 		<td>
				<!--  Display the employees manager if they have one  -->
				<c:catch var="exception">${emp.manager.firstName} ${emp.manager.lastName}</c:catch>
				<c:if test="${not empty exception}">None</c:if>
	 		</td> 
			<td> 
				<!--  Display whether or not an employee is a manager.  -->
				<c:choose>
				  <c:when test="${emp.isManager}">
				  	Manager
				  </c:when>
				  <c:otherwise>
				  	Employee
				  </c:otherwise>
				</c:choose>
			</td>
	 		<td>${emp.skypeName}</td> 
	 		<td> ${emp.email}</td>
	 		<td>
	 			<!--  Actions  -->
	 			<form action="javascript:;">
	 				<input type="submit" value="edit" name="empEdit" class="empEditBtn" data-id="${emp.id}">
	 			</form>
	 		</td>
	</c:forEach> 
</table>

<div class="hidden" id="empEditDialog" title="Add/Edit Employees">
  <b>Edit Area:</b>
  <form:form id="editForm" action="employees/edit" method="put">
  <input type="hidden" name="id" id="id"/>
  <fieldset>
    <label for="firstName">First Name</label>
    <input type="text" name="firstName" id="firstName"/> <br>
    
    <label for="lastName">Last Name</label>
    <input type="text" name="lastName" id="lastName" /> <br>
    
    <label for="jobTitles">Job Title</label>
	<select name="jobTitle.id" id="jobTitle">
		<c:forEach items="${jobs}" var="job">
			<option value="${job.id}" >${job.name}</option>
		</c:forEach>
	</select> <br>
	
    <label for="department">Departments</label>
	<select name="department.id" id="JobTitle">
		<c:forEach items="${depts}" var="dept">
			<option value="${dept.id}" >${dept.name}</option>
		</c:forEach>
	</select> <br>
    
    <label for="manager">Managed By:</label>
    <input type="text" name="manager.id" id="manager"/> <br>
    <span class="error" id="managerError" style="color: red"></span>
    
    <label for="isManager">Is Manager? </label>
    <input type="checkbox" name="isManager" id="isManager"/> <br>
 
    <label for="skypeName">Skype Name</label>
    <input type="text" name="skypeName" id="skypeName" /> <br>
    
    <label for="email">Email</label>
    <input type="text" name="email" id="email"/> <br>
    
    <h1> Press submit to accept changes. </h1>
    <input type="submit" value="Submit" id="editSubmit"/>
  </fieldset>
  </form:form>
  
  <div id="dangerZone">
  <br>
  <b>DANGER ZONE:</b>
  <form action="employees/delete" method="post">
  	<fieldset>
  	<input type="hidden" name="deleteId" id="deleteId"/>
  	Type the employee's <b>last name</b> (case sensitive) and press remove to erase them permanently:
  	<input type="text" name="confirmString" id="deleteName"/>
  	<br>
  	<input type="submit" value="Remove" id="deleteSubmit"/>
  	</fieldset>
  </form>
  </div>
</div>


<button type="button" id="addBtn">Add New Employee</button>	