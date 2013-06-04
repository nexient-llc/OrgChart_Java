<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%> 
<html>
<head>
<title>Systems In Motion</title>
<meta http-equiv="refresh" content="3;url=../${page}">
</head>
<body>
<sec:authorize access="hasRole('ROLE_ADMIN')">
Thank you for logging in ${name}.  You will now be redirected to ${page} shortly.
</sec:authorize>
</body>
</html>