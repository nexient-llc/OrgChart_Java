<!DOCTYPE HTML>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<header>Systems In Motion Organization Chart : Employees</header>
<br/>
<div id="addBtn-container"><button type="button" id="addBtn" style="width: 45px;">Add</button></div>

<div id="Entity" style="display:none">
	<fieldset>
		<legend>Employee</legend>
		<br/>
		<div id="Add" style="display:none">Add New Employee</div>
		<div id="Edit" style="display:none">Edit Employee</div>
		<br/>
			<form:form name="Employee" action="emps" method="post">
			<input type='hidden' name='_method' value='post' id='formMethod' />
				<div>
					<labeL>First Name *</labeL>
						<input type="text" name="firstName"/>
						<br/>
					<labeL>Last Name *</label>
						<input type="text" name="lastName"/>
						<br/>
					<label>Middle Initial</label>
						<input type="text" name="middleName">
						<br/>
					<label>Email *</label>
						<input type="text" name="email" />
						<br/>
					<label>Skype *</label>
						<input type="text" name ="skypeName"/>
					<br/>
					<br/>
					<label>Departments *</label>
					<select name="department.id">
						<c:forEach items="${depts}" var = "dept" >
							<option  value="${dept.id}">${dept.name}</option>
						</c:forEach>
					</select>
					<br/>
					<label>Job Titles *</label>
						<select name="jobTitle.id">
							<c:forEach items="${jobs}" var = "job" >
								<option value="${job.id}">${job.name}</option>
							</c:forEach>
						</select>
						<br/>
						<br/>
					<input type="hidden" id="editId" name=""/>
					<button class ="submitBtn" type="submit">Submit</button>
					<button class ="cancelBtn" type="reset">Cancel</button>
					<br/>
					<br/>
					<footer>Required Fields indicated with a *</footer>
					<br/>
				</div>
			</form:form>
	</fieldset>
</div>

<div>
<table id="t1">
	<tr>
		<th>First Name</th>
		<th>Last Name</th>
		<th>Middle Name</th>
		<th>Department Name</th>
		<th>Job Title</th>
		<th>Edit Employee</th>
		<th>Delete Employee</th>
		
	</tr> 
	<c:forEach items="${emps}" var="emp">
		<tr> 
			<td>${emp.firstName}</td> 
			<td>${emp.lastName}</td>
			<td>${emp.middleName}</td>
			<td>${emp.department.name}</td>
			<td>${emp.jobTitle.name}</td>
			<td><button type="button" class="editBtn" id="indivEdit" value='${emp.id}'>Edit</button></td>
			<td><button type="button" class="deleteBtn" id="indivDelete" value='${emp.id}'>Delete</button></td>
		</tr>
	</c:forEach> 
</table>
<div id="deleteEntity" style="display:none">
<fieldset>
		<legend>Delete Employee</legend>
<form:form name="deleteEmp" method="delete" action="emps">
	<input id="deleteInput" type="hidden" name="id"/>
	<button class ="submitBtn" type="submit">Delete</button>
	<button class ="cancelBtn" type="reset">Cancel</button>
</form:form>
</fieldset>
</div>