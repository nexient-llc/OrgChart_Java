<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%> 

<h3>Message : ${message}</h3>
<sec:authorize access="hasRole('ROLE_ADMIN')">
Thank you for logging in ${name}.<!-- You will now be redirected to ${page} shortly.-->
</sec:authorize>