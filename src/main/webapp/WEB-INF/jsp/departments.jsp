<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
        <table id="departmentTable" class="table1"> 
                <tr><!-- <sec:authorize access="hasRole('ROLE_ADMIN')"> -->
                        <!-- <th>Task</th></sec:authorize> --> 
                        <th>Department Name</th><th>Parent Department</th><th>Department Manager</th><th>Edit</th><th>Select</th><th>Delete</th>
                </tr> 
        </table>
        <div id="dialogWrapper">
	       	<div id="addDialog" style="display: hidden;">
	       		<fieldset>
	        	<form name="newDept" action="depts" method="post">
					<div><labeL>Dept Name:</labeL>
							<input type="text" name="name"/>
					        <labeL>Parent Dept:</label>
					        <select name="parent_id">
						        <option>...</option>
						        <c:forEach items="${depts}" var="dept">
						    	   	<option value="${dept.id}">${dept.name}</option>
								</c:forEach>
		  		            </select>
					        <button type="submit">Save</button><button type="submit">Delete</button>
					</div>
					<div></div>
				</form>
	            </fieldset>
	     	</div> 
     	</div>
        <div id="addBtn-container">
                        <button type="button" id="addBtn" style="padding: .25em 1.5em;">Add Department</button>	
        </div>
        <button id="clickme" type="submit">CLICK ME PLEASE</button>