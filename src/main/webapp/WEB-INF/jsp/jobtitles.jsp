<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
 <link rel="stylesheet" href="//code.jquery.com/ui/1.11.2/themes/smoothness/jquery-ui.css">
  <script src="//code.jquery.com/jquery-1.10.2.js"></script>
  <script src="//code.jquery.com/ui/1.11.2/jquery-ui.js"></script>
  
   <style>
	.ui-dialog-titlebar-close {
	  visibility: hidden;
	}
	</style>
	
<h3>Job Titles</h3> 
<sec:authorize access="hasRole('ROLE_ADMIN')">
<div id="addBtn-container">
		<button type="button" id="addBtn" style="width: 45px;">Add</button>	
</div><br>
</sec:authorize>
<table id="t1"> 
	<tr><!-- <sec:authorize access="hasRole('ROLE_ADMIN')"> -->
		<!-- <th>Task</th></sec:authorize> --> 
		<th>Job Title</th><sec:authorize access="hasRole('ROLE_ADMIN')"><th>Edit</th></sec:authorize>
	</tr> 
		<c:forEach items="${jobs}" var="job">
					<tr>
						<!-- <sec:authorize access="hasRole('ROLE_ADMIN')">
							<td>delete</td>
						</sec:authorize> -->
						<td>${job.name}</td>
						<sec:authorize access="hasRole('ROLE_ADMIN')"><td><button class="editButton" value="${job.id}">Edit</button></td></sec:authorize>
					</tr>
		</c:forEach>
</table>

<br>
<div id="dialog-form-make" title="Add New Job Title">
	<fieldset>
		<form name="newJob" action="jobs" method="post">
		<br>
		<div><labeL>*Job Title:</labeL>
			<input type="text" name="name" required max=1/><br>
			<p>Required Fields indicated with a *</p>
			<button type="submit">Save</button>
			<button type="reset" name="resetMakeBtn" id="resetMakeBtn">Cancel</button>
				</div>
			<div></div>
			</form>
		</fieldset>
</div>

<div id="dialog-form-edit" title="Edit Job Title">
		<fieldset>
			<form name="editJobs" action="jobs" method="post">
			<br>
				<input type="hidden" id="jobTitleId" name="id"/>
					<div>
						<labeL>* Job Title:</labeL> 
							<input type="text" name="name" id="jobTitleName" required /> <br><p></p>
							<p>Required Fields indicated with a *</p>
						<button type="submit">Save</button>
						<button type="reset" name="resetEditBtn" id="resetEditBtn">Cancel</button>
					</div>
				<div></div>
			</form>
		</fieldset>
</div>