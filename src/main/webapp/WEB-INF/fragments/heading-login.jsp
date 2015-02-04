<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<div id="heading"> <div id="login"
	<sec:authorize access="hasRole('ROLE_ADMIN')">style="visibility:hidden"</sec:authorize>>Login</div>
<sec:authorize access="hasRole('ROLE_ADMIN')">
	
	<c:url value="/logout" var="logoutUrl" />
 
		<!-- csrt support -->
	<form action="${logoutUrl}" method="post" id="logoutForm">
		<input type="hidden" 
			name="${_csrf.parameterName}"
			value="${_csrf.token}" />
	</form>
 
	<script>
		function formSubmit() {
			document.getElementById("logoutForm").submit();
		}
	</script>
 
	<c:if test="${pageContext.request.userPrincipal.name != null}">
			<div style="float:right; font-size:1em;"> Welcome:${pageContext.request.userPrincipal.name} |
		<a style="font-size:1.1em; z-index:-1;" href="javascript:formSubmit()"> Logout</a></div>
	</c:if>
</sec:authorize> <div class="sim-logo"></div><h1>Welcome to the Systems in Motion Organizational Management Application</h1> </div>
 