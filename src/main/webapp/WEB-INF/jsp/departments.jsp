<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>

<h3>Departments</h3> 
<table id="t1" class="pure-table pure-table-horizontal"> 
	<thead>
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
				<button onclick="editDepartment('${dept.getId()}', '${dept.getName()}', '${dept.getParentDepartment().getId()}')" class="pure-button">Edit</button>
			</td>
			<td>
				<button onclick="removeDepartment('${dept.getId()}')" class="pure-button">Delete</button>
			</td>
		</tr>
	</c:forEach> 
</table>

<br>

<div id="addBtn-container">
	<button class="pure-button" type="button" id="addBtn">Add</button>	
</div>

<div id="addEntity" style="display:none">
	<form class="pure-form pure-form-aligned" name="newDept" action="depts" method="post">
		<fieldset>
			<legend>Add Department</legend>
			
			<div class="pure-control-group">
				<label for="deptName">*Department Name</label>
				<input type="text" name="name" id="deptName">
			</div>
			
			<div class="pure-control-group">
				<label for="parentDept">*Department Name</label>
				<select name="parentDepartment.id" id="parentDept">
					<option value=null></option>
					<c:forEach items="${depts}" var="dept">
						<option value="${dept.getId()}">${dept.name}</option>
					</c:forEach>
				</select>
			</div>
			
			<input type="hidden" name="isActive" value="true" />
			
			<button class="pure-button pure-button-primary" type="submit">Save</button>
			<button class="pure-button" type="reset" id="cancelBtn">Cancel</button>
		</fieldset>
	</form>
	<p>Fields marked with a * are required</p>
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
