<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
 <link rel="stylesheet" href="//code.jquery.com/ui/1.11.2/themes/smoothness/jquery-ui.css">
  <script src="//code.jquery.com/jquery-1.10.2.js"></script>
  <script src="//code.jquery.com/ui/1.11.2/jquery-ui.js"></script>
    <style>
    .ui-menu-item{
    	font-size: .9em;   
    }
    
	.ui-dialog-titlebar-close {
	  visibility: hidden;
	}
	
	#searchEmp label {
		display: inline;	
	}
  </style>
  
<h3>Employees</h3> 

<div id="searchBtn-container">
		<button type="button" id="searchBtn" style="width: 55px;">Search</button>	
</div><br>

<div id="form-filter" title="Filter Employees" style="display:none">
	<fieldset>
		<form name="searchEmp" id="searchEmp" action="emps" method="post">
			<label>Name:</label> <input type="text" name="firstName" id="autocompleteText" maxlength="20"/>&nbsp;
				<labeL>Job Title:</labeL>
					<select name="jobTitleId" id="jobTitle">
						<option></option>
						<c:forEach items="${jobTitles}" var="jobTitle">
							<option value="${jobTitle.id}">${jobTitle.name}</option>
						</c:forEach>
					</select>&nbsp;
				<labeL>Department</labeL>
					<select name="departmentId" id="department">
						<option></option>
						<c:forEach items="${depts}" var="dept">
							<c:choose>
								<c:when test="${dept.isActive}">	
									<option value="${dept.id}">${dept.name}</option>
								</c:when>
							</c:choose>
						</c:forEach>
					</select>&nbsp;
				<button type="submit" id="search" name="search" value="true">Search</button>
				<button type="reset" id="cancelBtn" name="cancelBtn">Cancel</button>			
		</form>		
	</fieldset>
</div>
<div id="addBtn-container">
		<button type="button" id="addBtn" style="width: 45px;">Add</button>	
</div><br>

<table> 
	<!-- <sec:authorize access="hasRole('ROLE_ADMIN')"> -->
		<!-- <th>Task</th></sec:authorize> --> 
		<tr>
		<th>First Name</th><th>Last Name</th><th>Job Title</th><th>Department</th><th>Edit</th>
		</tr>		
	<c:forEach items="${emps}" var="emp">
		<tr> 
			<!-- <sec:authorize access="hasRole('ROLE_ADMIN')">
				<td>delete</td>
			</sec:authorize> -->
			<td>${emp.firstName}</td>
			<td>${emp.lastName }</td> 
			<td>${emp.jobTitle.name}</td>
			<td>${emp.department.name}</td>
			<td><button class="editButton" value="${emp.id}">Edit</button></td></tr>
	</c:forEach> 
	
</table>

<div id="dialog-form-make" title="Add New Employee">
	<fieldset>
		<form name="newEmp" action="emps" method="post">
		<br>
		<div><labeL>*First Name:</labeL>
				<input type="text" name="firstName" id="firstName" required maxlength="20"/><br><br>
			<labeL>Middle Initial:</labeL>
				<input type="text" name="middleInitial" id="middle_initial" maxlength="1"/><br><br>
			<labeL>*Last Name:</labeL>
				<input type="text" name="lastName" id="lastName" required maxlength="50"/><br><br>
			<labeL>*Email:</labeL>
				<input type="text" name="email" id="email" required maxlength="100"/><br><br>
			<labeL>*Skype Name:</labeL>
				<input type="text" name="skypeName" id="skypeName" required maxlength="100"/><br><br>
			<labeL>Is a Manager?:</labeL>
				<select name="isManager" id="isManager">
					<option>False</option>
					<option>True</option>
				</select><br><br>
			<labeL>*Job Title:</labeL>
				<select name="jobTitleId" id="jobTitle">
					<c:forEach items="${jobTitles}" var="jobTitle">
						<option value="${jobTitle.id}">${jobTitle.name}</option>
					</c:forEach>
				</select><br><br>
			<labeL>*Department</labeL>
				<select name="departmentId" id="department">
					<c:forEach items="${depts}" var="dept">
						<c:choose>
							<c:when test="${dept.isActive}">												
									<option value="${dept.id}">${dept.name}</option>
							</c:when>
						</c:choose>
					</c:forEach>
				</select><br><br>
			<div> <p class="validateTips">Required Fields indicated with a *</p></div><br>
			<button type="submit" >Save</button>
			<button type="reset" name="resetMakeBtn" id="resetMakeBtn">Cancel</button>
		</div>
		</form>
	</fieldset>
</div>

<div id="dialog-form-edit" title="Edit Employee">
	<fieldset>
		<form name="editEmp" action="emps" method="post">
		<div>
			<br>
			<input type="hidden" id="employeeId" name="id"/>
			<labeL>*First Name:</labeL>
				<input type="text" name="firstName" id="empFirstName" required maxlength="20"/><br><br>
			<labeL>Middle Initial:</labeL>
				<input type="text" name="middleInitial" id="empMiddleInitial" maxlength="1"/><br><br>
			<labeL>*Last Name:</labeL>
				<input type="text" name="lastName" id="empLastName" required maxlength="50"/><br><br>
			<labeL>*Email:</labeL>
				<input type="text" name="email" id="empEmail" required maxlength="100"/><br><br>
			<labeL>*Skype Name:</labeL>
				<input type="text" name="skypeName" id="empSkypeName" required maxlength="100"/><br><br>
			<labeL>Is a Manager?:</labeL>
				<select name="isManager" id="empIsManager">
					<option>False</option>
					<option>True</option>
				</select><br><br>
			<labeL>*Job Title:</labeL>
				<select name="jobTitleId" id="empJobTitleId">
					<c:forEach items="${jobTitles}" var="jobTitle">
						<option value="${jobTitle.id}">${jobTitle.name}</option>
					</c:forEach>
				</select><br><br>
			<labeL>*Department</labeL>
				<select name="departmentId" id="empDepartmentId">
					<c:forEach items="${depts}" var="dept">
						<c:choose>
							<c:when test="${dept.isActive}">												
									<option value="${dept.id}">${dept.name}</option>
							</c:when>
						</c:choose>
					</c:forEach>
				</select><br><br>
			<div> <p class="validateTips">Required Fields indicated with a *</p></div><br>
			<button type="submit" >Save</button>
			<button type="reset" name="resetEditBtn" id="resetEditBtn">Cancel</button>
		</div>
		</form>
	</fieldset>
</div>


