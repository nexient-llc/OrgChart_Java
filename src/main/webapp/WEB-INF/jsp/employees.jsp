<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<div class="pageTitle"> 
	<h2>Employees</h2>
</div>

<div id="empSearch">

	<h3>Search</h3>
	
	<table>
		<form id="findByID" name="findByID" action="emps" method="get">
			<tr>
				<td class="empLabel">ID:</td>
				<td>
					<input type="text" id="findEmpID" name="findEmpID" class="empInput" />
				</td>
				<td>
					<button type="button" id="findByIDBtn" name="findByIDBtn">Search</button>
				</td>
			</tr>
		</form>
		
		<form id="findByName" id="findByName" action="emps" method="get">
			
			<input type="hidden" id="searchName" name="searchName" value="true" />
			
			<tr>
				<td class="empLabel"><span>*</span> First Name:</td>
				<td>
					<input type="text" id="findFirstName" name="findFirstName" class="empInput" />
				</td>
				<td rowspan="2">
					<button type="button" id="findByNameBtn" name="findByNameBtn">Search</button>
				</td>
			</tr>
			<tr>
				<td class="empLabel"><span>*</span> Last Name:</td>
				<td>
					<input type="text" id="findLastName" name="findLastName" class="empInput" /> 
				</td>
			</tr>
		</form>
		
		<form id="findByEmail" name="findByEmail" action="emps" method="get">
			<tr>
				<td class="empLabel"><span>**</span> Email:</td>
				<td>
					<input type="text" id="findEmail" name="findEmail" class="empInput" />
				</td>
				<td>
					<button type="button" id="findByEmailBtn" name="findByEmailBtn">Search</button>
				</td>
			</tr>
		</form>
		
		<form id="findByDept" name="findByDept" action="emps" method="get">
			<tr>
				<td class="empLabel">Department:</td>
				<td>
					<select id="deptSelection" name="deptSelection" class="empInput">
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
		
		<form id="findByTitle" name="findByTitle" action="emps" method="get">
			<tr>
				<td class="empLabel">Job Title:</td>
				<td>
					<select id="titleSelection" name="titleSelection" class="empInput">
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
		
		<form id="findByManager" name="findByManager" action="emps" method="get">
			<tr>
				<td class="empLabel">Manager:</td>
				<td>
					<select id="mgrSelection" name="mgrSelection" class="empInput">
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
			<td></td>
			<td>
				<div id="viewAll-container">
					<form name="empsAll" action="emps" method="get">
						<input type="hidden" id="viewAllEmps" name="viewAllEmps" value="true" />
						<button type="submit">View All</button>
					</form>
				</div>
				
				<div id="addBtn-container">
					<form name="empsAddNew" action="emps" method="get">
						<input type="hidden" id="addNew" name="addNew" value="true" />
						<button type="submit" id="addBtn">Add New</button>
					</form>
				</div>
			</td>
			<td></td>
		</tr>
	</table>
	
	<p id="empSubtext"><span>*</span> Employees may be searched by using a full, or partial, first or last name. 
	You may also search for an employee by using a combination of first and last name.</p>
	
	<p id="empSubtext"><span>**</span> Email searches my also be performed by using an employee's whole or partial 
	address.</p>

</div>

<c:if test="${not empty msg}">
	<div id="errorMsg"> 
		<h3>${msg}</h3>
	</div>
</c:if>

<c:if test="${not empty empList}">
	<div id="empDisplay">	
		<h3>Employee List</h3>
		
		<table id="t1" cellpadding="4px" cellspacing="0"> 
			<tr> 
				<th>Employee Name</th>
				<th>Department</th>
				<th>Job Title</th>
				<th></th>
			</tr> 
			<c:forEach items="${empList}" var="emp" varStatus="loopStatus">
				<tr id="${emp.empID}" name="${emp.empID}" style="background-color: ${loopStatus.index % 2 == 0 ? '#fff' : '#cfcece'}">
					<td>
						${fn:toLowerCase(emp.firstName)} ${fn:toLowerCase(emp.lastName)}
					</td> 
					
					<td>${fn:toLowerCase(emp.dept.name)}</td> 
					
					<td>${fn:toLowerCase(emp.jobTitle.desc)}</td>
					
					<td>
						<button class="editLink">edit</button>
						<button class="deleteBtn">delete</button>
					</td>
				</tr>
			</c:forEach> 
		</table>
	</div>
</c:if>

<c:if test="${not empty addEmp}">
	<div id="addEmpEntity">
		
		<h3>Add New Employee</h3>
	
		<fieldset class="empFields">
			<form id="newEmp" name="newEmp" action="emps" method="post">				
				<div>
					<table id="newEmpTable">
						<tr>
							<td><label>First Name:</labeL></td>
							<td>
								<input type="text" name="firstName" id="firstName" />
							</td>
						</tr>
						<tr>
							<td><label>Last Name:</label></td>
							<td>
								<input type="text" name="lastName" id="lastName" />
							</td>
						</tr>
						<tr>
							<td><label>Department:</label></td>
							<td>
								<select name="departmentId" id="departmentId">
									<option value="0">...</option>
									<c:forEach items="${depts}" var="dept">
											<option value="${dept.departmentId}">${dept.name}</option>
									</c:forEach>
								</select>
							</td>
						</tr>
						<tr>
							<td><label>Email:</label></td>
							<td>
								<input type="text" name="email" id="email" />
							</td>
						</tr>
						<tr>
							<td><label>Skype:</label></td>
							<td>
								<input type="text" name="skypeName" id="skypeName" />
							</td>
						</tr>
						<tr>
							<td><labeL>Job Title:</label></td>
							<td>
								<select name="jobTitleID" id="jobTitleID">
									<option value="0">...</option>
									<c:forEach items="${jobs}" var="title">
										<option value="${title.jobTitleID}">${title.desc}</option>
									</c:forEach>
								</select>
							</td>
						</tr>
						<tr>
							<td>Manager</td>
							<td>
								<select name="managerID" id="managerID">
									<option value="0">...</option>
									<c:forEach items="${emps}" var="mgr">
										<c:if test="${mgr.isManager == true}">
											<option value="${mgr.empID}">${fn:toLowerCase(mgr.firstName)} ${fn:toLowerCase(mgr.lastName)}</option>
										</c:if>
									</c:forEach>
								</select>
							</td>
						</tr>
						<tr>
							<td><label>Is Manager: </labeL></td>
							<td><input type="checkbox" name="isManager"/>Yes</td>
						</tr>
						<tr>
							<td colspan="2" class="actionBtns">
								<button  type="button" class="cancelBtn">Cancel</button>
								<button type="button" class="saveBtn">Save</button>
							</td>
						</tr>
					</table>
				</div>
			</form>
		</fieldset>
	</div>
</c:if>

<c:if test="${not empty editEmp}">
	<div id="editEmpEntity">
		
		<h3>Edit Employee Information</h3>
		
		<fieldset class="empFields">
		<form id="editEmp" name="editEmp" action="emps" method="post">
			<div>
				<input type="hidden" name="editEmpID" id="editEmpID" value="${editEmp.empID}"/>
				<table id="editEmpTable">
					<tr>
						<td><label>First Name:</labeL></td>
						<td><input type="text" name="editFirstName" id="editFirstName" value="${editEmp.firstName}"/></td>
					</tr>
					<tr>
						<td><label>Last Name:</label></td>
						<td><input type="text" name="editLastName" id="editLastName" value="${editEmp.lastName}"/></td>
					</tr>
					<tr>
						<td><label>Department:</label></td>
						<td><select name="editDepartmentId" id="editDepartmentId">
							<option value="0">...</option>
							<c:forEach items="${depts}" var="dept">
								<option value="${dept.departmentId}"
									<c:if test="${editEmp.dept.departmentId == dept.departmentId}">selected="selected"</c:if>
								>${dept.name}</option>
							</c:forEach>
							</select></td>
					</tr>
					<tr>
						<td><label>Email:</label></td>
						<td><input type="text" name="editEmail" id="editEmail" value="${editEmp.email}"/></td>
					</tr>
					<tr>
						<td><label>Skype:</label></td>
						<td><input type="text" name="editSkypeName" id="editSkypeName" value="${editEmp.skypeName}"/></td>
					</tr>
					<tr>
						<td><labeL>Job Title:</label></td>
						<td><select name="editJobTitleID" id="editJobTitleID">
								<option value="0">...</option>
								<c:forEach items="${jobs}" var="title">
									<option value="${title.jobTitleID}"
										<c:if test="${editEmp.jobTitle.jobTitleID == title.jobTitleID}">selected="selected"</c:if>
									>${title.desc}</option>
								</c:forEach>
							</select></td>
					</tr>
					<tr>
							<td>Manager</td>
							<td>
								<select name="editManagerID" id="editManagerID">
									<option value="0">...</option>
									<c:forEach items="${emps}" var="mgr">
										<c:if test="${mgr.isManager == true}">
											<option value="${mgr.empID}" 
												<c:if test="${editEmp.manager.empID == mgr.empID}">selected="selected"</c:if>
											>${fn:toLowerCase(mgr.firstName)} ${fn:toLowerCase(mgr.lastName)}</option>
										</c:if>
										
									</c:forEach>
								</select>
							</td>
						</tr>
					<tr>
						<td><label>Is Manager: </labeL></td>
						<td><input type="checkbox" name="editIsManager" <c:if test='${editEmp.isManager}'>checked</c:if> /></td>
					</tr>
					
					<tr>
						<td colspan="2" class="actionBtns">
							<button type="button" class="cancelBtn">Cancel</button>
							<button type="button" class="updateBtn">Update</button>
							<button type="button" class="deleteBtn">Delete</button>
						</td>
					</tr>
				</table>
			</div>
		</form>
	</fieldset>
	</div>
</c:if>



<form id="deleteEmpForm" action="emps" method="post">
	<input type="hidden" id="deleteEmpID" name="deleteEmpID" value="0" />
</form>