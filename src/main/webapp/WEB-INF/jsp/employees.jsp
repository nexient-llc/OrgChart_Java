<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<h3>Employees</h3> 

<div id="addBtn-container">
		<button type="button" id="addBtn" style="width: 40px; font-size:65%;">Add</button>	
		<button type="button" id="delBtn" style="width: 40px; font-size:65%;"> Delete </button>
</div>


<table id="t1"> 
	<tr>	
	<!-- <sec:authorize access="hasRole('ROLE_ADMIN')"> -->
		<!-- <th>Task</th></sec:authorize> --> 
		<td>  </td><th> Employee Name </th> <th> Department </th> <th> Job Title </th> 
	</tr> 
	<c:forEach items="${emps}" var="emp">
		<tr> 	
			<!-- <sec:authorize access="hasRole('ROLE_ADMIN')">
				<td>delete</td>
			</sec:authorize> -->
			<td>	
				<button type="button" class="editBtn" style="width: 25px; font-size:65%" > 
			edit </button>
			</td>	
			<td>${emp.firstName} ${emp.lastName}</td> <td> ${emp.department.name}</td> <td>${emp.jobTitle.name}</td>
												
			
			</tr>
			
			
	</c:forEach> 
</table>


<div id="updateEntity" style="display:none">
	<fieldset>
		<legend>Update Department</legend>
		<form name="newDept" action="update_dept" method="post">
		<div><labeL>Dept Name:</labeL>
		<input id="updateOldName" type="text"
		 name="oldName" style= "display: none;"/>
		
		<input id="updateName" type="text" name="newName"/>
			<labeL>Parent Dept:</label>
			<select name="parent_id">		
				<option value="-1">(none)</option>
				<c:forEach items="${depts}" var="dept">
					<option value="${dept.id}">${dept.name}</option>
				</c:forEach>
			</select>
			<button type="submit">Update</button>
		</div>
		<div></div>
		</form>
	</fieldset>
</div>



<div id="addEntity" style="display:none">
	<fieldset>
		<legend>Add Employee</legend>
		<form name="Employee" action="addEmp" method="post">
		<div><labeL>First Name:</labeL><input type="text" name="firstName"/>
			<br/><labeL>Last Name:</label>
			<input type="text" name="lastName"/>
			
			<br/><labeL>Dept:</label>
			<select name="department.id">		
				<c:forEach items="${depts}" var="dept">
					<option value="${dept.id}">${dept.name}</option>
				</c:forEach>
			</select>
		
			<br/><labeL>e-mail:</labeL><input type="text" name="email"/>
			<br/><labeL> Skype Name:</labeL><input type="text" name= "skypeName"/>
 			
			<br/><labeL>Job Title:</label>
			<select name="jobTitle.id">					
				<c:forEach items="${jobs}" var="job">
					<option value="${job.id}">${job.name}</option>
				</c:forEach>
			</select>
					
			<button type="submit">Save</button>
		</div>
		<div></div>
		</form>
		<button type= "button" class="cancelButton"> cancel </button>
	</fieldset>
</div>