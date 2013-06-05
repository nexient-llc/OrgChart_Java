<!DOCTYPE html>
<%@ taglib prefix="c"    uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="sec"  uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<form:form action="/app/emps/edit" modelAttribute="modelEmp" method="post">
	<!-- POST to UPDATE translation -->
	<input type="hidden" name="_method" value="PUT" />
	
	<!-- Must include all data of the model -->
	<form:input type="hidden" path="id" />

	<labeL>First Name:</labeL>
	<form:input path="firstName" type="text" />

	<labeL>Last Name:</labeL>
	<form:input path="lastName" type="text" />

	<labeL>Middle Initial:</labeL>
	<form:input path="middleInitial" type="text" />

	<labeL>Email:</labeL>
	<form:input path="email" type="email" />

	<labeL>Skype Name:</labeL>
	<form:input path="skypeName" type="text" />

	<labeL>Job Title:</label>
	<form:select path="jobTitle" items="${jobs}" itemValue="id" itemLabel="name" />
	
	<labeL>Department:</label>
	<form:select path="department" items="${depts}" itemValue="id" itemLabel="name" />
	
	<input type="submit" value="update" />
	
	<a href="/orgchart/app/emps"><button type="button">cancel</button></a>
</form:form>