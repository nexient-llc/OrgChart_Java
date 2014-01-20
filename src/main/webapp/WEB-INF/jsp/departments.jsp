<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="sec"
        uri="http://www.springframework.org/security/tags"%>

<h3>Departments</h3> 
<div class="divContainer"> 
	<div style="overflow:hidden;">
		<!-- <sec:authorize access="hasRole('ROLE_ADMIN')"> -->
		<!-- <th>Task</th>
		</sec:authorize> --> 
		<div class="divHeader">Dept Name</div><!--
		--><div class="divHeader">Parent Dept</div>
	</div>
	<div style="overflow:hidden;">
		<c:forEach items="${depts}" var="dept">
			<div class="divRow"> 
				<!-- <sec:authorize access="hasRole('ROLE_ADMIN')">
					<td>delete</td>
				</sec:authorize> -->
				<div class="divColumn">${dept.name}</div> 
				<div class="divColumn">${dept.parentDepartment.name}</div>
			</div>
		</c:forEach> 
	</div>
</div>

<div id="addBtn-container">
	<button type="button" id="addBtn" style="width: 55px;">Add</button>        
</div>

<div id="addEntity" style="display:none;">
	<fieldset>
	    <legend>Add Department</legend>
		<form name="addDept" action="depts" method="post">
			<div>
				<label>Dept Name:</label><input type="text" name="name"/>
				<label>Parent Dept:</label>
				<select name="parentDepartmentId">
					<option value="">None</option>
					<c:forEach items="${depts}" var="dept">
						<option value="${dept.id}">${dept.name}</option>
					</c:forEach>
				</select>
				<button type="submit">Save</button>
				<button type="button" class="cancelBtn">Cancel</button>
			</div>
			<div></div>
		</form>
	</fieldset>
</div>

<div id="editBtn-container">
	<button type="button" id="editBtn" style="width:55px;">Edit</button>
</div>

<div id="editEntity" style="display:none">
	<fieldset>
		<legend>Edit Department</legend>
		<form name="editDept" action="depts" method="post">
			<input type="hidden" name="_method" value="put"/>
			<div>
				<label class="editSelect">Dept Name:</label>
				<select id="editSelectName" name="departmentId">
					<c:forEach items="${depts}" var="dept">
						<option value="${dept.id}">${dept.name}</option>
					</c:forEach>
				</select>
				
				<label class="editFields" style="width:79px; display:none;">New Name:</label>
				<input id="editNewNameField" type="text" name="name" style="display:none"/><br/>
				<label class="editFields" style="width:82px; display:none;">Parent Dept:</label>
				<select class="editFields" name="parentDepartmentId" style="display:none">
					<option value="">None</option>
					<c:forEach items="${depts}" var="dept">
						<option value="${dept.id}">${dept.name}</option>
					</c:forEach>
				</select><br/>
				<button type="submit" class="editFields" style="display:none; margin-top:8px">Save</button>
				<button type="button" id="selectBtn">Select</button>
				<button type="button" class="cancelBtn">Cancel</button>
			</div>
		</form>
	</fieldset>
</div>

<div id="deleteBtn-container">
	<button type="button" id="deleteBtn" style="width:55px;">Delete</button>
</div>

<div id="deleteEntity" style="display:none">
	<fieldset>
		<legend>Remove Department</legend>
		<form name="deleteDept" action="depts" method="post">
			<input type="hidden" name="_method" value="delete"/>
			<div>
				<label>Dept Name:</label>
				<select name="departmentId">
					<c:forEach items="${depts}" var="dept">
						<option value="${dept.id}">${dept.name}</option>
					</c:forEach>
				</select>
				<button type="submit">Remove</button>
				<button type="button" class="cancelBtn">Cancel</button>
			</div>
		</form>
	</fieldset>
</div>
