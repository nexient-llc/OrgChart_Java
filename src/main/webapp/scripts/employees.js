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
		$('.editColumn').fadeToggle("fast", "linear");
	});
	
	$('.editColumnBtn').click(function() {
		var ID = $(this).val();
		$('.divRow'+ID).fadeOut("fast", "linear", function() {
			$('.divEditRow'+ID).fadeIn("fast", "linear");
		});
	});
});