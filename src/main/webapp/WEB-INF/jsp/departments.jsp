<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<div id="deptDisplay">
	
	<h3>Current Departments</h3> 

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
				<td>
					<button id="edit" class="editLink">edit</button>
					<button id="delete" class="deleteLink">delete</button>
				</td>
			</tr>
		</c:forEach> 
	</table>
	
	<br/>
	
	<div id="addBtn-container">
		<button type="button" id="addBtn" style="width: 45px;">Add</button>	
	</div>
</div>



<div id="addDeptEntity">
	<h3>Add A New Department</h3>
	
	<fieldset id="deptFields">
		<legend>Add Department</legend>
		
		<form name="newDept" action="depts" method="post">
		
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
							<c:forEach items="${depts}" var="dept">
								<option value="${dept.departmentId}">${dept.name}</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				
				<tr>
					<td colspan="2" align="right">
					<button  type="button" id="cancelBtn">Cancel</button>
						<button type="submit">Save</button>
					</td>
				</tr>
			</table>

		</form>
	</fieldset>
</div>

<!-- Hidden forms -->
<form id="editDept" action="editDeptDisplay" method="post">
	<input type="hidden" id="" name="" />
</form>

<form id="deleteDeptByID" action="depts/delete" method="post">
	<input type="hidden" id="deleteDeptID" name="deleteDeptID" value="0" />
</form>
