$(document).ready(function() {
	$('#addBtn-container').css('width', $('#t1').width());

	$('#addBtn').button().click(function() {
		$('#addBtn-container').fadeToggle("fast", "linear", function() {
			$('#addEntity').dialog({
				minWidth : 322
			});
		});
	});

	$('#addEntity').bind('dialogclose', function(event) {
		$('#addBtn-container').fadeToggle("fast", "linear");
	});

	$('#cancelBtn').button().click(function() {
		$('#addEntity').dialog("close");
	});

	$('#submitBtn').button();

	$('.editBtn').button().click(function() {
		 $('#editEntity').dialog({minWidth: 322});
	});
	
	$('.delEmp').button().click(function(){
		$.ajax({
			url:	"deleteEmp",
			type:	"POST",
			data:	{action: "delete", id: $(this).val() },
			success: function(){
				window.location = "emps";
			}
		});
	});



});
