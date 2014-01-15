<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<h3>Job Titles</h3> 
<table id="t1"> 
	<tr><!-- <sec:authorize access="hasRole('ROLE_ADMIN')"> -->
		<!-- <th>Task</th></sec:authorize> --> 
		<th>Job Name</th> <!--  <th>Parent Dept</th> -->
	</tr> 
	<c:forEach items="${jobs}" var="jobs">
		<tr> 
			<!-- <sec:authorize access="hasRole('ROLE_ADMIN')">
				<td>delete</td>
			</sec:authorize> -->
			<td>${jobs.name}</td>  
		</tr>
	</c:forEach> 
</table>

<div id="addBtn-container">
		<button type="button" id="addBtn" style="width: 55px;">Add</button>	
</div>



<div id="addEntity" style="display:none">
	<fieldset>
		<legend>Add Job Title</legend>
		<form:form modelAttribute="job" action="jobs" method="post">
                 <table>
                 <tr>
                 <td>Job Title :</td>
                 <td><form:input path="name" /></td>
                 </tr>
                 </table>
                 <input type=submit />
                </form:form>
	</fieldset>
</div>
<div id="deleteBtn-container">
		<button type="button" id="deleteBtn" style="width: 55px;">Delete</button>	
</div>



<div id="deleteEntity" style="display:none">
	<fieldset>
		<legend>Add Job Title</legend>
		<form:form modelAttribute="job" action="jobs" method="delete">
                 <table>
                 <tr>
                 <td>Job Title :</td>
                 <td><form:input path="name" /></td>
                 </tr>
                 </table>
                 <input type=submit />
                </form:form>
	</fieldset>
</div>


