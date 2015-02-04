
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%> 


<h3>Login</h3>
<fieldset style="width:300px;">
	<legend>Login</legend>

	<c:if
		test="${not empty sessionScope['SPRING_SECURITY_LAST_EXCEPTION']}">
		<div class="errorblock">
			Your login attempt was not successful, try again.<br /> Caused :
			${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
		</div>
	</c:if>


	<form name='f' action="<c:url value='/app/j_spring_security_check' />"
		method='POST'>
		<div><br>
			<label for="username">User:</label><input type='text'
				name='username' />
		</div><br>
		<div>
			<label for="password">Password:</label><input type='password'
				name='password' />
		</div><br>
		<div>
			<button type="submit">Submit</button>
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/> 
		</div>
	</form>
</fieldset>