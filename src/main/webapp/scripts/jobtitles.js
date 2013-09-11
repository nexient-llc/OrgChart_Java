$(document).ready(function() {
	$('#addBtn-container').css('width', $('#t1').width());
	
	//Open Add Job Title box
	$('#addBtn').click(function() {
		$('#addBtn-container').fadeOut("fast", "linear", function() {
			$('#addEntity').fadeIn("fast", "linear");
		});
		$('.editJobTitle').fadeOut("fast", "linear");
	});
	
	//Close Add Job Title box
	$('#cancelAdd').click(function() {
		$('#addEntity').fadeOut("fast", "linear", function() {
			$('#addBtn-container').fadeIn("fast", "linear");
			$('.editJobTitle').fadeIn("fast, linear");
		});	
	});
	
	//Open Edit Job Title box
	$('.editJobTitle').click(function() {
		//Set edit form values to values of selected job title
		$('#editEntity input#name').val($(this).closest('tr.jobRow').data('jobName'));
		$('#editEntity input#id').val($(this).closest('tr.jobRow').data('jobId'));

		$('.editJobTitle').fadeOut("fast", "linear");
		$('#addBtn-container').fadeOut("fast", "linear", function() {
			$('#editEntity').fadeIn("fast", "linear");
		});
	});
	
	//Close Edit Job Title box
	$('#cancelEdit').click(function() {
		$('#editEntity').fadeOut("fast", "linear", function() {
			$('#addBtn-container').fadeIn("fast", "linear");
			$('.editJobTitle').fadeIn("fast, linear");
		});
	});
	
	//Validate form before submission
	$('form').submit(function() {
		//Check for existing Job Title with chosen name
		var allJobs = new Array();
		var submittedName = $(this).find('input#name').val();
		$('#t1').find('tr.jobRow').each(function() {
			allJobs.push($(this).data('jobName'));
		});
		if ($.inArray(submittedName, allJobs) > -1) {
			alert("Another Job Title exists with that name.");
			return false;
		}
		
		//Job Title name length cannot be 0
		if (submittedName.length == 0) {
			alert("Must complete all required fields")
			return false
		}
	});

});