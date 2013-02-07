$(document).ready(function() {
	$('#addBtn-container').css('width', $('#t1').width());

	$('#addBtn').click(function() {
		$('#addBtn-container').fadeToggle("fast", "linear", function() {
			$('#addChange-legend').text("Add Employee");
			$('#addChangeEntity').fadeToggle("fast", "linear");
		});
	});
	
	$('.editLink').click(function(){
		$('#hiddenEmpID').val(this.id);		
			
		$('#editEmpForm').submit();
	});

});
