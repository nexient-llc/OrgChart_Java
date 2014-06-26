<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<h3>Employees</h3> 
<table id="t1"> 
	<tr><!-- <sec:authorize access="hasRole('ROLE_ADMIN')"> -->
		<!-- <th>Task</th></sec:authorize> --> 
		<th>First Name</th> <th>Last Name</th> <th>Job Title</th> <th>Middle Initial</th>
		<th>Manager</th> <th>Department</th> <th>IS_Manager</th>  <th>Email</th>
		<th>Skype Name</th>
	</tr>
	<c:forEach items="${employees}" var="emps">
		<tr> 
			<!-- <sec:authorize access="hasRole('ROLE_ADMIN')">
				<td>delete</td>
			</sec:authorize> -->
			<td>${emps.firstName}</td> 
			<td>${emps.lastName}</td>
			<td>${emps.jobtitle}</td>
			<td>${emps.middleInitial}</td>
			<td>${emps.manager}</td> 
			<td>${emps.department}</td>
			<td>${emps.isManager}</td>
			<td>${emps.skypeName}</td>
			<td>${emps.email}</td>
		</tr>
	</c:forEach>
</table>

<div id="addBtn-container">
		<button type="button" id="addBtn" style="width: 45px;">Add</button>	
</div>

<div id="addEntity" style="display:none">
	<fieldset>
		<legend>Add Employee</legend>
		<form name="newEmp" action="employees" method="post">
		<div><labeL>First  Name:</labeL><input type="text" name="firstName"/>
			 <labeL>Last  Name:</labeL><input type="text" name="lastName"/>
			 <labeL>Job Title:</labeL><input type="text" name="jobtitle"/>
			 <labeL>Middle Initial:</labeL><input type="text" name="middleInitial"/>
			 <labeL>Manager:</labeL><input type="text" name="manager"/>
			 <labeL>Department:</labeL><input type="text" name="department"/>
			 <labeL>IS_Manager:</labeL><input type="text" name="isManager"/>
			 <labeL>Skype  Name:</labeL><input type="text" name="skypeName"/>
			 <labeL>Email:</labeL><input type="text" name="email"/>
			<button type="submit">Save</button>
		</div>
		<div></div>
		</form>
	</fieldset>
</div>
