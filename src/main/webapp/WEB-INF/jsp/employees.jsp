<!DOCTYPE html >

<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec"
uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<h3>Employees</h3> 
<br/>
<sec:authorize access="hasRole('ADMIN')">
<div id="addBtn-container">
		<button type="button" id="addBtn" style="width: 45px;">Add</button>	
</div>
<div id="addEntity" style="display:none">
	<fieldset>
		<legend>Add Employee</legend>
		<form:form id="newEmployee" name="New Employee" action="addEmployee" method="post">
		 <input type="hidden" id="empId" name="id"/> 
		 *First Name: <input id="fname" type="text" name="firstName" required />
		 *Last  Name: <input id="lname" type="text" name="lastName" required/>
		  Middle: <input id="mid" type="text" name="middleInitial"  style="width: 20px;" maxlength="1"/>
		 *Email: <input id="eEmail" type="text" name="email" required>
		 *Skype Name: <input id="empskype" type="text" name="skypeName" required>
	   	 *Department:
			<select id="depart" name="department.id" required>
				<option value=""> </option>
				<c:forEach items="${depts}" var="dept">  
					<option value="${dept.getId()}">${dept.name}</option>
				</c:forEach>
			</select>	
		 *Job Title:
			<select id="job" name="jobTitle.id">
				<option value=""> </option>
				<c:forEach items="${titles}" var="title">  
					<option value="${title.getId()}"> ${title.name}</option>
				</c:forEach>
			</select>
	    <input  id="activeBox" type="checkbox"  name="isActive"/> <label> Active </label> 
		<div>
		<button type="submit">Save</button>
		<button id="cancel" type="reset" value="reset" > Cancel</button>
		</div>
		</form:form>
		</fieldset> 
</div>

</sec:authorize>
<div id="searchBtn-container">
<button type="button" id="searchBtn" style="width: 65px;">Search</button>	
</div>
 <div class="searchClass" id="searchEntity" style="display: none"> 
 	<fieldset>
 		<legend>Edit Employee</legend> 
		<form:form name="Search Employee" action="filterEmployees" method="get">
 			<div> 
 				<input type="hidden" id="empId" name="id"/> 
 				 Full Name <input id="searchFullName" type="text" name="firstName"/> 
 				 Department: 
 			<select id="searchDepartment" name="department.id">
 				<option value=""> </option>
				<c:forEach items="${depts}" var="dept">  
					<option value="${dept.getId()}">${dept.name}</option>
				</c:forEach>
 			</select> 
 				Job Title: 
 			<select id="searchJobTitle" name="jobTitle.id">
				<option value=""> </option> 
 				<c:forEach items="${titles}" var="title">   
 					<option value="${title.getId()}"> ${title.name}</option> 
				</c:forEach> 
			</select> 
				<button type="submit">Search</button> 
 				<button id="searchEdit" type="reset" value="reset" >Cancel</button> 
 			</div> 
 			<div></div>
		</form:form> 
	</fieldset> 
 </div> 

<table id="t1" > 
	<tr>
    <th align="left" > Name</th>
	<th align="left" > Department</th> <th align="left" > Job Title</th>
	<sec:authorize access="hasRole('ADMIN')">
     <th>Active</th> <th>Skype</th> <th>Email</th><th>Edit</th><th>Remove</th>
	</sec:authorize> 
	</tr> 
	<c:forEach items="${emps}" var="emps">
		<tr id="emprow-${emps.id}" class="EmployeeTable"> 
			<td> ${emps}</td>
			<td>${emps.department.name}</td>
			<td>${emps.jobTitle.name}</td>
			<sec:authorize access="hasRole('ADMIN')">
			<td>
			<c:choose>
			<c:when test="${emps.getIsActive()}">Yes</c:when>
			<c:otherwise>No</c:otherwise> 
			</c:choose>
			<td> ${emps.skypeName}</td>
			<td> ${emps.email}</td>
			</td>
			<td>
			<button class="editButton" value="${emps.id}"  style="width: 60px;">Edit</button>
			</td> 			
			<td>
			<button class="removeButton" value="${emps.id}"  id="removeEntity" style="width: 60px;">Delete</button>
			</td>
			</sec:authorize>
		</tr>
	</c:forEach> 
</table>