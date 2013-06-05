$(document).ready(function() {
	$('#addDeptBtn-container').css('width', $('#t1').width());

	$('#addDeptBtn').button().click(function() {
		
		if ($('#newDeptForm').hasClass("editDept")) {
			$('#newDeptForm').removeClass("editDept");
			$('#newDeptForm').addClass("addDept");
			$("#putDeptMethod").remove();
			
			// Change title to Add Department and clear all the fields
			$("#deptFormLegend").text("Add Department");
			$("#departmentId").remove();
			$('#name').val("");
			$('#parentDepartment\\.departmentId').val("");
		}
		
		$('#addDeptBtn-container').fadeToggle("fast", "linear", function() {
			$('#addDeptEntity').dialog({
				minWidth : 381
			});
		});
		
		validateDeptForm();
	});

	$('#addDeptEntity').bind('dialogclose', function(event) {
		$('#addDeptBtn-container').fadeToggle("fast", "linear");
	});

	$('#cancelDeptBtn').button().click(function() {
		$('#addDeptEntity').dialog("close");
		$('#name').val("");
		$('#parentDepartment\\.departmentId').val("");
	});

	$('#submitBtn').button();

	$('.editDept').button().click(function() {
		// change Add Department form to Edit Department
		if ($('#newDeptForm').hasClass("addDept")) {
			$('#newDeptForm').removeClass("addDept");
			$('#newDeptForm').addClass("editDept");
			$('#newDeptForm').prepend("<input type='hidden' name='_method' value='put' id='putDeptMethod'/>");
			
			// Change title of form to Edit Department and add hidden input
			// field which has the id of the dept
			$("#deptFormLegend").text("Edit Department");
			$("#deptFormInputs").prepend("<input type='hidden' name='departmentId' id='departmentId' />");
		}

		
		// prepopulate the form with the employee info from an ajax call
		$.ajax({
			url : "depts/" + $(this).val(),
			type : "GET"
		}).done(function(data){
			var form = $.parseJSON(data);
			$('#departmentId').val(form.departmentId);
			$('#name').val(form.name);
			if (form.parentDepartment) {
				$('#parentDepartment\\.departmentId').val(form.parentDepartment.departmentId);
			} else {
				$('#parentDepartment\\.departmentId').val("");
			}
			
			$('#addDeptBtn-container').fadeToggle("fast", "linear");
			
			$('#addDeptEntity').dialog({
				minWidth : 381
			});
			validateDeptForm();
		});
	});
	
	validateDeptForm = function() {
		$('#newDeptForm').validate({
			rules: {
				name: {
					required: true,
				}
			}
		});
	}
	
});
