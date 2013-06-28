
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%> 
<%@ taglib prefix="sec"
 uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

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
		<th style="display: none"></th>
	</tr>
	<c:forEach items="${depts}" var="dept">
		<tr class="click_row" id="${dept.id}">
 			<!-- <sec:authorize access="hasRole('ROLE_ADMIN')">
				<td>delete</td>
			</sec:authorize> -->
			<td class="edit_icon" width="16 px" ></td>
			<td class="deptname" >${dept.name}</td> 
			<td class="deptparent" >${dept.parentDepartment.name}</td>
			<td class="deptparentid" style="display: none" >${dept.parentDepartment.id}</td>
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
				<form:form name="newDept" method="post" >
					<label>Dept Name:</label>
					<input type="text" name="name"/>
					<label>Parent Dept:</label>
					<select name="parentDepartment.id" id="put_parent_id" >
						<option value="0" >...</option>
						<c:forEach items="${depts}" var="dept">
							<option value="${dept.id}">${dept.name}</option>
						</c:forEach>
					</select>
					<button type="submit" id="saveBtn">Save</button>
				</form:form>
			</fieldset>
		</div>
		<div id="editEntity" >
			<fieldset>
				<legend>Edit Department</legend>
				<form:form name="updateDept" method="put" id="putDept">
					<label>Dept Name:</label>
					<input type="hidden" name="id" id="dept_put_id" />
					<input type="text" name="name" id="dept_put_name" />
					<label>Parent Dept:</label>
					<select name="parentDepartment.id" id="dept_put_parent_id">
						<option>...</option>
						<c:forEach items="${depts}" var="dept">
							<option value="${dept.id}">${dept.name}</option>
						</c:forEach>
					</select>
					<button type="submit">Update</button>
					</form:form>
			</fieldset>
			<fieldset>
				<legend id="delete_legend"></legend>
				<form:form method="delete" id="delDept">
					<input type="hidden" name="id" id="dept_del_id" />
					<button type="submit">Delete</button>
				</form:form>
			</fieldset>
		</div>
	</div>
</div>