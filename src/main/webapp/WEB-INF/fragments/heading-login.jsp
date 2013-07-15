<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<div id="heading"> 
	<div id="login"<sec:authorize access="hasRole('ROLE_ADMIN')">style="visibility:shown"</sec:authorize>>
	<a href="admin/login">LOG IN</a>
	</div> 
	<div class="sim-logo">
	</div> 
</div>
 