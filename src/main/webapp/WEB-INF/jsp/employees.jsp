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
	</tr> 
	
	<!--  Rows -->
	<c:forEach items="${emps}" var="emp">
		<tr> 
			<!-- <sec:authorize access="hasRole('ROLE_ADMIN')">
				<td>delete</td>
			</sec:authorize> -->
			<td>${emp.id}</td> 
			<td>${emp.firstName} ${emp.lastName}</td>
			<td>todo: fix</td>
			<td>todo: fix</td>
	 		<td>todo: fix</td>  
			<td></th> <!--  Manager or Employee -->
	 		<td>${emp.skypeName}</td> 
	 		<td> ${emp.email}</td>
	</c:forEach> 
</table>

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