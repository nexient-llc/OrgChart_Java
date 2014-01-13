$(document).ready(function() {
	$('#addBtn-container').css('width', $('#t1').width());
	$('#editBtn-container').css('width', $('#t1').width());

	$('#addBtn').click(function() {
		$('#addBtn-container').fadeToggle("fast", "linear", function() {
			$('#addEntity').fadeToggle("fast", "linear");
		});
	});
	
	$('#cancelBtn').click(function(evnt) {
		evnt.preventDefault();
		$('#addEntity').fadeToggle("fast", "linear", function() {
			$('#addBtn-container').fadeToggle("fast", "linear");
		});
	});
	
	$('#editBtn').click(function(evnt) {
		$('#editEntity').fadeToggle("fast", "linear", function() {
			$('#editBtn-container').fadeToggle("fast", "linear");
		});
	});
	
});
