$(document).ready(function() {
	$('#addBtn-container').css('width', $('#t1').width());

	$('#addBtn').click(function() {
		$('#addBtn-container').fadeOut("fast", "linear", function() {
			$('#addEntity').fadeIn("fast", "linear");
		});
		$('.editJobTitle').fadeOut("fast", "linear");
	});
	
	$('#cancelAdd').click(function() {
		$('#addEntity').fadeOut("fast", "linear", function() {
			$('#addBtn-container').fadeIn("fast", "linear");
			$('.editJobTitle').fadeIn("fast, linear");
		});	
	});
	
	$('.editJobTitle').click(function() {
		$('#editEntity input#name').val($(this).closest('tr.jobRow').data('jobName'));
		$('#editEntity input#id').val($(this).closest('tr.jobRow').data('jobId'));

		$('.editJobTitle').fadeOut("fast", "linear");
		$('#addBtn-container').fadeOut("fast", "linear", function() {
			$('#editEntity').fadeIn("fast", "linear");
		});
	});
	
	$('#cancelEdit').click(function() {
		$('#editEntity').fadeOut("fast", "linear", function() {
			$('#addBtn-container').fadeIn("fast", "linear");
			$('.editJobTitle').fadeIn("fast, linear");
		});
	});
	
	$('form').submit(function() {
		var allJobs = new Array();
		var submittedName = $(this).find('input#name').val();
		$('#t1').find('tr.jobRow').each(function() {
			allJobs.push($(this).data('jobName'));
		});
		
		if ($.inArray(submittedName, allJobs) > -1) {
			alert("Another Job Title exists with that name.");
			return false;
		}
		
		if (submittedName.length == 0) {
			alert("Must complete all required fields")
			return false
		}
	});

});