
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%> 
<%@ taglib prefix="sec"
 uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>

<h3>Departments</h3> 
<div class="column">
	<table id="t1"> 
	<tr >
 		<th ><!-- <sec:authorize access="hasRole('ROLE_ADMIN')"> -->
		<!--  </sec:authorize>--> 
		</th>
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
	&nbsp;&nbsp;
</div>
<div class="column">
	<div id="toggleCrudBtn-container">
			<button type="button" id="toggleCrudBtn" style="width: 5em">Add</button>	
	</div>
	<div id="crudform" >
		<div id="addEntity" >
			<fieldset>
				<legend>Add Department</legend>
				<form name="newDept" action="depts" method="post">
				<div>
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
		<div id="editEntity" >
			<fieldset>
				<legend>Edit Department</legend>
				<form name="editDept" action="depts" method="post">
				<div>
					<label>Dept Name:</label><input type="text" name="name" id="dept_input" />
					<label>Parent Dept:</label>
					<select name="parent_id">
						<option>...</option>
						<c:forEach items="${depts}" var="dept">
							<option value="${dept.id}">${dept.name}</option>
						</c:forEach>
					</select>
					<button type="submit">Save</button>
					<br />
				</div>
				<div></div>
				</form>
			</fieldset>
			<fieldset>
				<legend id="delete_legend">Delete</legend>
				<form name="deleteDept" action="depts" method="post">
					<button type=button id="deleteBtn" >Delete</button>
				</form>
			</fieldset>
		</div>
	</div>
</div>