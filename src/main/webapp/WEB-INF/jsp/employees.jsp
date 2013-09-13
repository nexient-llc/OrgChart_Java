<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
	
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
<div id="EditEntity" style="display:none">
	<jsp:include page="../fragments/EmployeeForm.jsp">
		<jsp:param value="Edit" name="formType"/>
		<jsp:param value="put" name="method"/>
		<jsp:param value="EditEmp" name="action"/>
	</jsp:include>
</div>

<!-- Create Form -->
<div id="CreateEntity" style="display:none">
	<jsp:include page="../fragments/EmployeeForm.jsp">
		<jsp:param value="Create" name="formType"/>
		<jsp:param value="post" name="method"/>
		<jsp:param value="CreateEmp" name="action"/>
	</jsp:include>
</div>

<!-- Remove Form -->
<div id="RemoveEntity" style="display:none">
	<form:form action="RemoveEmp" id="removeEntity" modelAttribute="EMPLOYEE" method="delete">
		<fieldset>
			<legend>Remove Employee</legend>
			<input id="removeEmployeeId" type="hidden" name="id">
			<label>Are you sure?</label>
			<input type="submit" value="Delete" />
		</fieldset>
	</form:form>
</div>