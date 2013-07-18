<%--<!DOCTYPE html>--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>



<h3>Departments</h3> 

<div id="addBtn-container">
	<button type="button" id="addBtn" style="width: 40px; font-size:65%;">Add</button>	
		<button type="button" id="delBtn" style="width: 40px; font-size:65%;"> Delete </button>
</div>

<br/>

<table id="t1"> 
	<tr>	
	<!-- <sec:authorize access="hasRole('ROLE_ADMIN')"> -->
		<!-- <th>Task</th></sec:authorize> --> 
		<td>  </td><th>Dept Name</th> <th>Parent Dept</th>
	</tr> 
	<c:forEach items="${depts}" var="dept">
		<tr> 	
			<!-- <sec:authorize access="hasRole('ROLE_ADMIN')">
				<td>delete</td>
			</sec:authorize> -->
			<td>	
				<button type="button" class="editBtn" style="width: 25px; font-size:65%"  > 
			edit </button>
			</td>	
			<td>${dept.name}</td> 
			<td>${dept.parentDepartment.name}
						
			</td>
			
			</tr>
			
			
	</c:forEach> 
</table>



<div id="updateEntity" style="display:none">
	<fieldset>
		<legend>Update Department</legend>		
		<form name="editDept" action="update_dept" method="post">		
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
		<legend>Add Department</legend>
		<form name="newDept" action="depts" method="post">
		<div><labeL>Dept Name:</labeL><input type="text" name="name"/>
			<labeL>Parent Dept:</label>
			<select name="parentDepartment.id">		
				<option value="-1">(none)</option>
				<c:forEach items="${depts}" var="dept">
					<option value="${dept.id}">${dept.name}</option>
				</c:forEach>
			</select>
			<button type="submit">Save</button>
		</div>
		<div></div>
		</form>
	</fieldset>
</div>


<div id="delEntity" style="display:none">
	<fieldset>
		<legend>Delete Department</legend>
		<form name="oldDept" action="remove_depts" method="post">
		<div><labeL>Dept Name:</labeL>
		<%-- <input type="text" name="name"/>--%>
	<%-- 		<labeL>Parent Dept:</label>
	--%>
		<select name="id">
				<option value= "-1"></option>
				<c:forEach items="${depts}" var="dept">
					<option value="${dept.id}">${dept.name}</option>
				</c:forEach>
			</select>
	
			<button type="submit">Delete</button>
		</div>
		<div></div>
		</form>
	</fieldset>
</div>
