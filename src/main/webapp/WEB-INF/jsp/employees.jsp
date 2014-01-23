<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="sec"
        uri="http://www.springframework.org/security/tags"%>

<h3>Employee Page</h3>

<div id="addBtn-container">
	<button type="button" id="addBtn">Add</button>        
</div>

<div id="editBtn-container">
	<button type="button" id="editBtn">Edit</button>
</div>

<div id="cancelBtn-container" style="display:none">
	<button type="button" id="cancelBtn">Cancel</button>
</div>

<div id="addEntity" style="display:none;">
	<fieldset>
	    <legend>Add Employee</legend>
		<form name="addEmp" action="emps" method="post">
			<div>
				<label>First Name:</label><input type="text" name="firstName"/><br/>
				<label>Last Name:</label><input type="text" name="lastName"/><br/>
				<label>Middle Initial:</label><input type="text" name="middleInitial"/><br/>
				<label>Department:</label>
				<select name="departmentId">
					<option value="">None</option>
					<c:forEach items="${depts}" var="dept">
						<option value="${dept.id}">${dept.name}</option>
					</c:forEach>
				</select><br/>
				<label>Email:</label><input type="text" name="email"/><br/>
				<label>Skype Name:</label><input type="text" name="skypeName"/><br/>
				<label>Job Title:</label>
				<select name="jobTitleId">
					<option value="">None</option>
					<c:forEach items="${jobs}" var="job">
						<option value="${job.id}">${job.name}</option>
					</c:forEach>
				</select><br/>
				<button type="submit" id="saveEntity">Save</button>
				<button type="button" id="cancelEntity">Cancel</button>
			</div>
			<div></div>
		</form>
	</fieldset>
</div>

<div class="divContainer"> 
	<div style="overflow:hidden;">
		<!-- <sec:authorize access="hasRole('ROLE_ADMIN')"> -->
		<!-- <th>Task</th>
		</sec:authorize> --> 
		<div class="divHeader">First Name</div><!--
		--><div class="divHeader">Last Name</div><!-- 
		--><div class="divHeaderHidden" style="display:none">Middle Initial</div><!--
		--><div class="divHeader">Department</div><!--
	 	--><div class="divHeaderHidden" style="display:none">Email</div><!-- 
	 	--><div class="divHeaderHidden" style="display:none">Skype Name</div><!-- 
	 	--><div class="divHeader">Job Title</div><!-- 
	 	--><div class="divHeaderHidden" style="display:none">Edit</div>
	</div>
	<div style="overflow:hidden;">
		<c:forEach items="${emps}" var="emp">
			<div id="divRow${emp.id}"> 
				<!-- <sec:authorize access="hasRole('ROLE_ADMIN')">
					<td>delete</td>
				</sec:authorize> -->
				<div class="divColumn">${emp.firstName}</div> 
				<div class="divColumn">${emp.lastName}</div>
				<div class="divColumnHidden" style="display:none">${emp.middleInitial}</div>
				<div class="divColumn">${emp.department.name}</div>
				<div class="divColumnHidden" style="display:none">${emp.email}</div>
				<div class="divColumnHidden" style="display:none">${emp.skypeName}</div>
				<div class="divColumn">${emp.jobTitle.name}</div>
				<div class="divColumnHidden" style="display:none">
					<button type="button" class="editColumnBtn" value="${emp.id}">Edit</button>
				</div>
			</div>
			<div id="divEditRow${emp.id}" style="display:none"> 
				<!-- <sec:authorize access="hasRole('ROLE_ADMIN')">
					<td>delete</td>
				</sec:authorize> -->
				<div class="divColumn"><input id="editInputBox${emp.id}firstName" type="text" value="${emp.firstName}"/></div> 
				<div class="divColumn"><input id="editInputBox${emp.id}lastName" type="text" value="${emp.lastName}"/></div>
				<div class="divColumn"><input id="editInputBox${emp.id}middleInitial" type="text" value="${emp.middleInitial}"/></div>
				<div class="divColumn">
					<select id="editSelectBox${emp.id}departmentId">
						<option value="">None</option>
						<c:forEach items="${depts}" var="dept">
							<c:choose>
								<c:when test="${dept.id == emp.department.id}">
									<option selected value="${dept.id}">${dept.name}</option>
								</c:when>
								<c:otherwise>
									<option value="${dept.id}">${dept.name}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</select>
				</div>
				<div class="divColumn"><input id="editInputBox${emp.id}email" type="text" value="${emp.email}"/></div>
				<div class="divColumn"><input id="editInputBox${emp.id}skypeName" type="text" value="${emp.skypeName}"/></div>			
				<div class="divColumn">
					<select id="editSelectBox${emp.id}jobTitleId" name="jobTitleId">
						<option value="">None</option>
						<c:forEach items="${jobs}" var="job">
							<c:choose>
								<c:when test="${job.id == emp.jobTitle.id}">
									<option selected value="${job.id}">${job.name}</option>
								</c:when>
								<c:otherwise>
									<option value="${job.id}">${job.name}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</select>
				</div>
				<div class="divColumn">
					<button type="button" class="submitColumnBtn" value="${emp.id}">Submit</button>
					<button type="button" class="removeColumnBtn" value="${emp.id}">Remove</button>
					<button type="button" class="cancelColumnBtn" value="${emp.id}">Cancel</button>
				</div>
			</div>
		</c:forEach> 
	</div>
</div>