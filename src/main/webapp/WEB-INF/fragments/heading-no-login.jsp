<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
	
<form action="<c:url value='admin/login?logout' />" method="post" id="logoutForm">
	<input type="hidden" 
		name="${_csrf.parameterName}"
		value="${_csrf.token}" />
</form>
<div id="heading"> <div id="logout" 
<sec:authorize access="hasRole('admin')">style="visibility:hidden"</sec:authorize>>logout</div> <div
	class="sim-logo"></div> </div>
 
 <h1>Welcome to the Systems in Motion Organizational Management Application</h1>