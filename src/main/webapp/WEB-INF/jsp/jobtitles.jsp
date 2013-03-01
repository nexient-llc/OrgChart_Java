<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<div class="pageTitle">
	<h2>Job Titles</h2>
</div>

<div id="jobSearch">
	<h3>Search</h3>
	
	<div>
		<table>
			<form name="jobByID" id="jobByID" action="jobs" method="get">
				<tr>
					<td class="jobLabel">ID:</td>
					<td><input type="text" id="jobTitleID" name="jobTitleID" class="jobInput" />
					<td><button type="button" id="ByIDButton">Search</button></td>
				</tr>
			</form>
			
			<form name="jobByName" id="jobByName" action="jobs" method="get">	
				<tr>
					<td class="jobLabel"><span>*</span> Title:</td>
					<td><input type="text" id="jobTitleName" name="jobTitleName" class="jobInput" />
					<td><button type="button" id="ByNameButton">Search</button></td>
				</tr>	
			</form>
			
			<tr>
				<td></td>
				<td>
					<div id="viewAll-container">
						<form name="jobsAll" action="jobs" method="get">
							<input type="hidden" id="viewAllJobs" name="viewAllJobs" value="true" />
							<button type="submit">View All</button>
						</form>
					</div>
					
					<div id="addBtn-container">
						<button type="button" id="addBtn">Add New</button>
					</div>
				</td>
				<td></td>
			</tr>
		</table>
		
		<br/>
		
		<p id="jobSubtext"><span>*</span> Job Titles may be searched by using a full or partial name.</p>
	</div>
</div>

<c:if test="${not empty msg}">
	<div id="errorMsg"  style="display:inline-block;"> 
		<h3>${msg}</h3>
	</div>
</c:if>

<c:if test="${not empty jobs}">
	<div id="jobDisplay">
	
		<h3>Job Title List</h3>
	
		<table id="t1" cellspacing="0px" cellpadding="4px"> 
			<c:forEach items="${jobs}" var="job" varStatus="loopStatus">
				<tr id="${job.jobTitleID}" style="background-color: ${loopStatus.index % 2 == 0 ? '#fff' : '#cfcece'}"> 
					<td>${fn:toLowerCase(job.desc)}</td>
					<td class="actionBtns">
						<button type="button" class="editLink">edit</button>
						<button type="button" class="deleteLink">delete</button>
					</td>
				</tr>
			</c:forEach> 
		</table>
	
	</div>
</c:if>

<c:if test="${not empty editJob}">
	<div id="editJobEntity">
	
		<h3>Edit Job Title</h3>	
		
		<fieldset class="jobFields">
			<form id="editJob" name="editJob" action="jobs" method="post">
			
				<input type="hidden" id="editJobID" name="editJobID" value="${editJob.jobTitleID}" />
				
				<table>
					<tr>
						<td>
							<labeL>Job Title:</labeL>
						</td>
						<td>
							<input type="text" id="editDesc" name="editDesc" value="${editJob.desc}" />
						</td>
					</tr>
					
					<tr>
						<td colspan="2" class="actionBtns">
							<button type="button" class="cancelBtn">Cancel</button>
							<button type="button" class="updateBtn">Update</button>
							<button type="button" class="deleteLink">Delete</button>
						</td>
					</tr>
				</table>
			
			<div>
				
			</div>
			<div></div>
			</form>
		</fieldset>
	</div>
</c:if>

<div id="addJobEntity" style="display:none">
	
	<h3>Add New Job Title</h3>	
	
	<fieldset class="jobFields">
		<form name="newJob" action="jobs" method="post">
		
			<input type="hidden" id="addNewJob" name="addNewJob" value="true" />
			
			<table>
				<tr>
					<td><labeL>Job Title:</labeL></td>
					<td><input type="text" name="desc"/></td>
				</tr>
				
				<tr>
					<td colspan="2" class="actionBtns">
						<button type="button" class="cancelBtn">Cancel</button>
						<button type="submit">Save</button>
					</td>
				</tr>
			</table>

		</form>
	</fieldset>
</div>

<form id="deleteJobByID" action="jobs" method="post">
	<input type="hidden" id="deleteJobID" name="deleteJobID" value="0" />
</form>