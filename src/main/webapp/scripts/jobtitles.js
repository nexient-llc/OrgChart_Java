$(document).ready(function() {
	$('#addBtn-container').css('width', $('#t1').width());

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
	});
	
	$('.editJobBtn').click(function() {
//		$('#t1 .activeEditJob').fadeOut("fast","linear",function(){
//			$('#t1 .activeEditJob .cancelJobEditBtn').click();
//		});
		var jobNum = $(this).val();
		$('#jobRow'+jobNum).fadeToggle("fast","linear",function(){
//			$(this).addClass("activeEditJob");
			$('#editJobRow'+jobNum).fadeToggle("fast","linear");
		});
		
		$('#editJobRow'+jobNum+' .editJobName').val($('#jobRow'+jobNum+' .jobName').data('value'));
		$('#editJobRow'+jobNum+' .editJobDesc').val($('#jobRow'+jobNum+' .jobDesc').data('value'));
	});
	
	$('.cancelJobEditBtn').click(function(e){
		e.preventDefault();
		var jobNum = $(this).val();
		$('#editJobRow'+jobNum).fadeToggle("fast","linear",function(){
			$('#jobRow'+jobNum).fadeToggle("fast","linear");
		});
		
		$('#editJobRow'+jobNum+' .editJobName').val("");
		$('#editJobRow'+jobNum+' .editJobDesc').val("");
	});
	
	$('.saveJobBtn').click(function(){
		var jobNum = $(this).val();
		$.ajax({
			type: "PUT",
			url: "jobs",
			data: {
					id: $(this).val(),
					name: $('#editJobRow'+jobNum+' .editJobName').val(), 
					description: $('#editJobRow'+jobNum+' .editJobDesc').val()
				  },
			success: function(){
				window.location.href="jobs";
			}
		});
	});
});