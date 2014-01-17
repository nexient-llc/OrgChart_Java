<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<h3>Departments Page</h3>

<div id="addBtn-container">
		<button type="button" id="addBtn" style="width: 45px;">Add</button>	
</div>

<div id="addEntity" style="display:none">
	<fieldset>
		<legend>Add New Department</legend>
		<form name="newDept" id="newDept" action="depts" method="post">
			<div>
				<labeL>Dept Name  <span style="color:red">*</span>:</labeL>
				<input type="text" name="name" id="name"/>
				<labeL>Parent Dept  <span style="color:red">*</span>:</label>
				<select name="parentDepartmentId" id="parent_department_id">
					<option value="">...</option>
					<!-- The dept var here is the variable to access the elements of depts in the for each loop. -->
					<c:forEach items="${depts}" var="dept">
						<option value="${dept.id}">${dept.name}</option>
					</c:forEach>
				</select>
				<button type="submit">Save</button>
				<button type="button" id="rstBtn" onClick="resetForm();">Clear</button>
			</div>
		</form>
		Required Fields indicated with a <span style="color:red">*</span>
	</fieldset>
</div>

<table id="t1"> 
	<tr>
		<th>Dept Name</th> <th>Parent Dept</th> <!-- Remove this --><th id="taskHead">Task</th><!-- Remove this -->
		<!-- ***** Fix this! remove the <th> you created with the value actions and replace it with this! ***** -->
		<!-- <sec:authorize access="hasRole('ROLE_ADMIN')"> -->
		<!-- <th id="taskHead">Task</th></sec:authorize> --> 
	</tr> 
	<c:forEach items="${depts}" var="dept">
		
		<!-- <tr style="background-color:#52bcec;"> -->
			<tr>
			<td> ${dept.name} </td> 
			<td> ${dept.parentDepartment.name} </td>
			<td>
				<div id="button-container${dept.id}">
					<button type="button" id="editButton" onClick='editDepartment(${dept.id})'>Edit</button>
					
					<!-- <sec:authorize access="hasRole('ROLE_ADMIN')">
						<td>delete</td>
					</sec:authorize> -->
				</div>
			</td>
			<td>
			<!-- ********************* ******************** ******************** ********************* -->
			<!-- ********************* This is the beginning of the edit section ********************* -->
			<!-- ********************* ******************** ******************** ********************* -->
				<div id="editEntity${dept.id}" style="display:none">
					<fieldset>
						<legend>Edit ${dept.name} Department</legend>
							<form name="editDept${dept.id}" id="editDept${dept.id}" action="depts" method="post">
								<input type="hidden" name="_method" value="PUT">
								<div>
									<labeL>Dept Name <span style="color:red">*</span>:</labeL>
									<!-- <input type="text" name="editName${dept.id}" id="editName${dept.id}" value="${dept.name}"/> -->
									<input type="hidden" name="id" value="${dept.id }" />
									<input type="text" name="name" id="editName${dept.id}" value="${dept.name}"/>
									<labeL>Parent Dept <span style="color:red">*</span>:</label>
									<select name="parentDepartmentId" id="parentDepartmentId">
										<option value="">...</option>
										<!-- The dept var here is the variable to access the elements of depts in the for each loop. -->
										<c:forEach items="${depts}" var="department">
											<c:choose>
												<c:when test="${department.id == dept.parentDepartment.id}">
													<option value="${department.id}" selected>${department.name}</option>
												</c:when>
												<c:when test="${dept.id == department.id}"></c:when>
												<c:otherwise>
													<option value="${department.id}">${department.name}</option>
												</c:otherwise>
											</c:choose>
										</c:forEach>
									</select>
									<button type="submit" name="buttonClicked" value="save">Save</button>
									<button type="button" onClick="closeEdit(${dept.id});">Clear</button>
									<button type="submit" name="buttonClicked" value="remove">Remove</button>
									
								</div>
							</form>
							Required Fields indicated with a <span style="color:red">*</span>
					</fieldset>
				</div>
				<!-- ********************* ***************** ***************** ********************* -->
				<!-- ********************* This is the end of the edit section ********************* -->
				<!-- ********************* ***************** ***************** ********************* -->
			</td>
		</tr>
	</c:forEach> 
</table>