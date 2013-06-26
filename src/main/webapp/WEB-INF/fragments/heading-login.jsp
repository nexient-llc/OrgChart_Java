<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<div id="heading">
	<div id="login"
	<sec:authorize access="hasRole('ROLE_ADMIN')">style="visibility:hidden"</sec:authorize>>Log in</div> 
	<div class="sim-logo"></div> 
	<h2>Organization Chart</h2>
	<table>
		<tr>
			<th><label id="navBarHome" color="#FFFFFF">Home</label></a></th>
			<th><label id="navBarDepts" color="#FFFFFF">Departments</label></th>
			<th><label id="navBarEmps" color="#FFFFFF">Employees</label></th>
			<th><label id="navBarJobs" color="#FFFFFF">Job Titles</label></th>
		</tr>
	</table>
</div>
 