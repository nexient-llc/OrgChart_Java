$(document).ready(function() {
	
	var hideButtons = function() {
		$('#addBtn-container').fadeToggle("fast", "linear");
		$('#editBtn-container').fadeToggle("fast", "linear");
		$('#deleteBtn-container').fadeToggle("fast", "linear");
	};

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
		$('#editBtn-container').fadeToggle("fast", "linear", function() {
			$('#editEntity').fadeToggle("fast", "linear");
		});
		$('#addBtn-container').fadeToggle("fast", "linear");
		$('#deleteBtn-container').fadeToggle("fast", "linear");
	});
	
	$('#goBtn').click(function() {
		$('.editSelect').fadeToggle("fast", "linear");
		$('#goBtn').fadeToggle("fast", "linear", function() {
			$('.editFields').fadeToggle("fast", "linear");
		});
	});
	
	/* Delete function */
	$('#deleteBtn-container').css('width', $('#t1').width());
	
	$('#deleteBtn').click(function() {
		$('#deleteBtn-container').fadeToggle("fast", "linear", function() {
			$('#deleteEntity').fadeToggle("fast", "linear");
		});
		$('#addBtn-container').fadeToggle("fast", "linear");
		$('#editBtn-container').fadeToggle("fast", "linear");
	});
});
