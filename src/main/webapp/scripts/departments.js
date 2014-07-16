$(document).ready(function() {

	$('#addBtn').click(function() {
		$('#addBtn-container').toggle();
		$('#addEntity').toggle();
	});
	
	$('#cancelBtn').click(function() {
		$('#addBtn-container').toggle();
		$('#addEntity').toggle();
	});
	
	$("#cancelEdit").click(function() {
		$("#editEntity").toggle();
		$("#editParentDept").val("");
		$("#editDeptName").val("");
		$("#editDepartmentId").val("");
	});
	
});

function editDepartment(id, name, parentDept) {
	$("#editEntity").toggle();
	$("#editDepartmentId").val(id);
	$("#editDeptName").val(name);
	$("#editParentDept").val(parentDept);
	$("#editDeptName").focus();
}

function removeDepartment(id) {
	if(confirm("You sure you want to delete the department?") == true) {
		$.ajax({
			type: "POST",
			url: "removeDepartment/" + id,
			dataType: "text",
			success: function(data) {
				$("#deptRow" + id).remove();
			}
		});
	}
}