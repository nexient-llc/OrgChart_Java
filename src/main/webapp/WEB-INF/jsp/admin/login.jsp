<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<fieldset>
	<legend>Logins</legend>

	<c:if
		test="${not empty sessionScope['SPRING_SECURITY_LAST_EXCEPTION']}">
		<div class="errorblock">
			Your login attempt was not successful, try again.<br /> Caused :
			${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
		</div>
	</c:if>

	<form:form action='j_spring_security_check' method='POST'>
		<input type="hidden" name="page" value="${param.page}" />
		<div>
			<label for="j_username">User:</label><input type='text'
				name='j_username' required />
		</div>
		<div>
			<label for="j_password">Password:</label><input type='password'
				name='j_password' required />
		</div>
		<div>
			<button id='submitBtn' type="submit">Submit</button>
		</div>
	</form:form>
</fieldset>
