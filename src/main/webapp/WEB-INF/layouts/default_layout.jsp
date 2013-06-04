<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<html>
<head> 
<%@ include file="/WEB-INF/fragments/meta-tags.jsp"%>
<%@ include file="/WEB-INF/fragments/scripts.jsp"%>
<%@page import="java.util.ArrayList" %>

<tiles:useAttribute id="list" name="page-scripts" classname="java.util.ArrayList" ignore="true" />
<c:forEach var="item" items="${list}">
	<c:if test="${not empty item}">
		<script type="text/javascript" src="${item}"></script>
	</c:if>
</c:forEach>

<%@ include file="/WEB-INF/fragments/styles.jsp"%>
<title>Systems In Motion - <tiles:getAsString name="title" /></title>
</head>
<body>
	<tiles:insertAttribute name="page-heading" />
	<table>
		<tr>
			<th><label id="navBarHome" color="#FFFFFF">Home</label></a></th>
			<th><label id="navBarDepts" color="#FFFFFF">Departments</label></th>
			<th><label id="navBarEmps" color="#FFFFFF">Employees</label></th>
			<th><label id="navBarJobs" color="#FFFFFF">Job Titles</label></th>
		</tr>
	</table>
	<tiles:insertAttribute name="body" />
</body>
</html>

