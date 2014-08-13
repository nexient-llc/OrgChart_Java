<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%> 
<%@ taglib prefix="sec"
 uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<h3>Departments</h3>
<sec:authorize access="hasRole('ADMIN')">
<div class="addClass" id="addEntity" style="display: none">
	<fieldset>
		<legend>Add Department</legend>
		<form:form name="newDept" action="deptAdd" method="post">
			<div>
			    <input type="hidden" name="id" id="departId" >
				<labeL>*Dept Name:</labeL><input type="text" id="department" name="name" required/>
			     <labeL>Parent Dept:</label> 
			     <select name="parentDepartment.id" id="parentId">
					<option>...</option>
					<c:forEach items="${depts}" var="dept">
						<option value="${dept.id}">${dept.name}</option>
					</c:forEach>
				</select>
				<button type="submit">Save</button>
				<button id="addCancelBtn" type="reset" value="reset" >Cancel</button>
			</div>
			<div></div>
		</form:form>
	</fieldset>
</div>
</sec:authorize>
<table id="t1" >
	<tr>
		<th align="left" > Dept Name</th>
		<th align="left" > Parent Dept</th>
		<td>
		<sec:authorize access="hasRole('ADMIN')">
		<button type="button" id="addBtn" style="width: 45px;">Add</button>
		</sec:authorize>
		</td>
	</tr>
	<c:forEach items="${depts}" var="dept">
		<tr >
			<td style="width:135px" align="left" >${dept.name}</td> 
			<td style="width:125px" align="left" > ${dept.parentDepartment.name}</td>
			<td>
			<sec:authorize access="hasRole('ADMIN')">
			<button  class="editButton" value="${dept.id}" id="editEntity" style="width: 60px;">Edit</button>
			<button class="removeButton" value="${dept.id}" id="removeEntity" style="width: 70px;">Delete</button>
			</sec:authorize>
			</td>
		</tr>
	</c:forEach>
</table>
<script type="text/javascript"> 

</script>