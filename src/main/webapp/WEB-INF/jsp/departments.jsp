<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<h3>Departments</h3> 

<button id="addBtn">Add</button>
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

<div id="t1"> 
	<div id="th" class="activeTH row headers">
		<div class="inputCol">Department Name</div> <div class="selectCol">Parent Department</div> <div class="buttonCol">&nbsp;</div> <div class="buttonCol">&nbsp;</div>
	</div> 
	<div id="thEdit" class="row headers editRow"><div class="inputCol">Department Name*</div> <div class="selectCol">Parent Department</div> <div class="buttonCol">*=required</div> <div class="buttonCol">&nbsp;</div></div>
	<c:forEach items="${depts}" var="dept">
		<div id="deptRow${dept.id}" class="row">
			<div class="deptName inputCol" data-value="${dept.name}">${dept.name}</div>
			<div class="deptParent selectCol" data-value="${dept.parentDepartment.id}">${dept.parentDepartment.name}<c:if test="${empty dept.parentDepartment.name}">&nbsp;</c:if></div> 
			<div class="col buttonCol"><button class="editBtn" value="${dept.id}">Edit</button></div>
			<div class="col buttonCol"><button class="removeBtn" value="${dept.id}">Remove</button></div>
		</div>
		
		<div id="editDeptRow${dept.id}" class="row editRow">
			<div class="inputCol"><input name="name" class="editDeptName"/></div>
			<div class="selectCol"><select name="parentDepartment.id" class="editDeptParent">
					<option value="" />
					<c:forEach items="${depts}" var="pDept">
						<option value="${pDept.id}">${pDept.name}</option>
					</c:forEach>
					<option value="last" />
				</select>
			</div>
			<div class="col buttonCol"><button class="saveBtn" value="${dept.id}">Save</button></div>
			<div class="col buttonCol"><button class="cancelEditBtn" value="${dept.id}">Cancel</button></div>
		</div>
	</c:forEach> 
</div>