$(document).ready(function() {
	$('#addBtn-container').css('width', $('#t1').width());

	$('#addBtn').click(function() {
		lastClicked($('#addEntity'), this);
	});
	
	$('.editBtn').click(function(){
		$('#editEntity').fadeToggle("fast", "linear");
		var id = this.val();
	});
	
	$('#EditEmp').submit(function(){
		var jobId = $('#[job_id_^]').attr('value');
		var deptId = $('#[department_id_^]').attr('value');
		
		$('#id').attr({
			'value': id
		});
		
		if(deptId == "..."){
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
