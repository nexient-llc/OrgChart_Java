<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<h3>Job Titles Page</h3>

<div id="addBtn-container">
		<button type="button" id="addBtn" style="width: 45px;">Add</button>	
</div>

<div id="addEntity" style="display:none">
	<fieldset>
		<legend>Add Job Title</legend>
		<form name="newJob" id="newJob" action="jobs" method="post">
			<div>
				<labeL>job Title <span style="color:red">*</span>:</labeL>
				<input type="text" name="name" id="name"/>
				<button type="submit">Save</button>
				<button type="button" id="rstBtn" onClick="resetForm();">Clear</button>
			</div>
		</form>
		Required Fields indicated with a <span style="color:red">*</span>
	</fieldset>
</div>

<div id="t1"> 
	<div class="row">
		<span class="head, cell">Job Title</span> <!-- Remove this --><span class="head, cell" id="taskHead">Task</span><!-- Remove this -->
		<!-- <sec:authorize access="hasRole('ROLE_ADMIN')"> -->
		<!-- <th id="taskHead">Task</th></sec:authorize> --> 
	</div> 
	<c:forEach items="${jobs}" var="job">
		<span id="row${job.id}" class="row">
			<span id="innerRow${job.id}" class="row">
				<span class="cell"> ${job.name}</span> 
				<span class="cell">
					<span id="button-container${job.id}">
						<button type="button" id="editButton" onClick='editJobTitle(${job.id}, "${job.name}")'>Edit</button>
						<!-- <sec:authorize access="hasRole('ROLE_ADMIN')">
							<td>delete</td>
						</sec:authorize> -->
					</span>
				</span>
			</span>
		</span>
	</c:forEach> 
</div>

<div id="jobTitleEditEntity" style="display:none">
	<fieldset>
		<legend>Edit Job Title</legend>
		<form name="editJob" id="editJob" action="jobs" method="post">
			<input type="hidden" name="_method" value="PUT">
			<span>
				<labeL>job Title <span style="color:red">*</span>:</labeL>
				<input type="text" name="name" id="editName" value=""/>
				
				<button type="submit">Save</button>
				<button type="button" onClick="closeEdit();">Clear</button>
			</span>
		</form>
		Required Fields indicated with a <span style="color:red">*</span>
	</fieldset>
</div>