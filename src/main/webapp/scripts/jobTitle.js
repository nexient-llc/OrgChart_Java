$(document).ready(function() {
	$('#addBtn-container').css('width', $('#t1').width());

	$('#addBtn').click(function() {
		$('#addBtn-container').slideToggle('4000', "swing", function() {
			$('#addEntity').slideToggle('4000', "swing");
		});
	});
	$("#cancel").click(function(){

		$('#addEntity').slideToggle('3000', "swing");
		$('#addBtn').slideToggle('3000', "swing");
	});
	
	$('.editButton').click(function() {
		
		$('#addBtn').slideToggle('3000', "swing");
		
		var query = $(this).attr('value');
		if(query != null){
			$.ajax({
				   
				type: 'get',
		        url: 'findJobTitle',
		        data: {id:query},
		        success: function (data) {
		        	
		        	var job = $.parseJSON(data);
		        	$('#titleId').val(job.id);
		        	$('#job').val(job.name);
		        	$('#activeBox').prop("checked",job.isActive);
		        }
		    });
		}//end of if statement	
		
		$('#addEntity').slideToggle('3000', "swing");
	
	});
});