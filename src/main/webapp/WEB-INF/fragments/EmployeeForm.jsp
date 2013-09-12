<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<fieldset>
    <legend>${param.formType} Employee</legend>
    <form:form id="${param.formType}Emp" action="emp${param.formType}" method="post">
      <div>
        <label>Employee's First Name:</label><input type="text" name="first_name"/>
        <label>Employee's Middle Initial:</label><input type="text" name="middle_initial"/>
        <label>Employee's Last Name:</label><input type="text" name="last_name"/>
        <label>Employee's Skype:</label><input type="text" name="skype_name"/>
        <label>Employee's Email:</label><input type="email" name="email"/>
        <label>Employee's Job Title:</label>
        <input id="id" type="hidden" name="id" />
        <input id="${param.formType}DepartmentId" type="hidden" name="department.id" />
		<input id="${param.formType}JobTitleId" type="hidden" name="jobTitle.id" />
        <select id="job_id_${param.formType}" name="jobTitle">
          <option>...</option>
          <c:forEach items="${jobs}" var="job">
            <option value="${job.id}">${job.name}</option>
          </c:forEach>
        </select>
        <label>Employee's Department:</label>
        <select id="department_id_${param.formType}" name="department">
          <option>...</option>
          <c:forEach items="${depts}" var="dept">
            <option value="${dept.id}">${dept.name}</option>
          </c:forEach>
        </select>
        <button type="submit">Save</button>
      </div>
    </form:form>
  </fieldset>