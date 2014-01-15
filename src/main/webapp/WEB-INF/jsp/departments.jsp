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
		<legend>Add Department</legend>
		<form name="newDept" id="newDept" action="depts" method="post">
			<div>
				<labeL>Dept Name:</labeL>
				<input type="text" name="name" id="name"/>
				<labeL>Parent Dept:</label>
				<select name="parentDepartmentId" id="parent_department_id">
					<option>...</option>
					<!-- The dept var here is the variable to access the elements of depts in the for each loop. -->
					<c:forEach items="${depts}" var="dept">
						<option value="${dept.id}">${dept.name}</option>
					</c:forEach>
				</select>
				<button type="submit">Save</button>
				<button type="button" id="rstBtn" onClick="resetForm();">Clear</button>
			</div>
		</form>

	</fieldset>
</div>

<table id="t1"> 
	<tr>
		<th>Dept Name</th> <th>Parent Dept</th> <!-- Remove this --><th>Actions</th><!-- Remove this -->
		<!-- ***** Fix this! remove the <th> you created with the value actions and replace it with this! ***** -->
		<!-- <sec:authorize access="hasRole('ROLE_ADMIN')"> -->
		<!-- <th>Task</th></sec:authorize> --> 
	</tr> 
	<c:forEach items="${depts}" var="dept">
		
		<!-- <tr style="background-color:#52bcec;"> -->
			<tr>
			<td> ${dept.name} </td> 
			<td> ${dept.parentDepartment.name} </td>
			<td>
				<div id="button-container${dept.id}">
					<button type="button" id="editButton" onClick='editDepartment(${dept.id})'>Edit</button>
					<button type="button" id="removeButton" onClick='removeDepartment(${dept.id})'>Remove</button>
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
								<div>
									<labeL>Dept Name:</labeL>
									<!-- <input type="text" name="editName${dept.id}" id="editName${dept.id}" value="${dept.name}"/> -->
									<input type="hidden" name="id" value="${dept.id }" />
									<input type="text" name="name" id="editName${dept.id}" value="${dept.name}"/>
									<labeL>Parent Dept:</label>
									<select name="parentDepartmentId" id="parentDepartmentId">
										<option value="">...</option>
										<!-- The dept var here is the variable to access the elements of depts in the for each loop. -->
										<c:forEach items="${depts}" var="listDept">
											<c:choose>
												<c:when test="${listDept.id == dept.parentDepartment.id}">
													<option value="${listDept.id}" selected>${listDept.name} - ${listDept.id}</option>
												</c:when>
												<c:otherwise>
													<option value="${listDept.id}">${listDept.name} - ${listDept.id}</option>
												</c:otherwise>
											</c:choose>
										</c:forEach>
									</select>
									<button type="submit">Save</button>
									<button type="button" id="rstBtn" onClick="resetForm();">Clear</button>
								</div>
							</form>
					</fieldset>
				</div>
				<!-- ********************* ***************** ***************** ********************* -->
				<!-- ********************* This is the end of the edit section ********************* -->
				<!-- ********************* ***************** ***************** ********************* -->
			</td>
		</tr>
	</c:forEach> 
</table>