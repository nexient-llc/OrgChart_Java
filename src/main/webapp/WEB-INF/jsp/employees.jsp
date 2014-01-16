<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<h3>Employees</h3>

<div id="addBtn-container">
		<button type="button" id="addBtn">Add</button>	
</div>
<div id="addEntity" style="display:none">
	<fieldset>
		<legend>Add Employee</legend>
		<form:form modelAttribute="newEmp" action="emps" method="post">
			<table>
				<tr>
					<td>
						<label>First Name:</label>
						<form:input path="firstName"/>
					</td>
					<td>
						<label>Last Name:</label>
						<form:input path="lastName"/>
					</td>
				</tr>
				<tr>
					<td>
						<label>Email:</label>
						<form:input path="email"/>
					</td>
				
				
					<td>
						<label>Skype:</label>
						<form:input path="skypeName"/>
					</td>
				</tr>
				<tr>
					<td>
						<label>Department:</label>
						<form:select path="department.id">
							<form:option value=""/>
							<c:forEach items="${depts}" var="dept">
								<c:if test="${dept.isActive}">
									<option label="${dept.name}" value="${dept.id}"/>
								</c:if>
							</c:forEach>
						</form:select>
					</td>
					<td>
						<label>Job Title:</label>
						<form:select path="jobTitle.id">
							<form:option value=""/>
							<form:options items="${jobs}" itemLabel="name" itemValue="id"/>
						</form:select>
					</td>
				</tr>
				<tr>
					<td></td>
					<td class="saveCancel">
						<input type="submit" value="Save" />
						<button id="cancelAddBtn">Cancel</button>
					</td>
				</tr>
			</table>
		</form:form>
	</fieldset>
</div>

<table id="t1"> 
	<tr><!-- <sec:authorize access="hasRole('ROLE_ADMIN')"> -->
		<!-- <th>Task</th></sec:authorize> --> 
		<th>Employee</th> <th></th> <th>Email</th> <th>Skype</th> <th>Department</th> <th>Job Title</th> <th></th> <th></th>
	</tr> 
	<c:forEach items="${emps}" var="emp">
		<tr id="row${emp.id}"> 
			<!-- <sec:authorize access="hasRole('ROLE_ADMIN')">
				<td>delete</td>
			</sec:authorize> -->
			<td class="firstName" data-value="${emp.firstName}">${emp.firstName}</td>
			<td class="lastName" data-value="${emp.lastName}">${emp.lastName}</td>
			<td class="email" data-value="${emp.email}">${emp.email}</td>
			<td class="skype" data-value="${emp.skypeName}">${emp.skypeName}</td> 
			<td class="dept" data-value="${emp.department.id}">${emp.department.name}</td> 
			<td class="job" data-value="${emp.jobTitle.id}">${emp.jobTitle.name}</td> 
			<td><button class="editBtn" value="${emp.id}">Edit</button></td>		
		</tr>
		
		<tr id="editRow${emp.id}" style="display:none">
			<td><input name="firstName" class="firstName"/></td>
			<td><input name="lastName" class="lastName"/></td>
			<td><input name="email" class="email"/></td>
			<td><input name="skypeName" class="skype"/></td>
			<td><select name="department.id" class="dept">
					<option value="" />
					<c:forEach items="${depts}" var="tDept">
						<c:if test="${tDept.isActive}">
							<option value="${tDept.id}">${tDept.name}</option>
						</c:if>
					</c:forEach>
				</select>
			</td>
			<td><select name="jobTitle.id" class="job">
					<option value="" />
					<c:forEach items="${jobs}" var="tJob">
						<option value="${tJob.id}">${tJob.name}</option>
					</c:forEach>
				</select>
			</td>
			<td><button class="saveBtn" value="${emp.id}">Save</button></td>
			<td><button class="cancelEditBtn" value="${emp.id}">Cancel</button></td>
		</tr>
	</c:forEach> 
</table>