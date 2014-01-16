<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<h3>Departments</h3>

<div id="addBtn-container">
	<button type="button" id="addBtn" style="width: 45px;">Add</button>
</div>


<div id="addEntity" style="display: none">
	<fieldset>
		<legend>Add Department</legend>
		<form name="newDept" action="depts" method="post">
			<div>
				<labeL>Dept Name:</labeL> <input type="text" name="name" /> <labeL>Parent
					Dept:</label> <select name="parentDepartment.id">
					<option></option>
					<c:forEach items="${depts}" var="dept">
						<option value="${dept.id}">${dept.name}</option>
					</c:forEach>
				</select>
				<button type="submit">Save</button>
				<button type="reset">Clear</button>
			</div>
			<div></div>
		</form>
	</fieldset>
</div>

<table id="t1">
	<tr>
		<th>Dept Name</th>
		<th>Parent Dept</th>
	</tr>

	<c:forEach items="${depts}" var="dept">
		<tr id="ViewDepts${dept.id}">
			<td>${dept.name}</td>
			<td>${dept.parentDepartment.name}</td>
			<td>
				<button type="button" class="editBtn" value="${dept.id}">Edit</button>
			</td>
		</tr>

		<tr id="EditDepts${dept.id}" style="display: none">
			<td><input path="name" id="deptName${dept.id}" value="${dept.name}"></td>
			<td><select path="parentDepartment.id" id="deptParentId${dept.id}">
					<option value="" label="" />
					<c:forEach items="${depts}" var="dept2">
						<c:choose>
							<c:when test="${dept.parentDepartment.id == dept2.id}">
								<option selected value="${dept2.id}">${dept2.name}</option>
							</c:when>
							<c:otherwise>
								<option value="${dept2.id}">${dept2.name} </option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
			</select></td>
			<td><button class='saveBtn' value="${dept.id}">Save</button></td>
			<td><button class='cancelBtn' value="${dept.id}">Cancel</button></td>
		</tr>
	</c:forEach>
	
</table>





<div id="editEntity" style="display: none">
	<fieldset>
		<legend>Edit</legend>
		<form:form modelAttribute="dept" action="depts" method="post">
			<input type="hidden" name="_method" value="put">
			<div>
				<labeL>Dept Name:</labeL>
				<form:input type="hidden" path="id" id="entityid" />
				<form:input type="text" path="name" />
				<labeL>Parent Dept:</label>
				<form:select path="parentDepartment.id">
					<form:option value="" label="" />
					<form:options items="${depts}" itemValue="id" itemLabel="name" />
				</form:select>
				<button type="submit">Save</button>
				<button type="reset">Clear</button>
			</div>
		</form:form>
	</fieldset>
</div>







