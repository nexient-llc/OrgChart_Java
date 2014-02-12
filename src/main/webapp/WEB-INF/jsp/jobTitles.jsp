<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<h3>Job Titles</h3> 
<table id="t1"> 
	<tr><!-- <sec:authorize access="hasRole('ROLE_ADMIN')"> -->
		<!-- <th>Task</th></sec:authorize> --> 
		<!-- <th>Job Title ID</th>  -->
		<th>Job Title Name</th>
		<th>Edit</th>
		<th>Remove</th>
	</tr> 
	<c:forEach items="${jobTitles}" var="jobTitle">
		<tr id="${jobTitle.id}"> 
		
			<!-- <sec:authorize access="hasRole('ROLE_ADMIN')">
				<td>delete</td>
			</sec:authorize> -->
			<!-- <td>${jobTitle.id}</td> --> 
			<td class="jobTitleName">${jobTitle.name}</td> 
			<td>
				<button type="button" class="editBtn" style="width: 45px;" value="${jobTitle.id}" >Edit</button>
			</td>
			<td>
				<form name="removeJobTitle" action="removeJobTitle" method="post">
					<input type="hidden" name=jobTitleId value= "${jobTitle.id}"/>
					
					<button type="submit" id="removeBtn">Remove</button>
				</form>
			</td>
		</tr>
	</c:forEach> 
</table>

<div id="addBtn-container">
		<button type="button" id="addBtn" style="width: 45px;">Add</button>	
</div>

<div id="addEntity" style="display:none">
	<fieldset>
		<legend>Add Job Title</legend>
		<form name="newJobTitle" action="jobTitles" method="post">
		<div>
			<label>Job Title Name:</labeL>
			<input type="text" name="name"/>
			<button type="submit">Save</button>
		</div>
		</form>
		<button type="button" class ="cancelBtn">Cancel</button>
	</fieldset>
</div>
<div id="editEntity" style="display:none">
	<fieldset>
		<legend>Edit Job Title</legend>
		<form name="editJobTitle" action="editJobTitle" method="post">
		<div>
			<label>Job Title Name:</labeL>
			
			<input id="editJobtitleName" type="text" name="name"/>
			<input id="editJobTitleId" type= "text" name=jobTitleId />
			<button type="submit">Save</button>
		</div>
		</form>
		<button type="button" class="cancelBtn">Cancel</button>
	</fieldset>
</div>

