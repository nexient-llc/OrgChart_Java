<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<html>
<head> 
<%@ include file="/WEB-INF/fragments/meta-tags.jsp"%>
<%@ include file="/WEB-INF/fragments/scripts.jsp"%>
<tiles:useAttribute id="pageJS" name="page-js"
	classname="java.lang.String" ignore="true" />
<c:if test="${not empty pageJS}">
	<script type="text/javascript" src="${pageJS}"></script>
</c:if>
<script type="text/javascript">
var path = "${pageContext.request.contextPath}/app/";
</script>
<%@ include file="/WEB-INF/fragments/styles.jsp"%>
<title>Systems In Motion Organization Chart" <tiles:getAsString name="title" /></title>
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

