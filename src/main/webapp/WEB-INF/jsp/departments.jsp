<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<h3>Department Page</h3> 
<sec:authorize access="hasRole('admin')">
<div id="addBtn-container">
		<button type="button" id="addBtn" style="width: 45px;">Add</button>	
</div>
</sec:authorize>

<table id="t1"> 
	<tr><!--<sec:authorize access="hasRole('ADMIN')">
			<th>Task</th>
		</sec:authorize>-->
		<th>Dept Name</th> <th>Parent Dept</th> <th></th>
	</tr> 
	<c:forEach items="${depts}" var="dept">
		<tr> 
			<c:if test="${dept.isActive}">
				<td>${dept.name}</td> 
				<td>${dept.parentDepartment.name}</td>
				<sec:authorize access="hasRole('admin')">
				<td>
					<div class="editBtn-container">
				    	<button type="button" id=${dept.id} class="editBtn" onClick="editDepartment(${dept.id})" style="width: 45px;">Edit</button>	
	            	</div>
	            </td>
	            </sec:authorize>
            </c:if>
        </tr>
        <c:set var="deptSelectParentNameIndex" value="${deptSelectParentNameIndex + 1}"></c:set>
	</c:forEach> 
</table>

<div id="addEntity" style="display:none">
	<fieldset>
		<legend>Add Department</legend>
		<form name="newDept" action="depts" method="post">
		<div><labeL>Dept Name:</labeL><input type="text" name="name" required />
			<labeL>Parent Dept:</label>
			<select name="parentDepartment.id">
				<option></option>
				<c:forEach items="${depts}" var="dept">
					<option value="${dept.id}">${dept.name}</option>
				</c:forEach>
			</select>
			<button type="submit">Save</button>
			<button type="reset" onClick="toggleSave()">Cancel</button>
		</div>
		<div></div>
		</form>
	</fieldset>
</div>

<c:forEach items="${depts}" var="dept">
<div id="editEntity${dept.id}" style="display:none">
	<fieldset>
		<legend>Edit Department</legend>
		<form name="editDept" action="editdepts" method="post">
		<input type="text" name="id" value="${dept.id}" style="display:none"/>
		<div><labeL>Dept Name:</labeL><input type="text" name="name" required value="${dept.name}" />
			<labeL>Parent Dept:</label>
			<select name="parent_id" id="departmentSelect">
				<option></option>
				<c:forEach items="${depts}" var="dept2">
				<c:choose>
					<c:when test="${dept2.id == dept.parentDepartment.id}">
						<option value="${dept2.id}" selected>${dept2.name}</option>
					</c:when>
					<c:otherwise>
						<option value="${dept2.id}">${dept2.name}</option>
					</c:otherwise>
				</c:choose>
				</c:forEach>
			</select>
			<button type="submit">Save</button>
			<button type="reset" onClick="editDepartment(${dept.id})">Cancel</button>
		</div>
		<div></div>
		</form>
		<form action="removedepts" method="post">
      		<input type="text" name="id" value="${dept.id}" style="display:none"/>
      		<div class="editBtn-container">
      			<button type="submit">Remove</button>
      		</div>
      	</form>
	</fieldset>
</div>
</c:forEach>
