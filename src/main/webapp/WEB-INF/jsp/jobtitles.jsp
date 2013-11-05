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
	</tr> 
	
	<!--  Rows -->
	<c:forEach items="${jobs}" var="job">
		<tr> 
			<!-- <sec:authorize access="hasRole('ROLE_ADMIN')">
				<td>delete</td>
			</sec:authorize> -->
			<td>${job.id}</td> 
			<td>${job.name}</td>
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