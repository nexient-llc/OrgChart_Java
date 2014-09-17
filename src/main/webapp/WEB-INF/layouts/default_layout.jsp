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
<title>Systems In Motion - <tiles:getAsString name="title" /></title>
</head>
<body>
	<tiles:insertAttribute name="page-heading" />
	 <!--  <table>
		<tr>
			<th><label id="navBarHome" style="color: #FFFFFF"><a style="color: #FFF;" href="<c:url value='/app/home.jsp'/>">Home</a></label></th>
			<th><label id="navBarDepts" style="color: #FFFFFF">Departments</label></th>
			<th><label id="navBarEmps" style="color: #FFFFFF">Employees</label></th>
			<th><label id="navBarJobs" style="color: #FFFFFF">Job Titles</label></th>
		</tr> 
	</table> -->
	<div id="navBar">
          <ul>
                  <li><a href="<c:url value='/app/home'/>">Home<span></span></a></li>
                  <li><a href="<c:url value='/app/depts'/>">Departments<span></span></a></li>
                  <li><a href="<c:url value='/app/emps'/>">Employees<span></span></a></li>
                  <li><a href="<c:url value='/app/jobs'/>">Job Titles<span></span></a>
                  </li>
           </ul>
  </div>
	<tiles:insertAttribute name="body" />
	<tiles:insertAttribute name="page-footer" />
</body>
</html>

