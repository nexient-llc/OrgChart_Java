<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="sec"
        uri="http://www.springframework.org/security/tags"%>

<h3>Department Page</h3>

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
	    <legend>Add Department</legend>
	    <label>Dept Name (*)</label><input id="addInputBox"/><img id=funAddInputBox><br/>
		<label>Parent Dept</label>
		<select id="addSelectBox">
			<option value="">None</option>
			<c:forEach items="${depts}" var="dept">
				<option value="${dept.id}">${dept.name}</option>
			</c:forEach>
		</select><br/>
		<div style="margin-top:0.5em; margin-bottom:0.5em; font-size:10px">
			Required Fields indicated with a (*)
		</div>
		<div>
			<button type="button" id="saveEntity" disabled>Save</button>
			<button type="button" id="cancelEntity">Cancel</button>
		</div>
	</fieldset>
</div>

<div class="divContainer"> 
	<div style="overflow:hidden;">
		<!-- <sec:authorize access="hasRole('ROLE_ADMIN')"> -->
		<!-- <th>Task</th>
		</sec:authorize> --> 
		<div class="divHeader">Dept Name</div><!--
		--><div class="divHeader">Parent Dept</div><!-- 
		--><div class="divHeaderHidden" style="display:none">Edit</div>
	</div>
	<div style="overflow:hidden;">
		<c:forEach items="${depts}" var="dept">
			<div id="divRow${dept.id}"> 
				<!-- <sec:authorize access="hasRole('ROLE_ADMIN')">
					<td>delete</td>
				</sec:authorize> -->
				<div class="divColumn">${dept.name}</div> 
				<div class="divColumn">${dept.parentDepartment.name}</div>
				<div class="divColumnHidden" style="display:none">
					<button type="button" class="editColumnBtn" value="${dept.id}">Edit</button>
				</div>
			</div>
			<div id="divEditRow${dept.id}" style="display:none">
				<div class="divColumn"><input id="editInputBox${dept.id}" value="${dept.name}"/><img class="funEditInputBox"></div>
				<div class="divColumn">
					<select id="editSelectBox${dept.id}">
						<option value="">None</option>
						<c:forEach items="${depts}" var="pdept">	
							<c:if test="${dept.id != pdept.id}">
								<c:choose>
									<c:when test="${dept.parentDepartment.id == pdept.id}">
										<option selected value="${pdept.id}">${pdept.name}</option>
									</c:when>
									<c:otherwise>
										<option value="${pdept.id}">${pdept.name}</option>
									</c:otherwise>
								</c:choose>
							</c:if>		
						</c:forEach>
					</select>
				</div>
				<div class="divColumn">
					<button type="button" class="submitColumnBtn" value="${dept.id}">Submit</button>
					<button type="button" class="removeColumnBtn" value="${dept.id}">Remove</button>
					<button type="button" class="cancelColumnBtn" value="${dept.id}">Cancel</button>
				</div>
			</div>
		</c:forEach> 
	</div>
</div>
