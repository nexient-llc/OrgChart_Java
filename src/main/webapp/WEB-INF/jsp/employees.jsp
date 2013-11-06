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
 		<th> Actions </th>
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

<div class="hidden" id="empEditDialog" title="Edit Employee">
  <form>
  <fieldset>
    <label for="firstName">First Name</label>
    <input type="text" id="firstName" class="text ui-widget-content ui-corner-all" />
    
    <label for="lastName">Last Name</label>
    <input type="text" id="lastName" class="text ui-widget-content ui-corner-all" />
    
    <label for="jobTitles">Job Title</label>
	<select name="jobTitles" id="jobTitles">
		<c:forEach items="${jobs}" var="job">
			<option value="${job.id}" >${job.name}</option>
		</c:forEach>
	</select>
	
    <label for="departments">Departments</label>
	<select name="departments" id="departments">
		<c:forEach items="${depts}" var="dept">
			<option value="${dept.id}" >${dept.name}</option>
		</c:forEach>
	</select>
    
    <label for="manager">Managed By:</label>
    <input type="text" id="manager" value="click to lookup"/>
    
    <label for="isManager">Is Manager? </label>
    <input type="checkbox" id="isManager"/>
    <br>
 
    <label for="skypeName">Skype Name</label>
    <input type="text" id="skypeName" value="" class="text ui-widget-content ui-corner-all" />
    
    <label for="email">Email</label>
    <input type="text" id="email" value="" class="text ui-widget-content ui-corner-all" />
    
  </fieldset>
  </form>
</div>

<!-- 
<div id="addBtn-container">
		<button type="button" id="addBtn" style="width: 45px;">Add</button>	
</div>

<div id="addEntity" style="display:none">
	<fieldset>
		<legend>Add Department</legend>
		<form:form name="newDept" action="depts" method="post">
			<div>
				<label>Dept Name:</label><input type="text" name="name"/>
				<label>Parent Dept:</label>
				<select name="parent_id">
					<option value="-1">No Parent</option>
					<c:forEach items="${depts}" var="dept">
						<option value="${dept.id}">${dept.name}</option>
					</c:forEach>
				</select>
				<button type="submit">Save</button>
			</div>
		</form:form>
	</fieldset>
</div>
 -->