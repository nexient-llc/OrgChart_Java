<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<h3>Departments</h3> 

<div id="addBtn-container">
		<button type="button" id="addBtn">Add</button>	
</div>
<div id="addEntity" style="display:none">
	<fieldset>
		<legend>Add Department</legend>
		<form:form modelAttribute="newDept" action="depts" method="post">		
			<div>
				<label>Dept Name:</label>
				<form:input path="name"/>
				<label>Parent Dept:</label>
				<form:select path="parentDepartment.id">
					<form:option value="" />
					<form:options items="${depts}" itemValue="id" itemLabel="name"/>
				</form:select>
				<input type="submit" value="Save" />
				<button id="cancelAddBtn">Cancel</button>
			</div>
		<div></div>
		</form:form>
	</fieldset>
</div>

<table id="t1"> 
	<tr><!-- <sec:authorize access="hasRole('ROLE_ADMIN')"> -->
		<!-- <th>Task</th></sec:authorize> --> 
		<th>Dept Name</th> <th>Parent Dept</th> <th></th> <th></th>
	</tr> 
	<c:forEach items="${depts}" var="dept">
		<tr id="deptRow${dept.id}"> 
			<!-- <sec:authorize access="hasRole('ROLE_ADMIN')">
				<td>delete</td>
			</sec:authorize> -->
			<td class="deptName" data-value="${dept.name}">${dept.name}</td>
			<td class="deptParent" data-value="${dept.parentDepartment.id}">${dept.parentDepartment.name}</td> 
			<td><button class="editDeptBtn" value="${dept.id}">Edit</button></td>
			<td><button class="removeDeptBtn" value="${dept.id}">Remove</button></td>			
		</tr>
		
		<tr id="editDeptRow${dept.id}" style="display:none">
			<td><input name="name" class="editDeptName"/></td>
			<td><select name="parentDepartment.id" class="editDeptParent">
					<option value="" />
					<c:forEach items="${depts}" var="pDept">
						<option value="${pDept.id}">${pDept.name}</option>
					</c:forEach>
				</select>
			</td>
			<td><button class="saveDeptBtn" value="${dept.id}">Save</button></td>
			<td><button class="cancelDeptEditBtn" value="${dept.id}">Cancel</button></td>
		</tr>
	</c:forEach> 
</table>