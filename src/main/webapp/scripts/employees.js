$(document).ready(function() {
	
	
	
	$("#cancelButton").click(function() {
		$("#addEntity").toggle();
		$("#addBtn-container").toggle();
	});
	
	$("#toggleFilter").click(function() {
		$("#filterContainer").toggle();
	});
	
	$("#cancelFilter").click(function() {
		$("#filterContainer").toggle();
	});
	
	
	$("#newEmployeeForm").submit(function() {
		if($("#addDepartmentSelect").val() == "") {
			alert("Please enter a department");
			return false;
		}
		if($("#addJobTitleSelect").val() == "") {
			alert("Please enter a job title");
			return false;
		}
	});

	$('#addBtn').click(function() {
		$('#addBtn-container').toggle();
		$('#addEntity').toggle();
		
	});
	
	
	
	$("input#autocompleteText").autocomplete({
		minLength: 3,
		source: function(request, response) {
			$.ajax({
				type: "GET",
				url: "suggestions/" + $("#autocompleteText").val(),
				dataType: "text",
				success: function(data) {
					$("#autocompleteText").removeClass("ui-autocomplete-loading");
					var suggestions = data.split(",");
					suggestions.pop();
					response(suggestions);
					
				}
			});
		}
	});
	
});


function openEditForm(id) {
	$("#container-" + id).toggle();
}

function removeEmployee(id) {
	if(confirm("You sure you want to delete the employee?") == true) {
		$.ajax({
			type: "POST",
			url: "removeEmployee/" + id,
			dataType: "text",
			success: function(data) {
				$("#empRow" + id).remove();
			}
		});
	}
	
	
}