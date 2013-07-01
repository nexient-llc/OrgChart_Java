<!DOCTYPE HTML>

<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<h1>Systems In Motion Organization Chart</h1>
<h3>Job Titles</h3>

<!-- ADD BUTTON -->
<div id="addBtn-container">
		<button type="button" id="addBtn" style="width: 45px;">Add</button>	
</div>

<!-- ADD JOB TITLE FORM -->
<div id="addEntity" style="display:none">
	<fieldset>
		<legend>
			Add New Job Title
		</legend>
			<form name="newJob" action="jobAdd" method="post">
				<div>
					<labeL>Job Name *</labeL>
					<input type="text" name="name"/>
					<labeL>Job Description *</label>
					<input type="text" name="description"/>
					<button type="submit">Save</button>
					<!-- Customize Footer Later -->
					<button type="reset" id="cancelBtn">Cancel</button>
					<footer>Required Fields indicated with a *</footer>
				</div>
			</form>
	</fieldset>
</div>

<!--  EDIT JOB TITLE FORM -->
<div id ="editEntity" style="display:none">
	<fieldset>
		<legend>
			Edit Job Title
		</legend>
			<form:form id = "editJob" action="jobUpdate" method="put">
				<div>
					<input id = "jobId" type="hidden" name="id"/>
					<label>Job Name *</label>
					<input type="text" name="name"/>
					<labeL>Job Description *</label>
					<input type="text" name="description"/>
					<button type="submit">Save</button>
					<button type="reset" id="cancelEditBtn">Cancel</button>
					<footer>Required Fields indicated with a *</footer>
				</div>
			</form:form>
	</fieldset>
</div>

<div id="deleteEntity" style ="display:none">

	<fieldset>
	<legend>Delete Job Title</legend>
	<form:form id = "deleteJob" action="jobDelete" method ="delete">
	<div>
	<input id="jobIdDelete" type="hidden" name="id"/>
	<button type="submit">Delete</button>
	<button type="reset" class="cancelEditBtn">Cancel</button>
	</div>
	</form:form>
		
	</fieldset>

</div>

<table id="t1">
	<tr>
		<th>Job Name</th>
		<th>Job Description</th>
	</tr> 
	<c:forEach items="${jobs}" var="job">
		<tr> 
			<td>${job.name}</td>
			<td>${job.description}</td>
			
			<td><button value = "${job.id}" type="button" class="editBtn">Edit</button></td>
			<td><button value = "${job.id}" type="button" class="deleteBtn">Delete</button></td>
		</tr>
	</c:forEach> 
</table>