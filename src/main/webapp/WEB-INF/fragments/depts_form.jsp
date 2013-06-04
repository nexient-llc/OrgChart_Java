<%@ taglib prefix="c"    uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="sec"  uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<labeL>Dept Name:</labeL>
<form:input id="${prefix}deptName" path="name" type="text" />
<labeL>Parent Dept:</label>
<form:select id="${prefix}deptParenDept" path="parentDepartment">
	<option id="${prefix}dotdotdot" value="-1">...</option>
	<c:forEach items="${depts}" var="dept">
		<option id="${prefix}deptParenDept${dept.id}" value="${dept.id}">${dept.name}</option>
	</c:forEach>
</form:select>