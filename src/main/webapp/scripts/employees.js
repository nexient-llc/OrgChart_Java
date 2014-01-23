$(document).ready(function() {
	
	/* Add function */
	$('#addBtn-container').css('width', $('#t1').width());

	$('#addBtn').click(function() {
		$('#addBtn-container').fadeToggle("fast", "linear", function() {
			$('#addEntity').fadeToggle("fast", "linear");
		});
		$('#editBtn-container').fadeToggle("fast", "linear");
		$('#deleteBtn-container').fadeToggle("fast", "linear");
	});
	
	/* Edit function */
	$('#editBtn-container').css('width', $('#t1').width());
	
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
			url: "emps",
			data: {
				_method: "put",
				employeeId: ID,
				firstName: $('#editInputBox'+ID+'firstName').val(),
				lastName: $('#editInputBox'+ID+'lastName').val(),
				middleInitial: $('#editInputBox'+ID+'middleInitial').val(),
        		departmentId: $('#editSelectBox'+ID+'departmentId').val(),
        		email: $('#editInputBox'+ID+'email').val(),
        		skypeName: $('#editInputBox'+ID+'skypeName').val(),
        		jobTitleId: $('#editSelectBox'+ID+'jobTitleId').val(),
			},
			success: function() {
				window.location.href="emps";
			}
        });
	});
	
	$('.removeColumnBtn').click(function() {
		var ID = $(this).val();
		$.ajax({
			type: "POST",
			url: "emps",
			data: {
				_method: "delete",
				employeeId: ID,
			},
			success: function() {
				window.location.href="emps";
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