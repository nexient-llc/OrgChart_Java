<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<h3>Manage Employee</h3> 
<table id="t1"> 
	<tr><!-- <sec:authorize access="hasRole('ROLE_ADMIN')"> -->
		<!-- <th>Task</th></sec:authorize> --> 
		<th> Employee Name </th> <th> Department Name </th> <th> Job Title </th>
	</tr> 
	<c:forEach items="${emps}" var="emp">
		<tr> 
			<!-- <sec:authorize access="hasRole('ROLE_ADMIN')">
				<td>delete</td>
			</sec:authorize> -->
			<td>${emp}</td> 
			<td>${emp.department.name}</td>
			<td>${emp.jobTitle.name}</td>
		</tr>
	</c:forEach> 
</table>

<div id="addBtn-container">
		<button type="button" id="addBtn" style="width: 45px;">Add</button>	
</div>

<div id="addEntity" style="display:none">
	<fieldset>
		<legend>Add New Employee</legend>
		<form name="newEmp" action="emps" method="post">
		<div>
			<labeL>*First Name:</labeL><input type="text" name="firstName" required/>
			<labeL>*Last Name:</labeL><input type="text" name="lastName"required/>
			<labeL>Middle Initial:</labeL><input type="text" name="middleInitial"/>
			<labeL>*Departments:</label>
			<select name="department" >
				<c:forEach items="${depts}" var="dept">
					<option value="${dept.id}">${dept.name}</option>
				</c:forEach>
			</select><br>
			<labeL>*Email:</labeL><input type="text" name="email" required/>
			<labeL>*Skype Name:</labeL><input type="text" name="skypeName" required/>
			<labeL>*Job Title:</label>
			<select name="department" >				
				<c:forEach items="${titles}" var="title">
					<option value="${title.id}">${title.name}</option>
				</c:forEach>
			</select>
			<button type="submit">Save</button>
			<button type="reset" value="reset" id="cancelButton">Cancel</button><br>
			Required Fields indicated with a *
		</div>
		<div></div>
		</form>
	</fieldset>
</div>
