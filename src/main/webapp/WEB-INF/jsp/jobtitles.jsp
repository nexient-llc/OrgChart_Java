<!DOCTYPE HTML>

<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<h3>Job Titles</h3>

<div id="addBtn-container">
		<button type="button" id="addBtn" style="width: 45px;">Add</button>	
</div>

<div id="addEntity" style="display:none">
	<fieldset>
		<legend>
			Add Job Title
		</legend>
			<form name="newJob" action="jobs" method="post">
				<div>
					<labeL>Job Name:</labeL>
					<input type="text" name="name"/>
					<labeL>Job Description:</label>
					<input type="text" name="id"/>
					<button type="submit">Save</button>
				</div>
			</form>
	</fieldset>
</div>

<table id="t1">
	<tr>
		<th>Job Title</th>
		<!-- Change Job Id to Job Description Later -->
		<th>Job Id</th>
	</tr> 
	<c:forEach items="${jobs}" var="job">
		<tr> 
			<td>${job.name}</td> 
			<td>${job.description}</td> 
		</tr>
	</c:forEach> 
</table>