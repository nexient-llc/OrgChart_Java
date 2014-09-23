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
<tiles:useAttribute id="pageCSS" name="page-css" classname="java.lang.String" ignore="true" />
<c:if test="${not empty pageCSS}">
	<link rel="stylesheet" type="text/css" href="<c:url value='${pageCSS}'/>">
</c:if>
<%@ include file="/WEB-INF/fragments/styles.jsp"%>
<title>Systems In Motion Organizational Chart: <tiles:getAsString name="title" /></title>
</head>
<body>
	<tiles:insertAttribute name="page-heading" />
		<div class="wrapOuter">
			<div class="wrapInner">
                 <div id="navBar">
                 <ul>
                          <li><div><a href="<c:url value='/app/home'/>">Home<span></span></a></div></li>
                          <li><div><a href="<c:url value='/app/depts'/>">Departments<span></span></a></div></li>
                          <li><div><a href="<c:url value='/app/emps'/>">Employees<span></span></a></div></li>
                          <li><div><a href="<c:url value='/app/jobs'/>">Job Titles<span></span></a></div></li>
                 </ul>
                 </div>
            </div>
        </div>
        <div class="wrapFinal"></div>
		<div class="wrapOuter">
			<div id ="bodyWrapper" class="wrapInner">
		        <h3><tiles:getAsString name="headingTitle"/></h3> 
            	<tiles:insertAttribute name="body" />
                <tiles:insertAttribute name="page-footer" />
			</div>
		</div>
</body>
</html>

