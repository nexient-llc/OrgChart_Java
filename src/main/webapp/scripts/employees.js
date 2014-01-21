$(document).ready(function() {
	$('#addBtn').click(function() {
		$(this).toggle();
		$('#addEntity').slideToggle();
	});
	
	$('#cancelAddBtn').click(function(e){
		e.preventDefault();
		$('#addEntity').slideToggle();
		$('#addBtn').fadeToggle("fast");
		
		$('#addEntity input[type=text], #addEntity select').val("");
	});
	
	$('.editBtn').click(function() {
		cancelEdit($('.activeEdit .cancelEditBtn').val(), false);
		
		var ID = $(this).val();		
		$('#row'+ID).fadeToggle("fast","linear",function(){
			$('#editRow'+ID).fadeToggle("fast","linear",function(){
				$(this).addClass('activeEdit');
			});
		});
		
		$('#editRow'+ID+' .firstName').val($('#row'+ID+' .firstName').data('value'));
		$('#editRow'+ID+' .middle').val($('#row'+ID+' .middle').data('value'));
		$('#editRow'+ID+' .lastName').val($('#row'+ID+' .lastName').data('value'));
		$('#editRow'+ID+' .email').val($('#row'+ID+' .email').data('value'));
		$('#editRow'+ID+' .skype').val($('#row'+ID+' .skype').data('value'));
		$('#editRow'+ID+' .dept').val($('#row'+ID+' .dept').data('value'));
		$('#editRow'+ID+' .job').val($('#row'+ID+' .job').data('value'));
		
		if($('#t1 #th').hasClass('activeTH')){
			$('#t1 #th').fadeToggle("fast","linear", function(){
				$(this).removeClass('activeTH');
				$('#t1 #thEdit').fadeToggle("fast","linear", function(){
					$(this).addClass('activeTH');
				});
			});
		}
	});
	
	$('.cancelEditBtn').click(function(e){
		e.preventDefault();
		var ID = $(this).val();
		cancelEdit(ID, true);
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
					middleInitial: $('#editRow'+ID+' .middle').val(),
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
	
	$('.removeBtn').click(function(){
		var ID = $(this).val();
		$.ajax({
			type: "POST",
			url: "emps",
			data: {
				_method: "delete",
				id: ID
			},
			success: function(){
				window.location.href="emps";
			}
		});
	});
	
	$('#filterBtn').click(function(){
		$(this).toggle();
		$('#filterEntity').slideToggle();
		$.ajax({
			type: "GET",
			url: "emps/names",
			success: function(data){
				$('#fullName').autocomplete({
					source: jQuery.parseJSON(data)
				});
			}
		});
	});
	
	$('#searchBtn').click(function(){
		$.ajax({
			type: "GET",
			url: "emps",
			data: {
				fullName: $('#filterEntity #fullName').val(),
				deptId: $('#filterEntity #deptId').val(),
				jobId: $('#filterEntity #jobId').val()
			},
			success: function(data){
				data = jQuery.parseJSON(data);
				cancelEdit($('.activeEdit .cancelEditBtn').val(), false);
				
				$('#t1 .row:not(.headers)').fadeOut('fast');
				data.forEach(function(empId){
					$('#t1 #row'+empId).fadeIn('fast');
				});
			}
		});
	});
	
	$('#resetBtn').click(function(){
		$('#filterEntity input, #filterEntity select').val('');
		$('#t1 .row:not(.editRow)').fadeIn('fast');
	});
	
	$('#fullName, #deptId, #jobId').keypress(function(e){
		var code = e.keyCode || e.which;
		if(code == 13){
			e.preventDefault();
			$('#searchBtn').click();
		}
	});
});

function cancelEdit(ID, editing){
	$('#editRow'+ID).fadeToggle("fast","linear",function(){
		$(this).removeClass('activeEdit');
		$('#row'+ID).fadeToggle("fast","linear");
	});
	
	$('#editRow'+ID+' .editInput').val('');
	
	if(editing){
		$('#t1 #thEdit').fadeToggle("fast","linear", function(){
			$(this).removeClass('activeTH');
			$('#t1 #th').fadeToggle("fast","linear", function(){
				$(this).addClass('activeTH');
			});
		});
	}
}