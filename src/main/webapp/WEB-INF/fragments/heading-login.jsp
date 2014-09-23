<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>

<div id="heading"> 
	<div id="login" <sec:authorize access="hasRole('ROLE_ADMIN')">style="visibility:hidden"</sec:authorize>>
		<span><a href="<c:url value='/app/admin/login'/>">Log in to edit</a></span>
	</div>
	<div class="sim-logo"><a href="<c:url value='/app/home' />"></a></div>
</div>
 