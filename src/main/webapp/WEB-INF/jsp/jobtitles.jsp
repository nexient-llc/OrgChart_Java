<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<h3>Job Titles</h3>
<table id="t1"> 
	<tr><!-- <sec:authorize access="hasRole('ROLE_ADMIN')"> -->
		<!-- <th>Task</th></sec:authorize> --> 
		<th>Job Title</th> 
	</tr> 
	<c:forEach items="${jobs}" var="job">
		<tr> 
			<!-- <sec:authorize access="hasRole('ROLE_ADMIN')">
				<td>delete</td>
			</sec:authorize> -->
				<td>${job.description}</td> 
			</tr>
	</c:forEach> 
</table>

<div id="addBtn-container">
		<button type="button" id="addBtn" style="width: 45px;">Add</button>	
</div>

<div id="addEntity" style="display:none">
	<fieldset>
		<legend>Add Job Title</legend>
		<form name="newJob" action="jobs" method="post">
		<div><labeL>Job Title:</labeL><input type="text" name="description"/>
			<button type="submit">Save</button>
		</div>
		<div></div>
		</form>
	</fieldset>
</div>