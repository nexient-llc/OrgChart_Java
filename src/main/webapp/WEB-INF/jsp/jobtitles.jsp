
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%> 
<%@ taglib prefix="sec"
 uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>

<h3>Job Titles</h3> 

<div class="column">
	<table id="t1"> 
	<c:forEach items="${jobs}" var="job">
		<tr class="click_row" id="${job.id}">
 			<!-- <sec:authorize access="hasRole('ROLE_ADMIN')">
				<td>delete</td>
			</sec:authorize> -->
			<td class="edit_icon" width="16 px" ></td>
			<td class="jobname" >${job.name}</td> 
			</tr>
	</c:forEach> 
</table>
</div>
