$(document).ready(function() {
	$(".empEditBtn").click(function() {
		
		$.ajax({
			url: "emps/" + $(this).data("id"),
			type: "GET",
			dataType: "json"
		
		}).done(function(data){
			var formData = $.parseJSON(data);
			console.log(formData.lastName);
		});
		
		$("#empEditDialog").dialog();
	});
});