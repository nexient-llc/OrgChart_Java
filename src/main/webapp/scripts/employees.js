$(document).ready(function() {
	$('#addBtn-container').css('width', $('#t1').width());

	$('#addBtn').click(function() {
		$('#addBtn-container').fadeToggle("fast", "linear", function() {
			$('#addChange-legend').text("Add Employee");
			$('#addChangeEntity').fadeToggle("fast", "linear");
		});
	});
	
	$('#editLink').click(function(){
		$('#hiddenEmpID').val($(this).parent().eq(2).attr('id'));
		alert($('#hiddenEmpID').val());
		$('#editEmpForm').submit();    
		
//		$('#addChange-legend').text("Edit Employee");
//		$('#addChangeEntity').fadeToggle("fast", "linear"); 
	});

});
