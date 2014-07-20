<!DOCTYPE html>

<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec"
uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

Admin

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
