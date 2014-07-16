<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>

<h3>Job Titles</h3>

<div id="addBtn-container">
	<button type="button" id="addBtn" style="width: 45px;">Add</button>	
</div>

<div id="addEntity" style="display:none">
	<fieldset>
		<legend>Add Job Title</legend>
		<form id="newJobTitleForm" name="newJobTitle" action="titles" method="post">
			Job Title Name: <input type="text" name="name" required />
			<input type="hidden" name="isActive" value="true" />
			<button type="submit">Save</button>
			<button id="cancelButton" type="reset" value="Reset">Cancel</button>
		</form>
	</fieldset>
</div>

<br><br>

<table class="sortable">
	<thead>
		<th>Description</th><th>Edit</th>
	</thead>
	<c:forEach items="${titles}" var="title">
		<tr> 
			<td>${title.getName()}</td>
			<td>
				<button onclick="editJob('${title.getId()}', '${title.getName()}')">Edit</button>
			</td>
		</tr>
	</c:forEach>
	
</table>

<div id="editJobTitleContainer" style="display: none; ">
	<form id="editJobTitleForm" name="editJobTitle" action="titles" method="post">
		<input id="editName" type="text" name="name" />
		<input id="editId" type="hidden" name="id" />
		
		
		<button type="submit">Save</button>
		<button type="reset" id="cancelEdit">Cancel</button>
	</form>
</div>
