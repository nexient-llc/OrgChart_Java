<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<fieldset>
	<legend>Login</legend>

	<c:if
		test="${not empty sessionScope['SPRING_SECURITY_LAST_EXCEPTION']}">
		<div class="errorblock">
			Your login attempt was not successful, try again.<br /> Caused :
			${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
		</div>
	</c:if>

	<c:if test="${not empty error}">
		<div class="error">${error}</div>
	</c:if>

	<c:if test="${not empty msg}">
		<div class="msg">${msg}</div>
	</c:if>


	<form action="<c:url value='/app/login' />" method='POST'>
		<input type="hidden" name="page" value="${param.page}" />
		<div>
			<label for="username">User:</label><input id='username' type='text'
				name='username'>
		</div>
		<div>
			<label for="password">Password:</label><input type='password'
				name='password' />
		</div>
		<div>
			<button type="submit">Submit</button>
		</div>
		<input type="hidden" name="${_csrf.parameterName }"
			value="${_csrf.token }" />
	</form>
</fieldset>