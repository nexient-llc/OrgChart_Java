$(document).ready(function() {
	$('#addBtn-container').css('width', $('#t1').width());

	$('#addBtn').click(function() {
		lastClicked($('#addEntity'), this);
	});
	
	$('.editBtn').click(function(){
		$('#editEntity').fadeIn("fast");
		var id = this.val();
		
		$('#EditId').attr({
			'value': id
		});
	});
	
	$('#EditEmp').submit(function(){
		var jobId = $('#[job_id_^]').attr('value');
		var deptId = $('#[department_id_^]').attr('value');
		
		if(deptId == 0){
			$('EditEmp').attr({
				'name': 'no Department Specified'
			});
		} else {
			$('EditEmp').attr({
				'value': deptId
			});
		}
		
		if(jobId == "..."){
			$('#EditEmp').attr({
				'name': "no Job Set"
			});
		} else {
			$('#EditEmp').attr({
				'value': jobId
			});
		}
	});
});
