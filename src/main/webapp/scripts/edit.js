$(document).ready(function() {
	$('#saveBtn').click(function() {
		if($('#deleteEmployee').attr('checked')){
			$('#editEmp').attr('action', 'delete');
		}		
	});
	
	$('#deleteEmployee').change(function(){
			if(this.checked){
				alert("You have selected to delete the current employee,\r\n" +
						"Please double check that this is correct before saving.");
			}
	});
	
});
