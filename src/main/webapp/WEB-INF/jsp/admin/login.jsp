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


	<form action="<c:url value='/app/j_spring_security_check' />"
		method='POST'>
		<input type="hidden" name="page" value="${param.page}" />
		<div>
			<label for="j_username">User:</label><input type='text'
				name='j_username'>
		</div>
		<div>
			<label for="j_password">Password:</label><input type='password'
				name='j_password' />
		</div>
		<div>
			<button type="submit">Submit</button>
		</div>
	</form>
</fieldset>