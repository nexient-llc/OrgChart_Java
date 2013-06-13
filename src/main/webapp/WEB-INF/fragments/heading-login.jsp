<%@ taglib prefix="c"   uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<div id="heading">
	<div id="login" <sec:authorize access="hasRole('ROLE_ADMIN')">style="visibility:hidden"</sec:authorize>>
		<a href="<c:url value='/app/admin/depts' />">login to edit</a>
	</div>
	<div class="sim-logo"></div>
</div>