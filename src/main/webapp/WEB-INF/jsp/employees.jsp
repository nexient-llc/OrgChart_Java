<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<div class="pageTitle"> 
	<h2>Employees</h2>
</div>

<div id="empSearch">

	<h3>Search</h3>
	
	<table>
		<form style="border:'1px solid black;'">
			<tr>
				<td class="empLabel">ID:</td>
				<td>
					<input type="text" id="findEmpID" name="findEmpID" class="empInput" />
				</td>
				<td>
					<button type="button" id="findByID" name="findByIDBtn">Search</button>
				</td>
			</tr>
		</form>
		
		<form>
			<tr>
				<td class="empLabel"><span>*</span> First Name:</td>
				<td>
					<input type="text" id="findFirstName" name="findFirstName" class="empInput" />
				</td>
				<td rowspan="2">
					<button type="button" id="findByName" name="findByNameBtn">Search</button>
				</td>
			</tr>
			<tr>
				<td class="empLabel"><span>*</span> Last Name:</td>
				<td>
					<input type="text" id="findLastName" name="findLastName" class="empInput" /> 
				</td>
			</tr>
		</form>
		
		<form>
			<tr>
				<td class="empLabel">Email:</td>
				<td>
					<input type="text" id="findEmail" name="findEmail" class="empInput" />
				</td>
				<td>
					<button type="button" id="findByEmail" name="findByEmailBtn">Search</button>
				</td>
			</tr>
		</form>
		
		<form>
			<tr>
				<td class="empLabel">Department:</td>
				<td>
					<select id="deptSelection" class="empInput">
						<option value="0">...</option>
						<c:forEach items="${depts}" var="dept">
							<option value="${dept.departmentId}">${fn:toLowerCase(dept.name)}</option>
						</c:forEach>
					</select>
				</td>
				<td>
					<button type="button" id="findByDeptBtn" name="findByDeptBtn">Search</button>
				</td>
			</tr>
		</form>
		
		<form>
			<tr>
				<td class="empLabel">Job Title:</td>
				<td>
					<select id="titleSelection" class="empInput">
						<option value="0">...</option>
						<c:forEach items="${jobs}" var="title">
							<option value="${title.jobTitleID}">${fn:toLowerCase(title.desc)}</option>
						</c:forEach>
					</select>
				</td>
				<td>
					<button type="button" id="findTitleBtn" name="findTitleBtn">Search</button>
				</td>
			</tr>
		</form>
		
		<form>
			<tr>
				<td class="empLabel">Manager:</td>
				<td>
					<select id="mgrSelection" class="empInput">
						<option value="0">...</option>
						<c:forEach items="${emps}" var="mgr">
							<c:if test="${mgr.isManager == true}">
								<option value="${mgr.empID}">${fn:toLowerCase(mgr.firstName)} ${fn:toLowerCase(mgr.lastName)}</option>
							</c:if>
						</c:forEach>
					</select>
				</td>
				<td>
					<button type="button" id="findByManagerBtn" name="findByManagerBtn">Search</button>
				</td>
			</tr>
		</form>
		
		<tr>
			<td colspan="3">
				<div id="viewAll-container">
					<form name="empsAll" action="emps" method="get">
						<input type="hidden" id="viewAllEmps" name="viewAllEmps" value="true" />
						<button type="submit">View All</button>
					</form>
				</div>
				
				<div id="addBtn-container">
					<button type="button" id="addBtn">Add New</button>
				</div>
			</td>
		</tr>
	</table>

</div>

<c:if test="${empty empList}">
	<div id="empDisplay">
	
		<h3>Employee List</h3>
		
		<table id="t1" cellpadding="4px" cellspacing="0"> 
			<tr> 
				<th>Employee Name</th>
				<th>Department</th>
				<th>Job Title</th>
				<th></th>
			</tr> 
			<c:forEach items="${emps}" var="emp">
				<tr name="${emp.empID}">
					<td>
						${fn:toLowerCase(emp.firstName)} ${fn:toLowerCase(emp.lastName)}
					</td> 
					
					<td>${fn:toLowerCase(emp.dept.name)}</td> 
					
					<td>${fn:toLowerCase(emp.jobTitle.desc)}</td>
					
					<td>
						<button id="${emp.empID}/edit" class="editLink">edit</button>
						<button id="${emp.empID}/delete" class="deleteLink">delete</button>
					</td>
				</tr>
			</c:forEach> 
		</table>
	
		<form id="editEmpForm" action="edit" method="post">
			<input type="hidden" id="hiddenEditEmpID" name="hiddenEditEmpID" />
		</form>
		
		<form id="deleteEmpForm" action="basicDelete" method="post">
			<input type="hidden" id="hiddenEmpID2" name="hiddenEmpID2" value="test" />
		</form>
	
		
	
	</div>
</c:if>

<c:if test="${not empty empList}">
<div id="addEmpEntity">
	
	<c:choose>
	<c:when test="${empty selectedEmp}">
	<h3>Add New Employee</h3>

	<fieldset class="empFields">
		<legend id="addChange-legend">Add Employee</legend>
		<form name="newEmp" action="emps" method="post">
			<div>
				<table id="newEmpTable">
					<tr>
						<td><label>First Name:</labeL></td>
						<td><input type="text" name="firstName"/></td>
					</tr>
					<tr>
						<td><label>Last Name:</label></td>
						<td><input type="text" name="lastName"/></td>
					</tr>
					<tr>
						<td><label>Department:</label></td>
						<td><select name="departmentId">
							<option>...</option>
							<c:forEach items="${depts}" var="dept">
								<option value="${dept.departmentId}">${dept.name}</option>
							</c:forEach>
							</select></td>
					</tr>
					<tr>
						<td><label>Email:</label></td>
						<td><input type="text" name="email"/></td>
					</tr>
					<tr>
						<td><label>Skype:</label></td>
						<td><input type="text" name="skypeName"/></td>
					</tr>
					<tr>
						<td><labeL>Job Title:</label></td>
						<td><select name="jobTitleID">
								<option>...</option>
								<c:forEach items="${jobs}" var="title">
									<option value="${title.jobTitleID}">${title.desc}</option>
								</c:forEach>
							</select></td>
					</tr>
					<tr>
						<td><label>Manager: </labeL></td>
						<td><input type="checkbox" name="isManager"/></td>
					</tr>
					<tr>
						<td><button type="submit">Save</button></td>
						<td></td>
					</tr>
				</table>
			</div>
		</form>
	</fieldset>
	</c:when>
	
	<c:otherwise>
		<script type="text/javascript">
			$(document).ready(
					$('#addChangeEntity').toggle("slow", 
							function()
							{ 
								$('#empDisplay').css('border-width','1px');
								$('#empDisplay').css('border-style', 'none dashed none none');
								$('#empDisplay').css('border-color', '#cfcece');
								$('#empDisplay').css('height', $('#pageBody').height());
							}
					).css('display', 'inline-block')
			);
			
		</script>
		HELLO WORLD
	</c:otherwise>
	</c:choose>
</div>
</c:if>