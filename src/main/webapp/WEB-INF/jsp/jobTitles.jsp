<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>

<h3>Job Titles</h3>

<div id="addBtn-container">
	<button class="pure-button" type="button" id="addBtn">Add</button>	
</div>

<div id="addEntity" style="display:none">
	<form id="newJobTitleForm" class="pure-form" name="newJobTitle" action="titles" method="post">
		<fieldset>
			<legend>Add Job Title</legend>
			<label for="jobName">Job Title Name</label>
			<input type="text" id="jobName" name="name" required >
			<button class="pure-button pure-button-primary" type="submit">Save</button>
			<button class="pure-button" id="cancelButton" type="reset" value="Reset">Cancel</button>
		</fieldset>
	</form>
</div>

<br><br>

<table class="pure-table pure-table-horizontal">
	<thead>
		<th>Description</th><th>Edit</th>
	</thead>
	<c:forEach items="${titles}" var="title">
		<tr> 
			<td>${title.getName()}</td>
			<td>
				<button class="pure-button" onclick="editJob('${title.getId()}', '${title.getName()}')">Edit</button>
			</td>
		</tr>
	</c:forEach>
	
</table>

<div id="editJobTitleContainer" style="display: none; ">
	<form id="editJobTitleForm" class="pure-form" name="editJobTitle" action="titles" method="post">
		<fieldset>
			<legend>Edit Job Title</legend>
			<label for="editName">Name</label>
			<input id="editName" type="text" name="name" >
			<input id="editId" type="hidden" name="id" />
			<button class="pure-button pure-button-primary" type="submit">Save</button>
			<button class="pure-button" type="reset" id="cancelEdit">Cancel</button>
		</fieldset>
	</form>
</div>
