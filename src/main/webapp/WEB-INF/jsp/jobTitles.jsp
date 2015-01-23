<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
	
<h3>Jobs</h3> 
<div id="addBtn-container">
		<button type="button" id="addBtn" style="width: 45px;">Add</button>	
</div>

<table id="t1">
	<tr><!-- <sec:authorize access="hasRole('ROLE_ADMIN')"> -->
		<!-- <th>Task</th></sec:authorize> --> 
		<th>Job Title</th> <th></th>
	</tr> 
	<c:forEach items="${jobTitles}" var="jobTitle">
		<tr> 
			<!-- <sec:authorize access="hasRole('ROLE_ADMIN')">
				<td>delete</td>
			</sec:authorize> -->
			<td>${jobTitle.name}</td> 
			<td>
				<div class="editBtn-container">
			    	<button type="button" id=${jobTitle.id} class="editBtn" onClick="editJob(${jobTitle.id})" style="width: 45px;">Edit</button>	
            	</div>
            </td>
        </tr>
	</c:forEach> 
</table>

<div id="addEntity" style="display:none">
	<fieldset>
		<legend>Add New Job Title</legend>
		<form name="newJob" action="jobs" method="post">
		<div><labeL>Job Title Description*:</labeL><input type="text" name="name" required />
			<button type="submit">Save</button>
			<button type="reset" onClick="toggleSave()">Cancel</button>
		</div>
		<div></div>
		<footer>Required Fields indicated with a *</footer>
		</form>
	</fieldset>
</div>

<c:forEach items="${jobTitles}" var="jobTitle">
<div id="editEntity${jobTitle.id}" style="display:none">
	<fieldset>
		<legend>Edit Job Title</legend>
		<form name="editJobTitle" action="editJob" method="post">
		<input type="text" name="id" value="${jobTitle.id}" style="display:none"/>
		<div><labeL>Job Title*:</labeL><input type="text" name="name" required value="${jobTitle.name}" />
			<button type="submit">Save</button>
			<button type="reset" onClick="editJob(${jobTitle.id})">Cancel</button>
		</div>
		<div></div>
		<footer>Required Fields indicated with a *</footer>
		</form>
	</fieldset>
</div>
</c:forEach>
