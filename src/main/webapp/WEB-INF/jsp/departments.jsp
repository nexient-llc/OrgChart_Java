<!DOCTYPE html>
<%@ taglib prefix="c"    uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="sec"  uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<h3>Departments</h3>
<table id="t1">
	<tr>
		<sec:authorize access="hasRole('ROLE_ADMIN')">
		<th colspan="2">Tasks</th>
		</sec:authorize>
		<th>Dept Name</th>
		<th>Parent Dept</th>
	</tr> 
	<c:forEach items="${depts}" var="dept">
		<tr>
			<sec:authorize access="hasRole('ROLE_ADMIN')">
			<td>
			<form action="<c:url value='/app/depts/${dept.id}'/>" method="post">
				<input type="hidden" name="_method" value="DELETE" />
				<input type="submit" value="delete" />
			</form>
			</td>
			<td>
			<form action="<c:url value='/app/depts/edit/${dept.id}'/>" method="get">
				<input type="submit" value="edit" />
			</form>
			</td>
			</sec:authorize>
			<td>${dept.name}</td> 
			<td>${dept.parentDepartment.name}</td> </tr>
	</c:forEach>
</table>

<sec:authorize access="hasRole('ROLE_ADMIN')">
<div id="addBtn-container">
		<button type="button" id="addBtn" style="width: 45px;">Add</button>	
</div>

<div id="addEntity" style="display:none">
	<fieldset>
	<legend>Add Department</legend>
	<form:form action="/app/depts" modelAttribute="modelDept" method="post">
	<div>
		<!-- Must include all data of the model -->
		<form:input type="hidden" path="id" value="-1" />
		
		<labeL>Dept Name:</labeL>
		<form:input path="name" type="text" />
		
		<labeL>Parent Dept:</label>
		<form:select path="parentDepartment">
			<option value="-1">...</option>
			<c:forEach items="${depts}" var="dept">
				<option value="${dept.id}">${dept.name}</option>
			</c:forEach>
		</form:select>
		
		<input type="submit" value="Save">
	</div>
	</form:form>
	</fieldset>
</div>
</sec:authorize>
