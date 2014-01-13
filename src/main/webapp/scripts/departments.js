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
	
	$('.editBtn').click(function(evnt) {
		var num = $(this).val();
		evnt.preventDefault();
		$('#ViewDepts'+num).fadeToggle("fast", "linear", function() {
			$('#EditDepts'+num).fadeToggle("fast", "linear");
		});
	});
	
	$('.saveBtn').click(function(evnt) {
		//TODO
	});
	
});
