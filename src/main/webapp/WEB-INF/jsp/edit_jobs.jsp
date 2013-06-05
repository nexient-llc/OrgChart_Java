<!DOCTYPE html>
<%@ taglib prefix="c"    uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="sec"  uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<form:form action="/app/jobs/edit"  modelAttribute="modelJob" method="put">
	<!-- Must include all data of the model -->
	<form:input type="hidden" path="id" />
	
	<labeL>Name:</labeL>
	<form:input path="name" type="text" />
	
	<input type="submit" value="update" />
	
	<a href="/orgchart/app/jobs"><button type="button">cancel</button></a>
</form:form>