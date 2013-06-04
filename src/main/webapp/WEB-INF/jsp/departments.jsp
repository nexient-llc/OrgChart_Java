<!DOCTYPE html>
<%@ taglib prefix="c"    uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="sec"  uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!-- Form for updating existing departments.
Needs to exist before the edit buttons can
reference it. -->
<div id="greyOut" class="greyOut">
	<div id="editForm" class="editForm">
		<form:form id="editEntity" modelAttribute="modelDept" method="post">
			<!-- POST to UPDATE translation -->
			<input type="hidden" name="_method" value="UPDATE" />
			
			<!-- Must include all data of the model -->
			<form:input type="hidden" id="editId" path="id" />
			
			<labeL>Dept Name:</labeL>
			<form:input id="editName" path="name" type="text" />
			
			<labeL>Parent Dept:</label>
			<form:select id="editParent" path="parentDepartment">
				<option id="dotdotdot" value="-1">...</option>
				<c:forEach items="${depts}" var="dept">
					<option id="editParent${dept.id}" value="${dept.id}">${dept.name}</option>
				</c:forEach>
			</form:select>
			
			<input type="submit" value="update" />
			<input type="button" value="cancel" onClick="document.getElementById("greyOut").display = "none";"/>
		</form:form>
	</div>
</div>

<script type="text/javascript" src="/orgchart/scripts/popupEditor.js"></script>

<h3>Departments</h3>
<table id="t1">
	<tr>
		<th>Task</th>
		<th>Dept Name</th>
		<th>Parent Dept</th>
	</tr> 
	<c:forEach items="${depts}" var="dept">
		<tr>
			<td>
				<form action="<c:url value='/app/depts/${dept.id}'/>" method="post">
					<input type="hidden" name="_method" value="DELETE" />
					<input type="submit" value="delete" />
				</form>
				<button type="button" onClick="initEditor()">edit</button>
			</td>
			<td>${dept.name}</td> 
			<td>${dept.parentDepartment.name}</td> </tr>
	</c:forEach>
</table>

<div id="addBtn-container">
		<button type="button" id="addBtn" style="width: 45px;">Add</button>	
</div>

<div id="addEntity" style="display:none">
	<fieldset>
	<legend>Add Department</legend>
	<form:form action="/app/depts" modelAttribute="modelDept" method="post">
	<div>
		<!-- Must include all data of the model -->
		<form:input type="hidden" path="id" value="-1" />
		
		<labeL>Dept Name:</labeL>
		<form:input path="name" type="text" />
		
		<labeL>Parent Dept:</label>
		<form:select path="parentDepartment">
			<option value="-1">...</option>
			<c:forEach items="${depts}" var="dept">
				<option value="${dept.id}">${dept.name}</option>
			</c:forEach>
		</form:select>
		
		<input type="submit" value="Save">
	</div>
	</form:form>
	</fieldset>
</div>



<script type="text/javascript" src="/orgchart/scripts/popupEditor.js"></script>
<c:forEach items="${depts}" var="dept">
	<script>
		var deptParenId = "${dept.parentDepartment.id}";
		deptParenId = (deptParenId == "") ? -1 : Number(deptParenId);
		attachEditButton(deptParenId, ${dept.id}, "${dept.name}")
	</script>
</c:forEach>
<script>
	var el = document.getElementById("closeEditor");
	if (el.addEventListener)
		el.addEventListener("click", closeEditor, false);
	else if (el.attachEvent)
		el.attachEvent('onclick', closeEditor);
</script>

