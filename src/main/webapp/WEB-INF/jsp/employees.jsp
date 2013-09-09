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
  </tr>
  <c:forEach items="${empls}" var="empl">
    <tr>
      <td>${empl.first_name} ${empl.middle_initial} ${empl.last_name}</td>
      <td>${empl.email}</td>
      <td>${empl.skype_name}</td>
      <td>${empl.manager.first_name} ${empl.manager.last_name}</td>
      <td>${empl.job_title.name}</td>
      <td>${empl.department.name}</td>
    </tr>
  </c:forEach>
</table>

<div id="addBtn-container">
  <button type="button" id="addBtn" style="width: 45px;">Add</button>
</div>

<div id="addEntity" style="display:none">
  <fieldset>
    <legend>Add Employee</legend>
    <form name="newEmp" action="emps" method="post">
      <div>
        <label>Employee's First Name:</label><input type="text" name="first_name"/>
        <label>Employee's Middle Initial:</label><input type="text" name="middle_initial"/>
        <label>Employee's Last Name:</label><input type="text" name="last_name"/>
        <label>Employee's Skype:</label><input type="text" name="skype_name"/>
        <label>Employee's Email:</label><input type="email" name="email"/>
        <label>Employee's Job Title:</label>
        <select name="jobTitle">
          <option>...</option>
          <c:forEach items="${jobs}" var="job">
            <option value="${job.jobTitleId}">${job.name}</option>
          </c:forEach>
        </select>
        <label>Employee's Department:</label>
        <select name="department">
          <option>...</option>
          <c:forEach items="${depts}" var="dept">
            <option value="${dept.departmentId}">${dept.name}</option>
          </c:forEach>
        </select>
        <button type="submit">Save</button>
      </div>
    </form>
  </fieldset>
</div>