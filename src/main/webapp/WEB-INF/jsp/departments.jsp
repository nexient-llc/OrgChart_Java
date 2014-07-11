<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<h3>Department Page</h3> 

<div id="addBtn-container">
		<button type="button" id="addBtn" style="width: 45px;">Add</button>	
</div>

<div id="addEntity-container" style="display:none">
	<fieldset>
		<legend>Add New Department</legend>
		<form name="newDept" action="depts" method="post">
		<div><labeL>*Department Name:</labeL><input type="text" name="name" required/>
			<labeL>Parent Department:</label>
			<select name="parentDepartment.id">
				<option>...</option>
				<c:forEach items="${depts}" var="dept">
					<option value="${dept.id}">${dept.name}</option>
				</c:forEach>
			</select>
			<button type="submit">Save</button>
			<button type="reset" id="cancelAddButton">Cancel</button>
			Required Fields indicated with a *
		</div>
		<div></div>
		</form>
	</fieldset>
</div>
<div id="deptTable-container">
	<table id="t1"> 
		<tr><!-- <sec:authorize access="hasRole('ROLE_ADMIN')"> -->
			<!-- <th>Task</th></sec:authorize> --> 
			<th>Department Name </th> <th> Parent Department Name</th><th></th>
		</tr> 
		<c:forEach items="${depts}" var="dept">
			<tr> 
				<!-- <sec:authorize access="hasRole('ROLE_ADMIN')">
					<td>delete</td>
				</sec:authorize> -->
				<td>${dept.name}</td> 
				<td>${dept.parentDepartment.name}</td> 
				<td><button class ="editButton" value ="${dept.id}">Edit</button></td>
				</tr>
		</c:forEach> 
	</table>
</div>
<div id="editEntity-container" style="display:none">
	<fieldset>
		<legend>Edit Department</legend>
		<form name="editDept" action="depts" method="put">
			<input type="hidden" id="departmentId"/>
			<div>
				<labeL>*Department Name:</labeL>
				<input type="text" name="name" id="departmentName" required/>
				<labeL>Parent Department:</label>
				<select name="parentDepartment.id" id="parentDepartment">
					<option>...</option>
					<c:forEach items="${depts}" var="dept">
						<option value="${dept.id}">${dept.name}</option>
					</c:forEach>
				</select>
				<button type="submit">Save</button>
				<button type="reset" id="cancelEditButton">Cancel</button>
				<button id="removeButton">Remove</button>
				Required Fields indicated with a *
			</div>
			<div></div>
		</form>
	</fieldset>
</div>

