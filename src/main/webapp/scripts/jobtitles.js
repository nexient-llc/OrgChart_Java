$(document).ready(function() {
	
	/* Add function */
	$('#addBtn').click(function() {
		$('#addBtn-container').fadeToggle("fast", "linear", function() {
			$('#addEntity').fadeToggle("fast", "linear");
		});
		$('#editBtn-container').fadeToggle("fast", "linear");
		$('#deleteBtn-container').fadeToggle("fast", "linear");
	});
	
	/* Edit function */
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
		$('#divRow'+ID).fadeToggle("fast", "linear", function() {
			$('#divEditRow'+ID).fadeToggle("fast", "linear");
		});
		$('.editColumnBtn').fadeToggle("fast", "linear");
		$('#cancelBtn-container').fadeToggle("fast", "linear");
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