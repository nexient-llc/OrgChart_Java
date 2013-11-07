function popupDialog(div) {
	div.dialog({
		modal: true,
		width: 400
	});
}

$(document).ready(function() {
	
	$("#addBtn").click(function(){
		$("#name").val('');
		$("#id").val('');
		$("#deleteId").val('');
		selectByValue($("#parentDepartment"), '');
		$("#dangerZone").css('display','none');
		$("#editForm").attr("action", "departments/add");
		popupDialog($("#depEditDialog"));
	});
	
	$(".depEditBtn").click(function(){
		
		/* Requests JSON representing an employee
		 * based on the passed in id.
		 */
		$.ajax({
			url: "departments/" + $(this).data("id") + "/json",
			type: "GET",
			dataType: "json"
		
		}).done(function(department){
			$("#name").val(department.name);
			$("#id").val(department.id);
			$("#deleteId").val(department.id);
			$("#dangerZone").css('display','inline');
			$("#editForm").attr("action", "departments/edit");
			
			if(department.parentDepartment == undefined)
				selectByValue($("#parentDepartment"), '');
			else
				selectByValue($("#parentDepartment"), department.parentDepartment.id);
		});
		
		popupDialog($("#depEditDialog"));
	});
});
