<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<title>"Systems in Motion Organization Chart: Employee Page"</title>

<h1>"Systems in Motion Organization Chart: Employee Page"</h1>
<a href='<c:url value=""/>'>Login</a> <br/>
<h3>Manage Employees</h3>
<div id="addBtn-container">
	<button type="button" id="addBtn" style="width: 45px;">Add</button>
</div>

<div id="addEntity" style="display: none;">
	<fieldset>
		<legend>Add New Employee</legend>
		<legend>* = Required</legend>
		<form:form modelAttribute="emp" id="addForm" action="emps" method="post">
			<table>
				<tr>
					<td>*First Name :</td>
					<td><form:input path="firstName" /></td>
				</tr>
				<tr>
					<td>Middle Initial :</td>
					<td><form:input path="middleInitial" /></td>
				</tr>
				<tr>
					<td>*Last Name :</td>
					<td><form:input path="lastName" /></td>
				</tr>
				<tr>
					<td>*Department :</td>
					<td>
					<form:select path="department.id">
                                 <form:option value="" label="" />
                                 <form:options items="${depts}" itemValue="id" itemLabel="name" />
                         </form:select>
					</td>
				</tr>
				<tr>
					<td>*Email :</td>
					<td><form:input path="email" /></td>
				</tr>
				<tr>
					<td>*Skype Name :</td>
					<td><form:input path="skypeName" /></td>
				</tr>
				<tr>
					<td>*Job Title :</td>
					<td>
					<form:select path="jobTitleId.id">
                                 <form:option value="" label="" />
                                 <form:options items="${jobTitles}" itemValue="id" itemLabel="name" />
                         </form:select>
					</td>
				</tr>
			</table>
			<button type = "submit" onclick ="save">Save</button>
			<div id="cancelAddBtn-container">
				<button type="button" id="cancelAddBtn" style="width: 60px;">Cancel</button>
			</div>
		</form:form>
	</fieldset>
</div>


<div id="editDeptRow" style="display: none;">
	<fieldset>
		<legend>Add New Employee</legend>
		<legend>* = Required</legend>
		<form:form modelAttribute="emp" id="addForm" action="emps" method="post">
			<table>
				<tr>
					<td>*First Name :</td>
					<td><form:input path="firstName" /></td>
				</tr>
				<tr>
					<td>Middle Initial :</td>
					<td><form:input path="middleInitial" /></td>
				</tr>
				<tr>
					<td>*Last Name :</td>
					<td><form:input path="lastName" /></td>
				</tr>
				<tr>
					<td>*Department :</td>
					<td>
					<form:select path="department.id">
                                 <form:option value="" label="" />
                                 <form:options items="${depts}" itemValue="id" itemLabel="name" />
                         </form:select>
					</td>
				</tr>
				<tr>
					<td>*Email :</td>
					<td><form:input path="email" /></td>
				</tr>
				<tr>
					<td>*Skype Name :</td>
					<td><form:input path="skypeName" /></td>
				</tr>
				<tr>
					<td>*Job Title :</td>
					<td>
					<form:select path="jobTitleId.id">
                                 <form:option value="" label="" />
                                 <form:options items="${jobTitles}" itemValue="id" itemLabel="name" />
                         </form:select>
					</td>
				</tr>
			</table>
			<button type = "submit" onclick ="save">Save</button>
			<div id="cancelAddBtn-container">
				<button type="button" id="cancelAddBtn" style="width: 60px;">Cancel</button>
			</div>
		</form:form>
	</fieldset>
</div>

<table id="t1">
	<tr>
		<sec:authorize access="hasRole('ROLE_ADMIN')">
			<!-- <th>Task</th>--></sec:authorize> 
			<th>First Name</th>
			<th>Middle Initial</th>
			<th>Last Name</th>
			<th>Department</th>
			<th>Email</th>
			<th>Skype Name</th>
			<th>Job Title</th>
	</tr>
	<c:forEach items="${emps}" var="emp">
		<tr>
			<!-- <sec:authorize access="hasRole('ROLE_ADMIN')"><td>delete</td> </sec:authorize> -->
			<td>${emp.firstName} </td>
			<td>${emp.middleInitial}</td>
			<td>${emp.lastName}</td>
			<td>${emp.department.name}</td>
			<td>${emp.email}</td>
			<td>${emp.skypeName}</td>
			<td>${emp.jobTitleId.name}</td>
			<td><button type="button" id="editBtn" style="width: 45px;">Edit</button></td>
		</tr>
	</c:forEach>
</table>

