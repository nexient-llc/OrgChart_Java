<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
	
<h3>Employees</h3>
<table id="t1">
  <tr>
    <th>Employee Name</th> <th>Employee Manager</th>
  </tr>
  <c:forEach items="${empls}" var="empl">
    <tr>
      <td>${empl.first_name}</td>
      <td>${empl.manager.first_name}</td>
    </tr>
  </c:forEach>
</table>