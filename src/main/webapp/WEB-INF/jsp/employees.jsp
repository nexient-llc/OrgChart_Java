<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<h3>Employees</h3> 


<div id= "showFilterBtn-container" style="display:none;">
 <button type="button" id="showFilterBtn" style="width: 40px; font-size:65%;">Show Filter</button>
 </div>

<div id="filterEntity">
	<fieldset>
		<legend>Search by</legend>

<div style="float: right;"> <button type="button" id="hideFilterBtn"style="width: 40px; font-size:65%;" >hide</button>
	
	  </div>
		<form name="search" action="search_emp" method="post">
	<div>		<button type="submit" style="width: 40px; font-size:65%;">Search</button>
			<button type="button" id="searchClear"style="width: 40px; font-size:65%;" >clear</button>
	</div>
		
	<div>
	<label> full name: </label><input id="filterFullName" name= "firstName" type= text/>

	<br/>	
		<label>Department:</labeL>
		<select name="department.id">
				<option value= "-1"></option>
				<c:forEach items="${depts}" var="dept">
					<option value="${dept.id}"> ${dept.name} </option>
				</c:forEach>
			</select>
	
		<label>Job Title:</labeL>
		<select name="jobTitle.id">
				<option value= "-1"></option>
				<c:forEach items="${jobs}" var="job">
					<option value="${job.id}"> ${job.name}</option>
				</c:forEach>
			</select>
	
	
		
		</div>
		
		</form>

	</fieldset>
</div>

<div id="addBtn-container">
		<button type="button" id="addBtn" style="width: 40px; font-size:65%;">Add</button>	
		<button type="button" id="delBtn" style="width: 40px; font-size:65%;"> Delete </button>
</div>

<br/>
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
			<td style="display:none"> ${emp.firstName} </td>
			<td style="display:none"> ${emp.lastName} </td>
			<td style="display:none"> ${emp.email} </td>
			<td style="display:none"> ${emp.skypeName} </td>	
			<td style="display:none"> ${emp.id} </td>	
			<td>${emp.firstName} ${emp.lastName}</td>
			 <td> ${emp.department.name}</td> 
			 <td>${emp.jobTitle.name}</td>																	
		</tr>
	</c:forEach> 
</table>

<div id="addEntity" style="display:none">
	<fieldset>
		<legend>Add Employee</legend>
		<form name="Employee.id" action="addEmp" method="post">
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



<div id="delEntity" style="display:none">
	<fieldset>
		<legend>Delete Department</legend>
		<form name="oldEmp" action="remove_emp" method="post">
		<div><labeL>Dept Name:</labeL>

		<select name="id">
				<option value= "-1"></option>
				<c:forEach items="${emps}" var="emp">
					<option value="${emp.id}"> ${emp.firstName} ${emp.lastName}${emp.id} </option>
				</c:forEach>
			</select>
	
			<button type="submit">Delete</button>
		</div>
		<div></div>
		</form>
	</fieldset>
</div>

<div id="updateEntity" style="display:none">
	<fieldset>
		<legend>Update Employee</legend>
		<form name="newDept" action="update_emp" method="post">
		<div>
		
		<labeL> First Name: </labeL>
		<input id="firstName" type="text"
		 name="firstName"/>
		 
		 <br/>
		
		<label>Last Name: </label>
		<input id="lastName" type="text" name="lastName"/>
		<br/>	
			<label>Department: </label>			
			<select name="department.id">					
				<c:forEach items="${depts}" var="dept">
					<option value="${dept.id}">${dept.name}</option>
				</c:forEach>
			</select>
			<br/>
			<label>Job Title: </label>			
			<select name="jobTitle.id">					
				<c:forEach items="${jobs}" var="job">
					<option value="${job.id}">${job.name}</option>
				</c:forEach>
			</select>
			
			<br/>
		<label>E-mail: </label>
		<input id="email" type="text" name="email"/>
		
		<br/>		
		<label>Skype name: </label>
		<input id="skypeName" type="text" name="skypeName"/>
		<input id= "id"  name="id"  style="display:none"/>
			
			<br/>
			<button type="submit">Update</button>
		</div>
		<div></div>
		</form>
	</fieldset>
</div>

