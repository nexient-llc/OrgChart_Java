<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
	
<h3>Employee Page</h3>

<button type="button" id="filterBtn" onClick="toggleFilter()" style="width: 45px;">Filter</button>

<div id="filterEntity" style="display:none">
	<fieldset>
		<legend>Filter Employees</legend>
		<form name="filterEmp" action="empsfilter" method="post">
			<label>First/Last Name:</label><input type="text" name="name" />
			<label>Department:</label>
			<select name="departmentId">
				<option></option>
				<c:forEach items="${depts}" var="dept">
					<option value="${dept.id}">${dept.name}</option>
				</c:forEach>
			</select>
			<label>Job Title:</label>
			<select name="jobTitle">
				<option></option>
				<c:forEach items="${jobTitles}" var="jobTitle">
					<option value="${jobTitle.id}">${jobTitle.name}</option>
				</c:forEach>
			</select>
			<br/><button type="submit">Search</button>
			<button type="reset">Reset</button>
		</form>
	</fieldset>
</div>

<div id="addBtn-container">
		<button type="button" id="addBtn" style="width: 45px;">Add</button>	
</div>

<table id="t1"> 
	<tr><!-- <sec:authorize access="hasRole('ROLE_ADMIN')"> -->
		<!-- <th>Task</th></sec:authorize> --> 
		<th>Employee Name</th> <th>Department</th> <th>Job Title</th><th></th>
	</tr> 
	<c:forEach items="${emps}" var="emp">
		<tr> 
			<!-- <sec:authorize access="hasRole('ROLE_ADMIN')">
				<td>delete</td>
			</sec:authorize> -->
			<td>${emp.firstName} ${emp.lastName}</td> 
			<td>${emp.department.name}</td>
			<td>${emp.jobTitle.name}</td>
			<td><div class="editBtn-container">
				<button type="button" id=${emp.id} class="editBtn" onClick="editEmployee(${emp.id})" style="width: 45px;">Edit</button>	
            </div></td>
         </tr>
	</c:forEach> 
</table>

<div id="addEntity" style="display:none">
	<fieldset>
		<legend>Add Employee</legend>
		<form name="newEmp" action="emps" method="post">
		<div>
			<label>First Name*:</label><input type="text" name="firstName" required />
			<label>Last Name*:</label><input type="text" name="lastName" required />
			<label>Middle Initial:</label><input type="text" name="middleInitial" />
			<label>Department*:</label>
			<select name="departmentId" required>
				<option></option>
				<c:forEach items="${depts}" var="dept">
					<option value="${dept.id}">${dept.name}</option>
				</c:forEach>
			</select>
			<label>Email*:</label><input type="email" name="email" required />
			<label>Skype Name*:</label><input type="text" name="skypeName" required />
			<br/>
			<label>Job Title*:</label>
			<select name="jobTitle" required>
				<option></option>
				<c:forEach items="${jobTitles}" var="jobTitle">
					<option value="${jobTitle.id}">${jobTitle.name}</option>
				</c:forEach>
			</select>
			<br/><button type="submit">Save</button>
			<button type="reset" onClick="toggleSave()">Cancel</button>
			<br/><footer>Required fields indicated with a "*"</footer>
		</div>
		<div></div>
		</form>
	</fieldset>
</div>

<c:forEach items="${emps}" var="emp">
<div id="editEntity${emp.id}" style="display:none">
	<fieldset>
		<legend>Edit Employee</legend>
		<form name="editEmp" action="empsedit" method="post">
		<input type="text" name="id" value="${emp.id}" style="display:none"/>
		<div><labeL>First Name*:</labeL><input type="text" name="firstName" required value="${emp.firstName}" />
			<labeL>Last Name*:</labeL><input type="text" name="lastName" required value="${emp.lastName}" />
			<label>Middle Initial:</label><input type="text" name="middleInitial" value="${emp.middleInitial}" />
			<labeL>Department*:</label>
			<select name="departmentId" required>
				<option></option>
				<c:forEach items="${depts}" var="dept">
					<c:choose>
						<c:when test="${dept.id == emp.department.id}">
							<option value="${dept.id}" selected>${dept.name}</option>
						</c:when>
						<c:otherwise>
							<option value="${dept.id}">${dept.name}</option>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</select>
			<label>Email*:</label><input type="email" name="email" value="${emp.email}" required />
			<label>Skype Name*:</label><input type="text" name="skypeName" value="${emp.skypeName}" required />
			<br/>
			<label>Job Title*:</label>
			<select name="jobTitle" required>
				<option></option>
				<c:forEach items="${jobTitles}" var="jobTitle">
					<c:choose>
						<c:when test="${jobTitle.id == emp.jobTitle.id}">
							<option value="${jobTitle.id}" selected>${jobTitle.name}</option>
						</c:when>
						<c:otherwise>
							<option value="${jobTitle.id}">${jobTitle.name}</option>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</select>
			<button type="submit">Save</button>
			<button type="reset" onClick="editEmployee(${emp.id})">Cancel</button>
		</div>
		<div></div>
		<footer>Required fields indicated with a "*"</footer>
		</form>
	</fieldset>
</div>
</c:forEach>
