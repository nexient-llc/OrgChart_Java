<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<h3>Departments</h3> 
<table id="t1"> 
	<tr><!-- <sec:authorize access="hasRole('ROLE_ADMIN')"> -->
		<!-- <th>Task</th></sec:authorize> --> 
		<!--<th>Dept Id</th> -->
		<th>Dept Name</th> 
		<th>Parent Dept</th>
		<th>Dept Manager</th> 
		<th>Edit</th>
		<th>Remove</th>
		
	</tr> 
	<c:forEach items="${depts}" var="dept">
		<tr id ="${dept.id}" > 
			<!-- <sec:authorize access="hasRole('ROLE_ADMIN')">
				<td>delete</td>
			</sec:authorize> -->
			<!--<td>${dept.id}</td>-->
			<td>${dept.name}</td> 
			<td class ="${dept.parentDepartment.id}">${dept.parentDepartment.name}</td> 
			<td class ="${dept.departmentManager.id}">${dept.departmentManager.firstName}  ${dept.departmentManager.lastName} </td>
			<td><button type="button" class="editBtn" style="width: 45px;">Edit</button>	</td>
			<td>
			<form name="removeDepartment" action="removeDepartment" method="post">
					<input type="hidden" name=departmentId value= "${dept.id}"/>
					
					<button type="submit" id="removeBtn">Remove</button>
				</form>
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
		<div><labeL>Dept Name:</labeL><input type="text" name="name"/>
			<label>Parent Dept:</label>
			<select name="parentDepartmentId">
				<option value = "-1">...</option>
				<c:forEach items="${depts}" var="dept">
					<option value="${dept.id}">${dept.name}</option>
				</c:forEach>
			</select>
			<label>Dept Manager:</label>
			<select name="departmentManagerId">
			<option value ="-1">...</option>
			<c:forEach items="${employees}" var="employee">
					<option value="${employee.id}">${employee.firstName} ${employee.lastName}</option>
			</c:forEach>
			</select>
			<button type="submit">Save</button>
		</div>
		<div></div>
		</form>
		<button type="button" class ="cancelBtn">Cancel</button>
	</fieldset>
</div>

<div id="editEntity" style="display:none">
	<fieldset>
		<legend>Edit Department</legend>
		<form name="editDepts" action="editDepts" method="post">
		<div>
		<input type ="text" name="departmentId" id="editDepartmentId"/>
		<labeL>Dept Name:</labeL>
			<input type="text" name="departmentName" id="editDepartmentName"/>
		<label>Parent Dept:</label>
			<select name="parentDepartmentId" id = "editParentDepartmentId">
				<option value = "-1">...</option>
				<c:forEach items="${depts}" var="dept">
					<option value="${dept.id}">${dept.name}</option>
				</c:forEach>
			</select>
		<label>Dept Manager:</label>
			<select name="departmentManagerId" id = "editDepartmentManagerId">
			<option value ="-1">...</option>
			<c:forEach items="${employees}" var="employee">
					<option value="${employee.id}">${employee.firstName} ${employee.lastName}</option>
			</c:forEach>
			</select>
			<button type="submit">Save</button>
		</div>
		<div></div>
		</form>
		<button type="button" class ="cancelBtn">Cancel</button>
	</fieldset>
</div>

