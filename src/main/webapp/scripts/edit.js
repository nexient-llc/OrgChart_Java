$(document).ready(function() {
	$('#saveBtn').click(function() {
		if($('#deleteEmployee').val()){
			$('#editEmp').attr('action', 'delete');
		}		
	});
	
});
