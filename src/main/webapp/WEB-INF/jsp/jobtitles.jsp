<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="sec"
        uri="http://www.springframework.org/security/tags"%>

<h3>Job Title Page</h3>

<div class="divContainer"> 
	<div style="overflow:hidden;">
		<!-- <sec:authorize access="hasRole('ROLE_ADMIN')"> -->
		<!-- <th>Task</th>
		</sec:authorize> --> 
		<div class="divHeader">Job Title</div>
	</div>
	<div style="overflow:hidden;">
		<c:forEach items="${jobs}" var="job">
			<div class="divRow"> 
				<!-- <sec:authorize access="hasRole('ROLE_ADMIN')">
					<td>delete</td>
				</sec:authorize> -->
				<div class="divColumn">${job.name}</div> 
			</div>
		</c:forEach>
	</div>
</div>