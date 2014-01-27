<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="sec"
           uri="http://www.springframework.org/security/tags" %>

<h3>Employees</h3>
<table id="t1">
    <tr><!-- <sec:authorize access="hasRole('ROLE_ADMIN')"> -->
        <!-- <th>Task</th></sec:authorize> -->
        <th>First Name</th>
        <th>Middle Initial</th>
        <th>Last Name</th>
        <th>Department</th>
        <th>Job Title</th>
    </tr>
    <c:forEach items="${emps}" var="emp">
        <tr>
            <!-- <sec:authorize access="hasRole('ROLE_ADMIN')">
				<td>delete</td>
			</sec:authorize> -->
            <td>${emp.firstName}</td>
            <td>${emp.middleInitial}</td>
            <td>${emp.lastName}</td>
            <td>${emp.department.name}</td>
            <td>${emp.jobTitle.name}</td>

        </tr>
    </c:forEach>
</table>

<div id="addBtn-container">
    <button type="button" id="addBtn" style="width: 45px;">Add</button>
</div>

<div id="addEntity" style="display:none">
    <fieldset>
        <legend>Add Employee</legend>
        <form name="newEmp" action="emps" method="post">
            <div>
                <labeL>First Name: </labeL><input type="text" name="firstName"/>
                <labeL>Middle Initial: </labeL><input type="text" name="middleInitial"/>
                <labeL>Last Name: </labeL><input type="text" name="lastName"/>

                <labeL>Skype Name: </labeL><input type="text" name="skypeName"/>
                <labeL>Employee Department :</label>
                <select name="department">
                    <option>...</option>
                    <c:forEach items="${depts}" var="dept">
                        <option value="${dept.id}">${dept.name}</option>
                    </c:forEach>
                </select>
                <button type="submit">Save</button>
            </div>
            <%--<div></div>--%>
        </form>
    </fieldset>
</div>
