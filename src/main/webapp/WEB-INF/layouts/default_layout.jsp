<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<html>
	<head> 
	<%@ include file="/WEB-INF/fragments/meta-tags.jsp"%>
	<%@ include file="/WEB-INF/fragments/scripts.jsp"%>
	
	<tiles:useAttribute id="pageJS" name="page-js" classname="java.lang.String" ignore="true" />
	<c:if test="${not empty pageJS}">
		<script type="text/javascript" src="${pageJS}"></script>
	</c:if>
	
	<%@ include file="/WEB-INF/fragments/styles.jsp"%>
	
	<title>Systems In Motion - <tiles:getAsString name="title" /></title>
</head>

<body>
	
	<div id="mainContainer">
	
		<!-- Header -->
		<div id="headerContent">
			<tiles:insertAttribute name="page-heading" />
		</div>
		
		<div id="bodyContent">
		
			<div id="navBar">
				<div id="navBarHome" class="navActive">
					Home
				</div>
				
				<div id="navBarDepts" class="navInactive">
					Departments
				</div>
				
				<div id="navBarEmps" class="navInactive">
					Employees
				</div>
				
				<div id="navBarJobs" class="navInactive">
					Job Titles
				</div>
			</div>
		
			<div id="pageBody">
				<tiles:insertAttribute name="body" />
			</div>
			
		</div>
		
		<!-- Footer -->
		<div id="footerContent">
			<tiles:insertAttribute name="page-footer" />
		</div>
		
	</div>
	
</body>

</html>

