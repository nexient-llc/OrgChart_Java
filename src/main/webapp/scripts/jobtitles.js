$(document).ready(function() {
	
	/* Add function */
	$('#addBtn').click(function() {
		$('#addBtn-container').fadeToggle("fast", "linear", function() {
			$('#addEntity').fadeToggle("fast", "linear");
		});
		$('#editBtn-container').fadeToggle("fast", "linear");
		$('#deleteBtn-container').fadeToggle("fast", "linear");
	});
	
	$('#addInputBox').on('keyup', function() {
		var myLen = $(this).val().length;
        $.ajax({
			type: "GET",
			url: "checkJobs",
			data: {
				name: $(this).val(),
			},
			success: function(response) {
				if(response == false) {
					if(myLen != 0) {
						$('#funAddInputBox').attr('src', '/orgchart/images/ok.gif');
						$('#saveEntity').removeAttr('disabled');
					}
					else {
						$('#funAddInputBox').removeAttr('src');
						$('#saveEntity').attr('disabled', true);
					}
				}
				else {
					$('#funAddInputBox').attr('src', '/orgchart/images/delete.gif');
					$('#saveEntity').attr('disabled', true);
				}
			},
        });
	});
	
	$('#saveEntity').click(function() {
		$.ajax({
			type: "POST",
			url: "jobs",
			data: {
				_method: "post",
				name: $('#addInputBox').val(),
			},
			success: function() {
				window.location.href="jobs";
			},
			error: function() {
				alert('Request failed!');
			}
		});
	});
	
	/* Edit function */
	var initialNameVal;
	
	$('#editBtn').click(function() {
		$('.divHeaderHidden').fadeToggle("fast", "linear");
		$('.divColumnHidden').fadeToggle("fast", "linear");
		$('#addBtn-container').fadeToggle("fast", "linear");
		$('#editBtn-container').fadeToggle("fast", "linear", function() {
			$('#cancelBtn-container').fadeToggle("fast", "linear");
		});
	});
	
	$('.editColumnBtn').click(function() {
		var ID = $(this).val();
		initialNameVal = $('#editInputBox'+ID).val();
		$('#divRow'+ID).fadeToggle("fast", "linear", function() {
			$('#divEditRow'+ID).fadeToggle("fast", "linear");
		});
		$('.editColumnBtn').fadeToggle("fast", "linear");
		$('#cancelBtn-container').fadeToggle("fast", "linear");
	});
	
	$('[id^=editInputBox]').on('keyup', function() {
		var myLen = $(this).val().length;
		var myStr = $(this).val();
        $.ajax({
			type: "GET",
			url: "checkJobs",
			data: {
				name: $(this).val(),
			},
			success: function(response) {
				if(response == false) {
					if(myLen != 0) {
						$('.funEditInputBox').attr('src', '/orgchart/images/ok.gif');
						$('.submitColumnBtn').removeAttr('disabled');
					}
					else { 
						$('.funEditInputBox').removeAttr('src');
						$('.submitColumnBtn').attr('disabled', true);
					}
				}
				else {
					if(initialNameVal == myStr) {
						$('.funEditInputBox').attr('src', '/orgchart/images/ok.gif');
						$('.submitColumnBtn').removeAttr('disabled');
					}
					else {
						$('.funEditInputBox').attr('src', '/orgchart/images/delete.gif');
						$('.submitColumnBtn').attr('disabled', true);
					}
				}
			},
        });
	});
	
	$('.submitColumnBtn').click(function() {
		var ID = $(this).val();
        $.ajax({
			type: "POST",
			url: "jobs",
			data: {
				_method: "put",
				jobTitleId: ID,
				name: $('#editInputBox'+ID).val(),
			},
			success: function() {
				window.location.href="jobs";
			}
        });
	});
	
	$('.removeColumnBtn').click(function() {
		var ID = $(this).val();
        $.ajax({
			type: "POST",
			url: "jobs",
			data: {
				_method: "delete",
				jobTitleId: ID,
			},
			success: function() {
				window.location.href="jobs";
			}
        });
	});
	
	$('.cancelColumnBtn').click(function() {
		var ID = $(this).val();
		$('.funEditInputBox').removeAttr('src');
		$('.submitColumnBtn').removeAttr('disabled');
		$('#editInputBox'+ID).val(initialNameVal);
		$('#divEditRow'+ID).fadeToggle("fast", "linear", function() {
			$('#divRow'+ID).fadeToggle("fast", "linear");
		});
		$('.editColumnBtn').fadeToggle("fast", "linear");
		$('#cancelBtn-container').fadeToggle("fast", "linear");
	});
	
	/* Cancel button */
	$('#cancelBtn').click(function() {
		$('.divHeaderHidden').fadeToggle("fast", "linear");
		$('.divColumnHidden').fadeToggle("fast", "linear");
		$('#cancelBtn-container').fadeToggle("fast", "linear", function () {
			$('#addBtn-container').fadeToggle("fast", "linear");
			$('#editBtn-container').fadeToggle("fast", "linear");
		});
	});
	
	$('#cancelEntity').click(function() {
		$('#addEntity').fadeToggle("fast", "linear");
		$('#addBtn-container').fadeToggle("fast", "linear");
		$('#editBtn-container').fadeToggle("fast", "linear");
	});
});