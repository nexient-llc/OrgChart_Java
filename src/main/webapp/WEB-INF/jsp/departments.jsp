<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>

<h3>Departments</h3> 
<table id="t1" class="sortable"> 
	<thead><!-- <sec:authorize access="hasRole('ROLE_ADMIN')"> -->
		<!-- <th>Task</th></sec:authorize> --> 
		<th>Dept Name</th><th>Parent Dept</th><th>Edit</th><th>Delete</th>
	</thead> 
	<c:forEach items="${depts}" var="dept">
		<tr id="deptRow${dept.getId()}"> 
			<!-- <sec:authorize access="hasRole('ROLE_ADMIN')">
				<td>delete</td>
			</sec:authorize> -->
			<td>${dept.name}</td> 
			<td>${dept.parentDepartment.name}</td>
			<td>
				<button onclick="editDepartment('${dept.getId()}', '${dept.getName()}', '${dept.getParentDepartment().getId()}')">Edit</button>
			</td>
			<td>
				<button onclick="removeDepartment('${dept.getId()}')">Delete</button>
			</td>
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
			*Dept Name: <input type="text" name="name" />
			Parent Dept: 
			<select name="parentDepartment.id">
				<option value=null></option>
				<c:forEach items="${depts}" var="dept">
					<option value="${dept.getId()}">${dept.name}</option>
				</c:forEach>
			</select>
			
			<input type="hidden" name="isActive" value="true" />
			
			<button type="submit">Save</button>
			<button type="reset" id="cancelBtn">Cancel</button>
		</form>
		<p>Fields marked with a * are required</p>
	</fieldset>
</div>

<div id="editEntity" style="display: none; ">
	<fieldset>
		<legend>Edit Department</legend>
		<form name="editDept" action="depts" method="post">
			*Dept Name: <input id="editDeptName" type="text" name="name" />
			Parent dept: 
			<select id="editParentDept" name="parentDepartment.id">
				<option value=""></option>
				<c:forEach items="${depts}" var="dept">
					<option value="${dept.getId()}">${dept.getName()}</option>
				</c:forEach>
			</select>
			
			<input id="editDepartmentId" type="hidden" name="id" value="${dept.getId()}" />
			
			<button type="submit">Save</button>
			<button id="cancelEdit" type="reset">Cancel</button>
		</form>
	
	</fieldset>
</div>
