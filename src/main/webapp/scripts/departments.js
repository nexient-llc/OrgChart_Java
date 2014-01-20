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
		$('#editBtn-container').fadeToggle("fast", "linear", function() {
			$('#editEntity').fadeToggle("fast", "linear");
		});
		$('#addBtn-container').fadeToggle("fast", "linear");
		$('#deleteBtn-container').fadeToggle("fast", "linear");
	});
	
	$('#selectBtn').click(function() {
		$('.editSelect').fadeOut("fast", "linear");
		$('#editSelectName').fadeOut("fast", "linear");
		var text = $('#editSelectName option:selected').text(); // For setting default value of the New Name Field
		$('#selectBtn').fadeOut("fast", "linear", function() {
			$('.editFields').fadeIn("fast", "linear");
			$('#editNewNameField').fadeIn("fast", "linear");
			$('#editNewNameField').val(text); // For setting default value of the New Name Field
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
	
	/* Cancel button */
	$('.cancelBtn').click(function() {
		$('#addEntity').fadeOut("fast", "linear", function() {
			$('#editNewNameField').fadeOut("fast", "linear");
			$('.editFields').fadeOut("fast", "linear", function() {
				$('#selectBtn').fadeIn("fast", "linear", function() {
					$('.editSelect').fadeIn("fast", "linear");
					$('#editSelectName').fadeIn("fast", "linear");
				});
			});
			$('#editEntity').fadeOut("fast", "linear", function() {
				$('#deleteEntity').fadeOut("fast", "linear", function() {
					$('#addBtn-container').fadeToggle("fast", "linear");
					$('#editBtn-container').fadeToggle("fast", "linear");
					$('#deleteBtn-container').fadeToggle("fast", "linear");
				});
			});
		});
	});
});
