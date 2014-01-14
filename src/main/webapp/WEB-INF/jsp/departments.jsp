<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<h3>Departments</h3> 
<table id="t1"> 
	<tr><sec:authorize access="hasRole('ROLE_ADMIN')">
		<th>Task</th></sec:authorize>
		<th>Dept Name</th> <th>Parent Dept</th>
	</tr> 
	<c:forEach items="${depts}" var="dept">
		<tr> 
		<div id="editBtn-container${dept.id}">
			<sec:authorize access="hasRole('ROLE_ADMIN')">
				<td>delete</td>
			</sec:authorize>
			<td>${dept.name}</td> 
			<td>${dept.parentDepartment.name}</td> 
			<td>
			<button type="button" id="editBtn${dept.id}" style="width: 45px;" onclick = "editButton(${dept.id})">Edit</button>
			</td>	
			</div>
		</tr>
	</c:forEach> 
</table>

<div id="addBtn-container">
		<button type="button" id="addBtn" style="width: 45px;">Add</button>	
</div>

<div id="addEntity" style="display:none">
	<fieldset>
		<legend>Add Department</legend>
		<form name="newDept" action="depts" method="post">
		<div><labeL>Dept Name:</labeL>
		
		<input type="text" name="name"/>
			<labeL>Parent Dept:</label>
			<select name="parentDepartment.id">
				<option>...</option>
				<c:forEach items="${depts}" var="dept">
					<option value="${dept.id}">${dept.name}</option>
				</c:forEach>
			</select>
			<button type="submit">Save</button>
			<button type="reset">Clear</button>
		</div>
		<div></div>
		</form>
	</fieldset>
</div>


<div id="editEntity" style="display:none">
	<fieldset>
		<legend>Edit</legend>
	<form:form modelAttribute="dept" action="depts" method="put">
		<div><labeL>Dept Name:</labeL>
		<form:input type="hidden" path="id"/>
		<form:input type="text" path="name"/>
			<labeL>Parent Dept:</label>
			<form:select path="parentDepartment.id">
			<form:option value="" label="" />
			<form:options items="${depts}" itemValue="id" itemLabel="name" />
			</form:select>
			<button type="submit">Save</button>
			<button type="reset">Clear</button>
		</div>
	</form:form>
	</fieldset>
</div>

  
  <!--  
<script>
function editEntity(id)
{
alert("${id}");
}
</script>

-->







