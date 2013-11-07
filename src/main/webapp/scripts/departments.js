function popupDialog() {
	$("#depEditDialog").dialog({
		modal: true,
		width: 400
	});
}

$(document).ready(function() {
	$(".depEditBtn").click(function(){
		
		/* Requests JSON representing an employee
		 * based on the passed in id.
		 */
		$.ajax({
			url: "departments/" + $(this).data("id") + "/json",
			type: "GET",
			dataType: "json"
		
		}).done(function(employee){
			$("#name").val(employee.name);
			$("#id").val(employee.id);
			$("#deleteId").val(employee.id);
		});
		
		popupDialog();
	});
});
