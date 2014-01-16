$(document).ready(function() {
	$('#addBtn').click(function() {
		$('#addBtn-container').fadeToggle("fast", "linear", function() {
			$('#addEntity').fadeToggle("fast", "linear");
		});
	});
	
	$('#cancelAddBtn').click(function(e){
		e.preventDefault();
		$('#addEntity').fadeToggle("fast", "linear", function() {
			$('#addBtn-container').fadeToggle("fast", "linear");
		});
		
		$('#addEntity input[type=text], #addEntity select').val("");
	});
	
	$('.editBtn').click(function() {
		var ID = $(this).val();
		$('#row'+ID).fadeToggle("fast","linear",function(){
			$('#editRow'+ID).fadeToggle("fast","linear");
		});
		
		$('#editRow'+ID+' .firstName').val($('#row'+ID+' .firstName').data('value'));
		$('#editRow'+ID+' .lastName').val($('#row'+ID+' .lastName').data('value'));
		$('#editRow'+ID+' .email').val($('#row'+ID+' .email').data('value'));
		$('#editRow'+ID+' .skype').val($('#row'+ID+' .skype').data('value'));
		$('#editRow'+ID+' .dept').val($('#row'+ID+' .dept').data('value'));
		$('#editRow'+ID+' .job').val($('#row'+ID+' .job').data('value'));
	});
	
	$('.cancelEditBtn').click(function(e){
		e.preventDefault();
		var ID = $(this).val();
		$('#editRow'+ID).fadeToggle("fast","linear",function(){
			$('#row'+ID).fadeToggle("fast","linear");
		});
		
		$('#editRow'+ID+' .firstName').val('');
		$('#editRow'+ID+' .lastName').val('');
		$('#editRow'+ID+' .email').val('');
		$('#editRow'+ID+' .skype').val('');
		$('#editRow'+ID+' .dept').val('');
		$('#editRow'+ID+' .job').val('');
	});
	
	$('.saveBtn').click(function(){
		var ID = $(this).val();
		$.ajax({
			type: "POST",
			url: "emps",
			data: {
					_method: "put",
					id: ID,
					firstName: $('#editRow'+ID+' .firstName').val(), 
					lastName: $('#editRow'+ID+' .lastName').val(),
					email: $('#editRow'+ID+' .email').val(), 
					skypeName: $('#editRow'+ID+' .skype').val(),
					'department.id': $('#editRow'+ID+' .dept').val(), 
					'jobTitle.id': $('#editRow'+ID+' .job').val()
				  },
			success: function(){
				window.location.href="emps";
			}
		});
	});
});