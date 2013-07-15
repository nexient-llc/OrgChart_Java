<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%> 
<div id="heading">
	<div id="login">
	<sec:authorize var="loggedIn" access="isAuthenticated()" />
	<c:choose>
    <c:when test="${loggedIn}">
       <h1><a href="<c:url value="/app/j_spring_security_logout" />" >LOG OUT</a></h1>
    </c:when>
	<c:otherwise>
	<a href="admin/login">LOG IN</a>
	</c:otherwise>
	</c:choose> 
	</div>
	<div class="sim-logo">
	</div> 
</div>
 