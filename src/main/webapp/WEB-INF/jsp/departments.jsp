<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<div class="pageTitle"> 
	<h2>Departments</h2>
</div>

<div id="deptSearch">
	
	<h3>Search</h3>
	
	<div>
		<table>
			<form name="deptByID" id="deptByID" action="depts" method="get">
				<tr>
					<td class="deptLabel">ID:</td>
					<td><input type="text" id="deptID" name="deptID" class="deptInput" />
					<td><button type="button" id="ByIDButton">Search</button></td>
				</tr>
			</form>
			
			<form name="deptByName" id="deptByName" action="depts" method="get">	
				<tr>
					<td class="deptLabel"><span>*</span> Name:</td>
					<td><input type="text" id="deptName" name="deptName" class="deptInput" />
					<td><button type="button" id="ByNameButton">Search</button></td>
				</tr>	
			</form>
			
			
			<tr>
				<td></td>
				<td>
					<div id="viewAll-container">
						<form name="deptAll" action="depts" method="get">
							<input type="hidden" id="viewAllDepts" name="viewAllDepts" value="true" />
							<button type="submit">View All</button>
						</form>
					</div>
					
					<div id="addBtn-container">
						<form id="addNewDept" action="depts" method="get">
							<input type="hidden" id="addDept" name="addDept" value="true" />
							<button type="submit">Add New</button>
						</form>
					</div>
				</td>
				<td></td>
			</tr>
		</table>
		
		<br/>
		
		<p id="deptSubtext"><span>*</span> Departments may be searched by using a full or partial name.</p>
	</div>

</div>

<c:if test="${not empty msg}">
	<div id="errorMsg"> 
		<h3>${msg}</h3>
	</div>
</c:if>

<c:if test="${not empty depts}">
	<div id="deptDisplay">
	
		<h3>Department List</h3>
		
		<table id="t1" cellspacing="0px" cellpadding="4px"> 
			<tr>
				<th>Department Name</th>
				<th>Parent Department</th>
				<th></th>
			</tr> 
			
			<c:forEach items="${depts}" var="dept" varStatus="loopStatus">
				<tr id="${dept.departmentId}" style="background-color: ${loopStatus.index % 2 == 0 ? '#fff' : '#cfcece'}"> 
					<td>${fn:toLowerCase(dept.name)}</td> 
					<td>${fn:toLowerCase(dept.parentDepartment.name)}</td>
					<td class="actionBtns">
						<button id="edit" class="editLink">edit</button>
						<button id="delete" class="deleteLink">delete</button>
					</td>
				</tr>
			</c:forEach> 
		</table>
		
	</div>
</c:if>

<c:if test="${not empty addDept}">
	<div id="addDeptEntity">
		<h3>Add New Department</h3>
		
		<fieldset class="deptFields">
			
			<form name="newDept" id="newDept" action="depts" method="post">
			
				<input type="hidden" name="addNew" value="true" />
				
				<table>
					<tr>
						<th>
							<label>Department Name:</label>
						</th>
						<td>
							<input type="text" name="name"/>
						</td>
					</tr>
					
					<tr>
						<th>
							<label>Parent Department:</label>
						</th>
						<td>
							<select name="parent_id">
								<option value="0">...</option>
								<c:forEach items="${addDept}" var="dept">
									<option value="${dept.departmentId}">${dept.name}</option>
								</c:forEach>
							</select>
						</td>
					</tr>
					
					<tr>
						<td colspan="2" align="right">
							<button  type="button" class="cancelBtn">Cancel</button>
							<button type="submit">Save</button>
						</td>
					</tr>
				</table>
	
			</form>
		</fieldset>
	</div>
</c:if>

<c:if test="${not empty editDept}">
	<div id="editDeptEntity" >
		<h3>Edit Department</h3>
		
		<fieldset class="deptFields">
			
			<form name="editDept" id="editDept" action="depts" method="post">
				
				<input type="hidden" name="update" value="true" />
				<input type="hidden" name="editDeptID" id="editDeptID" value="${editDept.departmentId}" />
				
				<table>
					<tr>
						<th>
							<label>Department Name:</label>
						</th>
						<td>
							<input type="text" name="editName" id="editName" value="${editDept.name}" />
						</td>
					</tr>
					
					<tr>
						<th>
							<label>Parent Department:</label>
						</th>
						<td>
							<select name="editParent_id" id="editParent_id">
								<option value="0">...</option>
								<c:forEach items="${deptsList}" var="dept">
									<option value="${dept.departmentId}" <c:if test="${dept.departmentId eq dept.parentDepartment.name}">selected="selected"</c:if>>${dept.name}</option>
								</c:forEach>
							</select>
						</td>
					</tr>
					
					<tr>
						<td colspan="2" class="actionBtns">
							<button type="button" class="cancelBtn">Cancel</button>
							<button type="button" class="updateBtn">Update</button>
							<button type="button" class="deleteLink">Delete</button>
						</td>
					</tr>
				</table>
	
			</form>
		</fieldset>
	</div>
</c:if>
			
<form id="deleteDeptByID" action="depts" method="post">
	<input type="hidden" id="deleteDeptID" name="deleteDeptID" value="0" />
</form>
