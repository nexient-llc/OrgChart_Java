$(document).ready(function() {

	$('#addBtn').click(function() {
		$('#addBtn-container').toggle();
		$('#addEntity').toggle();
		
	});
	
	$("#cancelButton").click(function() {
		$("#addEntity").toggle();
		$("#addBtn-container").toggle();
	});
	
	$("#cancelEdit").click(function() {
		$("#editJobTitleContainer").toggle();
		$("#editName").val("");
		$("#editId").val("");
		$("#editName").focus("");
	});
	
});

function editJob(id, name) {
	$("#editJobTitleContainer").toggle();
	$("#editName").val(name);
	$("#editId").val(id);
	$("#editName").focus();
}