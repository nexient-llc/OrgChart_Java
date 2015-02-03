<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
 <link rel="stylesheet" href="//code.jquery.com/ui/1.11.2/themes/smoothness/jquery-ui.css">
  <script src="//code.jquery.com/jquery-1.10.2.js"></script>
  <script src="//code.jquery.com/ui/1.11.2/jquery-ui.js"></script>
  
  <style>
	.ui-dialog-titlebar-close {
	  visibility: hidden;
	}
  </style>
  
<h3>Departments</h3> 
<sec:authorize access="hasRole('ROLE_ADMIN')">

		<c:url var="logoutUrl" value="j_spring_security_logout"/>

		<form action="${logoutUrl}" method="post">
		  	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		</form>

		<script>
			function formSubmit() {
				document.getElementById("logoutForm").submit();
			}
		</script>
		
		<h2>
			<a href="javascript:formSubmit()"> Logout</a>
		</h2>
		
		</sec:authorize>

<sec:authorize access="hasRole('ROLE_ADMIN')">
<div id="addBtn-container">
		<button type="button" id="addBtn" style="width: 45px;">Add</button>	
</div><br>
</sec:authorize> 
<table id="t1"> 
	<tr>
		<th>Dept Name</th> <th>Parent Dept</th><sec:authorize access="hasRole('ROLE_ADMIN')"><th>Edit</th></sec:authorize>
	</tr> 
		<c:forEach items="${depts}" var="dept">
		<c:choose>
				<c:when test="${dept.isActive}">
					<tr>
						<!-- <sec:authorize access="hasRole('ROLE_ADMIN')">
							<td>delete</td>
						</sec:authorize> -->
						<td>${dept.name}</td>
						<td>${dept.parentDepartment.name}</td>
						<sec:authorize access="hasRole('ROLE_ADMIN')"><td><button class="editButton" value="${dept.id}">Edit</button></td></sec:authorize> 
					</tr>
				</c:when>
			</c:choose>
		</c:forEach>
</table>

<br>
<div id="dialog-form-make" title="Add New department">
	<fieldset>
		<form name="newDept" action="depts" method="post">
		<br>
		<div><labeL>* Dept Name:</labeL>
			<input type="text" name="name" required min=1/><br><br>
			<labeL>* Parent Dept:</label>
				<select name="parent_id">
					<c:forEach items="${depts}" var="dept">
						<option value="${dept.id}">${dept.name}</option>
				</c:forEach>
			</select><br>
			<p>Required Fields indicated with a *</p>
			<button type="submit">Save</button>
			<button type="reset" name="resetMakeBtn" id="resetMakeBtn">Cancel</button>
				</div>
			<div></div>
			</form>
		</fieldset>
</div>

<div id="dialog-form-edit" title="Edit Department">
		<fieldset>
			<form name="editDept" action="depts" method="post">
			<br>
				<div>
					<input type="hidden" id="departmentId" name="id"/>
						<labeL>* Department Name:</labeL> 
							<input type="text" name="name" id="departmentName" required /> <br>
						<labeL>* Parent Department:</label> 
							<select name="parentDepartment.id" id="parentDepartment">
								<option></option>
								<c:forEach items="${depts}" var="dept">
									<c:choose>
										<c:when test="${dept.isActive}">												
												<option value="${dept.id}">${dept.name}</option>
										</c:when>
									</c:choose>
								</c:forEach>
							</select><br><p></p>
							<p>Required Fields indicated with a *</p>
						<button type="submit">Save</button>
						<button type="submit" id="isActive" name="isActive" value="false">Remove</button>
						<button type="reset" name="resetEditBtn" id="resetEditBtn">Cancel</button>
					</div>
				<div></div>
			</form>
		</fieldset>
</div>


