$(document).ready(function() {
	$('#addBtn').click(function() {
		$(this).fadeToggle("fast", "linear", function() {
			$('#addEntity').fadeToggle("fast", "linear");
		});
	});
	
	$('#cancelAddBtn').click(function(e){
		e.preventDefault();
		$('#addEntity').fadeToggle("fast", "linear", function() {
			$('#addBtn').fadeToggle("fast", "linear");
		});
		
		$('#addEntity input[type=text]').val("");
	});
	
	$('.editBtn').click(function() {
		cancelEdit($('.activeEdit .cancelEditBtn').val(), false);
		
		var jobNum = $(this).val();
		$('#jobRow'+jobNum).fadeToggle("fast","linear",function(){
			$('#editJobRow'+jobNum).fadeToggle("fast","linear",function(){
				$(this).addClass('activeEdit');
			});
		});
		
		$('#editJobRow'+jobNum+' .editJobName').val($('#jobRow'+jobNum+' .jobName').data('value'));
		$('#editJobRow'+jobNum+' .editJobDesc').val($('#jobRow'+jobNum+' .jobDesc').data('value'));
		
		if($('#th').hasClass('activeTH')){
			$('.activeTH').fadeToggle("fast","linear", function(){
				$('.headers:not(.activeTH)').fadeToggle("fast","linear", function(){
					$(this).addClass('activeTH');
				});
				$(this).removeClass('activeTH');
			});
		}
	});
	
	$('.cancelEditBtn').click(function(e){
		e.preventDefault();
		var ID = $(this).val();
		cancelEdit(ID, true);
	});
	
	$('.saveBtn').click(function(){
		var jobNum = $(this).val();
		$.ajax({
			type: "POST",
			url: "jobs",
			data: {
					_method: "put",
					id: $(this).val(),
					name: $('#editJobRow'+jobNum+' .editJobName').val(), 
					description: $('#editJobRow'+jobNum+' .editJobDesc').val()
				  },
			success: function(){
				window.location.href="jobs";
			}
		});
	});
	
	$('.removeBtn').click(function(){
		var ID = $(this).val();
		$.ajax({
			type: "POST",
			url: "jobs",
			data: {
				_method: "delete",
				id: ID
			},
			success: function(){
				window.location.href="jobs";
			}
		});
	});
});

function cancelEdit(ID, editing){
	$('#editJobRow'+ID).fadeToggle("fast","linear",function(){
		$(this).removeClass('activeEdit');
		$('#jobRow'+ID).fadeToggle("fast","linear");
	});
	
	$('#editJobRow'+ID+' .editJobName').val("");
	$('#editJobRow'+ID+' .editJobDesc').val("");
	
	if(editing){
		$('#thEdit').fadeToggle("fast","linear", function(){
			$(this).removeClass('activeTH');
			$('#th').fadeToggle("fast","linear", function(){
				$(this).addClass('activeTH');
			});
		});
	}
}