<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<div id="heading"> <div id="login"
	<sec:authorize access="hasRole('admin')">style="visibility:hidden"</sec:authorize>>login
to edit</div> <div class="sim-logo"></div> </div>
