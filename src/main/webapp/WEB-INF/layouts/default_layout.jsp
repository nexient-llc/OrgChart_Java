<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<html>
<head>
	
<%@ include file="/WEB-INF/fragments/meta-tags.jsp"%>
<%@ include file="/WEB-INF/fragments/scripts.jsp"%>
<%@ include file="/WEB-INF/fragments/styles.jsp"%>


<tiles:useAttribute id="pageJS" name="page-js" classname="java.lang.String" ignore="true" />
<c:if test="${not empty pageJS}">
	<script type="text/javascript" src="${pageJS}"></script>
</c:if>

<title>Systems in Motion Organization Chart: <tiles:getAsString name="title" /></title>
</head>
<body>
	<div class="sim-logo"></div>
	<div align="center">
			<div id="navbar">
				<a href="home">Home</a>
				<a href="depts">Departments</a>
				<a href="emps">Employees</a>
				<a href="titles">Job Titles</a>
				<a href="admin/login">Login</a>
			</div>
		
		<tiles:insertAttribute name="body" />
	</div>
</body>
</html>

