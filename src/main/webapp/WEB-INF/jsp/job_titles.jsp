<!DOCTYPE html>
<%@ taglib prefix="c"    uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="sec"  uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<h3>Job Titles</h3> 
<table id="t1"> 
	<tr>
		<th colspan="2">Task</th>
		<th>Name</th>
	</tr> 
	<c:forEach items="${jobs}" var="job">
		<tr>
			<td>
			<form action="<c:url value='/app/jobs/${job.id}'/>" method="post">
				<input type="hidden" name="_method" value="DELETE" />
				<input type="submit" value="delete" />
			</form>
			</td>
			<td>
			<form action="<c:url value='/app/jobs/edit/${job.id}'/>" method="get">
				<input type="submit" value="edit" />
			</form>
			</td>
			<td>${job.name}</td> 
		</tr>
	</c:forEach> 
</table>

<div id="addBtn-container">
		<button type="button" id="addBtn" style="width: 45px;">Add</button>	
</div>

<div id="addEntity" style="display:none">
	<fieldset>
		<legend>Add Job Title</legend>
		<form:form modelAttribute="modelJob" action="jobs" method="post">
		<div>
			<labeL>Name:</labeL>
			<form:input path="name" type="text" />
			
			<button type="submit">Save</button>
		</div>
		</form:form>
	</fieldset>
</div>