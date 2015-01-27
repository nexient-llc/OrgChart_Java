<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<div id="heading"> <div id="login"
	<sec:authorize access="hasRole('ROLE_ADMIN')">style="visibility:hidden"</sec:authorize>>login
to edit</div> <div class="sim-logo"></div><h1>Welcome to the Systems in Motion Organizational Management Application</h1> </div>
 