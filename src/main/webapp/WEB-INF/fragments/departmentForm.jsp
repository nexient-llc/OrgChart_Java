<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<fieldset>
	<legend>${param.formType} Department</legend>
	<form:form id="${param.formType}DepartmentForm" action="${param.action}" modelAttribute="DEPARTMENT" method="${param.method}">
		<div>  
			<input type="hidden" name="id" id="${param.formType}Id" />
			<label>Dept	Name:</label> 
			<form:input path="name" />
			<form:select path="parentDepartment.id">
				<form:option value="0">...</form:option>
				<c:forEach items="${depts}" var="dept">
					<form:option value="${dept.id}">${dept.name}</form:option>
				</c:forEach>
			</form:select> 
			<input type="submit" value="Save" />
		</div>
	</form:form>
</fieldset>