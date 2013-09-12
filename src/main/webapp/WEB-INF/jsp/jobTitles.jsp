<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
	
<h3>Job Titles</h3>
<table id="t1">
  <tr>
    <th>Job Title Name</th>
    <th></th>
    <th></th>
  </tr>
  <c:forEach items="${jobs}" var="job">
    <tr>
      <!-- May be job.description -->
      <td>${job.name}</td>
      <td><button class="editBtn" style="width:45px;" value="${job.id}">Edit</button></td>
      <td><button class="removeBtn" style="width:80px;" value="${job.id}">Remove</button></td>
    </tr>
  </c:forEach>
</table>

<div id="addBtn-container">
  <button type="button" id="addBtn" style="width: 45px;">Add</button>
</div>

<div id="addEntity" style="display:none">
  <fieldset>
    <legend>Add Job Title</legend>
    <form name="newJob" action="jobs" method="post">
      <div>
        <label>Job Title Name:</label><input type="text" name="name"/>
        <button type="submit">Save</button>
      </div>
    </form>
  </fieldset>
</div>