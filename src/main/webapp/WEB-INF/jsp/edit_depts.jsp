<!DOCTYPE html>
<%@ taglib prefix="c"    uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="sec"  uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<form:form action="/app/depts/edit" modelAttribute="modelDept">
	<!-- POST to UPDATE translation -->
	<input type="hidden" name="_method" value="PUT" />

	<!-- Must include all data of the model -->
	<form:input type="hidden" path="id" />

	<labeL>Dept Name:</labeL>
	<form:input path="name" type="text" />

	<labeL>Parent Dept:</label>
	<form:select path="parentDepartment">
		<form:option value="-1">...</form:option>
		<form:options items="${depts}" itemValue="id" itemLabel="name" />
	</form:select>

	<input type="submit" value="update" />

	<a href="/orgchart/app/depts"><button type="button">cancel</button></a>
</form:form>
