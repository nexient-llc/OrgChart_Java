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
		$('#ViewEmps'+num).fadeToggle("fast", "linear", function() {
			$('#EditEmps'+num).fadeToggle("fast", "linear");
		});
		$('#preEditViewBar').fadeToggle("fast", "linear", function() {
			$('#postEditViewBar').fadeToggle("fast", "linear");
		});
	});
	
	$('.deleteBtn').click(function(evnt){
		var num = $(this).val();
		$.ajax({
			url : "emps",
			type : "DELETE",
			success: function(response){
				window.location.href="depts";
			},	
		});
	});
	
});