<!DOCTYPE HTML>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<header>Systems In Motion Organization Chart : Employee Page</header>
<div id="addBtn-container"><button type="button" id="addBtn" style="width: 45px;">Add</button></div>

<div id="addEntity" style="display:none">
	<fieldset>
		<legend>
			Add New Employee
		</legend>
			<form:form name="newEmp" action="emps" method="post">
				<div>
					<labeL>First Name *</labeL>
						<input type="text" name="firstName"/>
					<labeL>Last Name *</label>
						<input type="text" name="lastName"/>
					<label>Middle Initial</label>
						<input type="text" name="middleName">
					<label>Departments *</label>
						<select>
							<c:forEach items="${depts}" var = "dept" >
								<option>${dept.name}</option>
							</c:forEach>
						</select>
					<label>Email *</label>
						<input type="text" name="email" />
					<label>Skype *</label>
						<input type="text" name ="skypeName"/>
					<label>Job Titles *</label>
						<select>
							<c:forEach items="${jobs}" var = "job" >
								<option>${job.name}</option>
							</c:forEach>
						</select>
					<button type="submit">Submit</button>
					<footer>Required Fields indicated with a *</footer>
				</div>
			</form:form>
	</fieldset>
</div>

<div>
<table id="t1">
	<tr>
		<th>First Name</th>
		<th>Last Name</th>
		<th>Job Title</th>
		<th>Department Name</th>
	</tr> 
	<c:forEach items="${emps}" var="emp">
		<tr> 
			<td>${emp.firstName}</td> 
			<td>${emp.lastName}</td>
			<td>${emp.jobTitle.name}</td>
			<td>${emp.department.name}</td>
		</tr>
	</c:forEach> 
</table>
</div>