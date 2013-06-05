$(document).ready(function() {
	
	$('#addJobsBtn-container').css('width', $('#t1').width());

	$('#addJobBtn').button().click(function() {
		
		if ($('#newJobForm').hasClass("editJob")) {
			$('#newJobForm').removeClass("editJob");
			$('#newJobForm').addClass("addJob");
			$("#putJobsMethod").remove();
			
			// Change title to Add Job Title and clear all the fields
			$("#jobsFormLegend").text("Add Job Title");
			$("#id").remove();
			$('#name').val("");
		}
		
		$('#addJobsBtn-container').fadeToggle("fast", "linear", function() {
			$('#addJobsEntity').dialog({
				minWidth : 381
			});
		});
		
		validateJobsForm();
	});

	$('#addJobsEntity').bind('dialogclose', function(event) {
		$('#addJobsBtn-container').fadeToggle("fast", "linear");
	});

	$('#cancelJobsBtn').button().click(function() {
		$('#addJobsEntity').dialog("close");
		$('#name').val("");
	});
	
	
	$('.editJob').button().click(function() {
		
		// change Add Job form to Edit Job
		if ($('#newJobForm').hasClass("addJob")) {
			$('#newJobForm').removeClass("addJob");
			$('#newJobForm').addClass("editJob");
			$('#newJobForm').prepend("<input type='hidden' name='_method' value='put' id='putJobsMethod'/>");
			
			// Change title of form to Add Job TItle and add hidden input
			// field which has the id of the job
			$("#jobsFormLegend").text("Edit Job Title");
			$("#jobsFormInputs").prepend("<input type='hidden' name='id' id='id' />");
		}
		
		$.ajax({
			url : "jobs/" + $(this).val(),
			type : "GET"
		}).done(function(data) {
			var form = $.parseJSON(data);
			$('#id').val(form.id);
			$('#name').val(form.name);
			
			$('#addJobsBtn-container').fadeToggle("fast", "linear");
			
			$('#addJobsEntity').dialog({
				minWidth : 381
			});
			
			validateJobsForm();
		});
	});

	validateJobsForm = function() {
		$('#newJobForm').validate({
			rules : {
				name : "required"
			}
		});
	}

});
