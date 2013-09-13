<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<fieldset>
	<legend>${param.formType} Job Title</legend>
	<form:form name="${param.formType}Job" modelAttribute="JOB_TITLE" action="${param.action}" method="${param.method}">
		<div>
			<input type="hidden" name="id" id="${param.formType}Id" />
			<label>Job Title Name:</label>
			<form:input path="name" />
			<input type="submit" value="Save" />
		</div>
	</form:form>
</fieldset>