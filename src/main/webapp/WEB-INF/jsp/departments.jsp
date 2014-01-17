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
				<form:input path="name" required="true"/>
				<label>Parent Dept:</label>
				<form:select path="parentDepartment.id">
					<form:option value="" />
					<c:forEach items="${depts}" var="tDept">
						<form:option value="${tDept.id}" label="${tDept.name}"/>
					</c:forEach>
				</form:select>
				<input type="submit" value="Save" />
				<button id="cancelAddBtn">Cancel</button>
			</div>
		</form:form>
	</fieldset>
</div>

<table id="t1"> 
	<tr id="th" class="activeTH"><!-- <sec:authorize access="hasRole('ROLE_ADMIN')"> -->
		<!-- <th>Task</th></sec:authorize> --> 
		<th>Department Name</th> <th>Parent Department</th> <th></th> <th></th>
	</tr> 
	<tr id="thEdit" style="display:none"><th>Department Name*</th> <th>Parent Department</th> <th>*=required</th> <th></th></tr>
	<c:forEach items="${depts}" var="dept">
		<tr id="deptRow${dept.id}"> 
			<!-- <sec:authorize access="hasRole('ROLE_ADMIN')">
				<td>delete</td>
			</sec:authorize> -->
			<td class="deptName" data-value="${dept.name}">${dept.name}</td>
			<td class="deptParent" data-value="${dept.parentDepartment.id}">${dept.parentDepartment.name}</td> 
			<td><button class="editBtn" value="${dept.id}">Edit</button></td>
			<td><button class="removeBtn" value="${dept.id}">Remove</button></td>			
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
			<td><button class="saveBtn" value="${dept.id}">Save</button></td>
			<td><button class="cancelEditBtn" value="${dept.id}">Cancel</button></td>
		</tr>
	</c:forEach> 
</table>