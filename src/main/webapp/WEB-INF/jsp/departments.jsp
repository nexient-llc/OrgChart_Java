<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<h3>Departments</h3>

<!-- Main Index Values --> 
<table id="t1"> 
	<tr><!-- <sec:authorize access="hasRole('ROLE_ADMIN')"> -->
		<!-- <th>Task</th></sec:authorize> --> 
		<th>Dept Name</th> <th>Parent Dept</th> <th></th>
	</tr> 
	<c:forEach items="${depts}" var="dept">
		<tr> 
			<!-- <sec:authorize access="hasRole('ROLE_ADMIN')">	
				<td>delete</td>
			</sec:authorize> -->
			<td>${dept.name}</td> 
			<td>${dept.parentDepartment.name}</td>
			<td><button class="editBtn" style="width:45px;" value="${dept.id}">Edit</button></td>
			<td><button class="removeBtn" style="width:80px;" value="${dept.id}">Remove</button></td>
		</tr>
	</c:forEach> 
</table>

<!-- Add Button -->
<div id="addBtn-container">
		<button type="button" id="addBtn" style="width: 45px;">Add</button>	
</div>

<!-- Edit Form -->
<div id="editEntity" style="display:none"> 
	<jsp:include page="../fragments/departmentForm.jsp">
		<jsp:param name="formType" value="Edit" />
		<jsp:param name="method" value="put" />
		<jsp:param name="action" value="/depts/editDept" />
	</jsp:include>
</div>

<!-- Add Form -->
<div id="addEntity" style="display:none">
	<jsp:include page="../fragments/departmentForm.jsp">
		<jsp:param name="formType" value="Create" />
		<jsp:param name="method" value="post" />
		<jsp:param name="action" value="/depts/createDept" />
	</jsp:include>
</div> 

<!-- Delete Form -->
<div id="removeEntity" style="display:none">
	<fieldset>
		<legend>Remove Department?</legend>
		<form:form id="removeDepartmentForm" action="depts/removeDept" method="delete">
			<div>
				<label>Are you sure?</label>
				<input id="removeDepartmentId" type="hidden" name="id" />
				<input type="submit" value="Delete" />
			</div>
		</form:form>		
	</fieldset>
</div>
