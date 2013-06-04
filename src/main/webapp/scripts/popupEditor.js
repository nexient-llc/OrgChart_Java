
function initEditor(loadDeptId, loadDeptName, loadDeptParenId) {
	var actionURL = "<c:url value='/app/depts/" + loadDeptId + "'/>"
	document.getElementById("editEntity").action = actionURL;
	document.getElementById("editId").value = loadDeptId;
	document.getElementById("editName").value = loadDeptName;
	if(loadDeptParenId == -1) {
		document.getElementById("dotdotdot").selected = "selected";
	}
	else {
		var selectValue = "editParent" + loadDeptParenId;
		var selectOption = document.getElementById(selectValue);
		selectOption.selected = "selected";
	}
	
	var greyedOutDiv = document.getElementById("greyOut");
	greyedOutDiv.display = "inline-block";

}
