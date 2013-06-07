<%@ taglib prefix="sec"  uri="http://www.springframework.org/security/tags"%>

<div>
<a href="/orgchart/app/departments">Departments</a> <br/>
<a href="/orgchart/app/emps">Employees</a><br/>
<a href="/orgchart/app/jobs">Job Titles</a><br/>
</div>
<sec:authorize access="hasRole('ROLE_ADMIN')">
IF YOU CAN SEE THIS THEN YOU ARE LOGGED IN AS ADMIN!!!!
</sec:authorize>