<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
	
<h3>Employees</h3>
<table id="t1">
  <tr>
    <th>Employee: Name</th>
    <th>email</th>
    <th>Skype</th>
    <th>Manager</th>
    <th>JobTitle</th>
    <th>Department</th>
    <th></th>
    <th></th>
  </tr>
  <c:forEach items="${emps}" var="empl">
    <tr>
      <td>${empl.firstName} ${empl.lastName}</td>
      <td>${empl.email}</td>
      <td>${empl.skypeName}</td>
      <td>${empl.manager.firstName} ${empl.manager.lastName}</td>
      <td>${empl.jobTitle.name}</td>
      <td>${empl.department.name}</td>
      <td><button class="editBtn" style="width:45px;" value="${empl.id}">Edit</button></td>
      <td><button class="removeBtn" style="width:80px;" value="${empl.id}">Remove</button></td>
    </tr>
  </c:forEach>
</table>

<div id="addBtn-container">
  <button type="button" id="addBtn" style="width: 45px;">Add</button>
</div>

<!-- Edit Form -->
<div id="editEntity" style="display:none">
	<jsp:include page="../fragments/EmployeeForm.jsp">
		<jsp:param value="Edit" name="formType"/>
	</jsp:include>
</div>

<!-- Create Form -->
<div id="addEntity" style="display:none">
	<jsp:include page="../fragments/EmployeeForm.jsp">
		<jsp:param value="Create" name="formType"/>
	</jsp:include>
</div>