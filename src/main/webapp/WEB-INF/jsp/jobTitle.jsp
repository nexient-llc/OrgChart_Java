<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<h3>Job Titles</h3> 
<div id="addBtn-container">
		<button type="button" id="addBtn" style="width: 45px;">Add</button>	
</div>
<div id="addEntity" style="display:none">
	<fieldset>
		<legend>Add Job Title</legend>
		<form name="newTitle" action="titles" method="post">
		<div><labeL>*Description:</labeL><input type="text" name="name" required/>
			<button type="submit">Save</button>
			<button id="cancel" type="reset" value="reset" >Cancel</button>
		</div>
		<div></div>
		</form>
	</fieldset>
	
</div>
<div class="editClass" id="editEntity" style="display: none">
	<fieldset>
		<legend>Edit Job Title</legend>
		<form:form name="Job Title " action="titles" method="post">
			<div>  
				<labeL>Description:</labeL><input id="job" type="text" name="name" />
				<input type="hidden" id="titleId" name="id"/>
				<button type="submit">Save</button>
				<button id="cancelEdit" type="reset" value="reset" >Cancel</button>
			</div>
			<div></div>
		</form:form>
	</fieldset>
</div>
<table id="t1"> 
	<tr><!-- <sec:authorize access="hasRole('ROLE_ADMIN')"> -->
		<!-- <th>Task</th></sec:authorize> --> 
		<th>Description</th>
	</tr> 
	<c:forEach items="${titles}" var="title">
		<tr class="jobTitleClass" > 
			<td class="JobTitleName">${title.name}</td> 
			<td>
			<button class="editButton" value="${title.id}" id="editEntity" style="width: 60px;">Edit</button>
			</td> 
		 </tr>
	</c:forEach> 
</table>


