<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<div id="heading"> <div id="login"
	<sec:authorize access="isAuthenticated()">style="visibility:hidden"</sec:authorize>>login
to edit </div>
<br>
<div id="logout" <sec:authorize access="isAuthenticated()">style="display:block"</sec:authorize>style="display:none">logout</div>
<div class="sim-logo"></div>
</div>