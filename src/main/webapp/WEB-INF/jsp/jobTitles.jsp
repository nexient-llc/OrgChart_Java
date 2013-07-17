<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>



<div id="addBtn-container">
		<button type="button" id="addBtn" style="width: 40px;  font-size:65%;">Add</button>	
		<button type="button" id="delBtn" style="width: 40px;  font-size:65%;"> Delete </button>
</div>

<h3>Job Titles</h3> 
<table id="t1"> 
	<tr>	
	<!-- <sec:authorize access="hasRole('ROLE_ADMIN')"> -->
		<!-- <th>Task</th></sec:authorize> --> 
		<td>  </td><th>Job Title Description </th>
	</tr> 
	<c:forEach items="${jobs}" var="job">
		<tr> 	
			<!-- <sec:authorize access="hasRole('ROLE_ADMIN')">
				<td>delete</td>
			</sec:authorize> -->
			<td>	
				<button type="button" class="editBtn" style="width: 25px; font-size:65%"> 
			edit </button>
			</td>	
			<td>${job.name}</td> 					
			</tr>		
	</c:forEach> 
</table>


<div id="addEntity" style="display:none">
	<fieldset>
		<legend>Add New Job Title</legend>
		<form name="newDept" action="addJob" method="post">
		<div><labeL>Job Title Description:</labeL><input  class= "input" type="text" name="name"/>			
			<button type="submit">Save</button>			
		</div>
		<div></div>
		</form>
		<button class="cancelButton"> Cancel </button>
	</fieldset>
</div>


<div id="updateEntity" style="display:none">
	<fieldset>
		<legend>Edit Job Title</legend>
		<form name="newJob" action="update_job" method="post">
		<div><labeL>Job Title Description:</labeL>
		<input class= "input" id="updateOldName" type="text"
		 name="oldName" style= "display: none;"/>
		
		<input class= "input" id="updateName" type="text" name="newName"/>			
			<button type="submit">Update</button>
		</div>
		<div></div>
		</form>
	<button type= "button" class="cancelButton"> cancel </button>
	</fieldset>
</div>



<div id="delEntity" style="display:none">
	<fieldset>
		<legend>Delete Job Title</legend>
		<form name="oldjob" action="remove_job" method="post">
		<div><labeL>Job Title Description:</labeL>

		<select name="name">
				<option value= "-1"></option>
				<c:forEach items="${jobs}" var="job">
					<option value="${job.name}">${job.name}</option>
				</c:forEach>
			</select>
	
			<button type="submit">Delete</button>
		</div>
		<div></div>
		</form>
		<button type= "button" class="cancelButton"> cancel </button>
	</fieldset>
</div>
