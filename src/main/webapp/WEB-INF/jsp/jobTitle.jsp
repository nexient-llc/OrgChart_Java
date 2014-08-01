<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<h3>Job Titles</h3> 
<sec:authorize access="hasRole('ADMIN')">
<div id="addBtn-container">
		<button type="button" id="addBtn" style="width: 45px;">Add</button>	
</div>
<div id="addEntity" style="display:none">
	<fieldset>
		<legend>Add Job Title</legend>
		<form name="newTitle" action="title" method="post">
		<input type="hidden" id="titleId" name="id" />
		<div><labeL>*Description:</labeL><input id="job" type="text" name="name" required/>
			<input  id="activeBox" type="checkbox"  name="isActive"/> <label> Active </label> 
			<button type="submit">Save</button>
			<button id="cancel" type="reset" value="reset" >Cancel</button>
		</div>
		<div></div>
		</form>
	</fieldset>
	
</div>
</sec:authorize>
<table id="t1"> 
	
	<tr>
		<th> Description</th><sec:authorize access="hasRole('ADMIN')"><th>Edit</th></sec:authorize>
	<c:forEach items="${titles}" var="title">
		<tr>
			<td>${title.name}</td> 
			<td>
			<sec:authorize access="hasRole('ADMIN')">
			<button class="editButton" value="${title.id}" style="width: 60px;">Edit</button>
			</sec:authorize>
			</td> 
		 </tr>
	</c:forEach>
	</tr> 
</table>


