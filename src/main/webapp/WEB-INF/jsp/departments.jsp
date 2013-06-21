
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>

<h3>Departments</h3> 
<div class="column">
	<table id="t1"> 
	<tr >
 	<!-- <sec:authorize access="hasRole('ROLE_ADMIN')"> -->
		<th ></th><!--  </sec:authorize>--> 
		<th>Dept Name</th>
		<th>Parent Dept</th>
	</tr>
	<c:forEach items="${depts}" var="dept">
		<tr class="click_row" > 
 			<!-- <sec:authorize access="hasRole('ROLE_ADMIN')">
				<td>delete</td>
			</sec:authorize> -->
			<td class="edit_icon" width="16 px" ></td>
			<td class="deptname" >${dept.name}</td> 
			<td>${dept.parentDepartment.name}</td>
			</tr>
	</c:forEach> 
</table>
</div>
<div class="column">
	<div id="addBtn-container">
			<button type="button" id="addBtn" style="width: 45px;">Add</button>	
	</div>
	<div id="addEntity" style="display:none">
		<fieldset>
			<legend>Add Department</legend>
			<form name="newDept" action="depts" method="post">
			<div>
				<button type=button id="cancelBtn">Cancel</button>
				<label>Dept Name:</label><input type="text" name="name"/>
				<label>Parent Dept:</label>
				<select name="parent_id">
					<option>...</option>
					<c:forEach items="${depts}" var="dept">
						<option value="${dept.id}">${dept.name}</option>
					</c:forEach>
				</select>
				<button type="submit">Save</button>
			</div>
			<div></div>
			</form>
		</fieldset>
	</div>
	<div id="editEntity" style="display:none">
		<fieldset>
			<legend>Edit Department</legend>
			<form name="editDept" action="depts" method="post">
			<div>
				<button type=button id="cancelBtn">Cancel</button>
				<label>Dept Name:</label><input type="text" name="name" id="dept_input" />
				<label>Parent Dept:</label>
				<select name="parent_id">
					<option>...</option>
					<c:forEach items="${depts}" var="dept">
						<option value="${dept.id}">${dept.name}</option>
					</c:forEach>
				</select>
				<button type="submit">Save</button>
			</div>
			<div></div>
			</form>
		</fieldset>
	</div>
</div>