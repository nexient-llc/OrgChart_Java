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
	$('#submitEditBtn').button();

	$('#cancelEditBtn').button().click(function(){
		$('#editEntity').dialog("close");
	});
	
	$('.editEmp').button().click(function(){
		$.ajax({
			url:	"emps/"+ $(this).val(),
			type:	"GET",
			success: function(data){
				var form = $.parseJSON(data);
				$('#empId').val(form.id);
				$('#firstName').val(form.firstName);
				$('#lastName').val(form.lastName);
				$('#email').val(form.email);
				$('#skypeName').val(form.skypeName);
				$('#department').val(form.department.departmentId);
				$('#jobTitle').val(form.jobTitle.id);
				if (form.isManager) {
					$('#isManager').attr('defaultChecked', true);
				}
				
				$('#editEntity').dialog({minWidth: 322});
			}
		});
	});



});
