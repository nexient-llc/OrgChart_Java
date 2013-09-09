<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
	
<h3>Job Titles</h3>
<table id="t1">
  <tr>
    <th>Job Title Name</th>
  </tr>
  <c:forEach items="${jobs}" var="job">
    <tr>
      <td>${job.first_name}</td>
    </tr>
  </c:forEach>
</table>