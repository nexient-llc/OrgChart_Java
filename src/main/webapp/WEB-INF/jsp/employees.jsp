<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<h3>Employees</h3> 

<div id="addBtn-container">
		<button type="button" id="addBtn" style="width: 45px;">Add</button>	
</div>

<div id="addEntity" style="display:none">
	<fieldset>
		<legend>Add Employee</legend>
		<form:form modelAttribute="emp" action="emps" method="post">
			<table>
				<tr>
					<td>First Name*:</td>
					<td><form:input path="firstName" maxlength="20" /></td>
					<td>Middle Initial:</td>
					<td><form:input path="middleInitial" maxlength="1" size="1" /></td>
					<td>Last Name*:</td>
					<td><form:input path="lastName" maxlength="50"/></td>
				</tr>
				<tr>
					<td>Department*:</td>
					<td><form:select path="department.id">
							<option value=""/>
							<c:forEach items="${depts}" var="depart">
								<option label="${depart.name}" value="${depart.id}"/>
							</c:forEach>
					</form:select></td>
					<td>Job Title*:</td>
					<td><form:select path="jobTitle.id">
						<option value=""/>
						<c:forEach items="${jobs}" var="userJob">
							<option label="${userJob.name}" value="${userJob.id}"/>
						</c:forEach>
					</form:select></td>
				</tr>
				<tr>
					<td>Email*:</td>
					<td><form:input path="email" maxlength="100"/></td>
					<td>Skype Name*:</td>
					<td><form:input path="skypeName" maxlength="100"/></td>
				</tr>
				<tr><i>*Required Field</i></tr>
			</table>
			<input type=submit />
			<button id="cancelBtn">Cancel</button>
		</form:form>
	</fieldset>
</div>

<table id="t1"> 
	<tr><!-- <sec:authorize access="hasRole('ROLE_ADMIN')"> -->
		<!-- <th>Task</th></sec:authorize> --> 
		<th>Employee Name</th> <th>Department</th> <th>Job Title</th>
	</tr> 
	<c:forEach items="${emps}" var="employee">
		<tr id="ViewEmps${emp.id}"> 
			<!-- <sec:authorize access="hasRole('ROLE_ADMIN')">
				<td>delete</td>
			</sec:authorize> -->
			<td>${employee.firstName} ${employee.lastName}</td> 
			<td>${employee.department.name}</td>
			<td>${employee.jobTitle.name }</td>
		</tr>
	</c:forEach>
</table>