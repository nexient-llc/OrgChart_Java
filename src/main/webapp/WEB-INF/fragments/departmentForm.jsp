<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<fieldset>
	<legend>${param.formType} Department</legend>
	<form:form id="${param.formType}DepartmentForm" modelAttribute="DEPARTMENT" method="put">
		<div>
			<input id="${param.formType}Id" type="hidden" name="id" /> 
			<input id="${param.formType}ParentId" type="hidden" name="parentDepartment" /> 
			<label>Dept	Name:</label> 
			<form:input path="name" /> <form:select path="parentDepartment.id">
				<form:option value="...">...</form:option>
				<c:forEach items="${depts}" var="dept">
					<form:option value="${dept.id}">${dept.name}</form:option>
				</c:forEach>
			</form:select> <input type="submit" value="Save" />
		</div>
	</form:form>
</fieldset>