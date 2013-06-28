<!DOCTYPE HTML>

<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<h1>Systems In Motion Organization Chart</h1>
<h3>Job Titles</h3>

<div id="addBtn-container">
		<button type="button" id="addBtn" style="width: 45px;">Add</button>	
</div>

<div id="addEntity" style="display:none">
	<fieldset>
		<legend>
			Add New Job Title
		</legend>
			<form name="newJob" action="jobs" method="post">
				<div>
					<labeL>Job Name *</labeL>
					<input type="text" name="name"/>
					<labeL>Job Description *</label>
					<input type="text" name="description"/>
					<button type="submit">Save</button>
					<!-- Customize Footer Later -->
					<footer>Required Fields indicated with a *</footer>
				</div>
			</form>
	</fieldset>
</div>

<table id="t1">
	<tr>
		<th>Job Description</th>
	</tr> 
	<c:forEach items="${jobs}" var="job">
		<tr> 
			<td>${job.description}</td> 
		</tr>
	</c:forEach> 
</table>