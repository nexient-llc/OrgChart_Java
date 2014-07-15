$(document).ready(function() {
	$('#addBtn-container').css('width', $('#t1').width());

	
	$('#addBtn').click(function() {
		$('#addBtn-container').fadeToggle("fast", "linear", function() {
			$('#addEntity').slideToggle('3000', "swing");
		});
		
	});
	
	$('#addCancelBtn').click(function() {
			$('.addClass').slideToggle('3000', "swing");
			$('#addBtn-container').fadeToggle("fast", "linear");
	});
	
	$('.editButton').click(function() {
		
		var query = $(this).attr('value');
    	
    	$.ajax({
	        type: 'get',
	        url: 'depart',
	        data: {id:query},
	        success: function (data) {
	        	var departmentInfo = $.parseJSON(data);	
	        	$('#department').val(departmentInfo['name']);
	        	$('#departId').val(departmentInfo['id']);
	        }
	    });
		$('#editEntity').slideToggle('3000', "swing");
		});

	$("#cancel").click(function(){
		$(".editClass").slideToggle('3000', "swing");
	   
	});
	$('.removeButton').click(function() {
	
		var query = $(this).attr('value');
		$.ajax({
	        type: 'get',
	        url: 'depart',
	        data: {id:query},
	        success: function (data) {
	        	var departmentInfo = $.parseJSON(data);	
	        	$('#removeDepartment').val(departmentInfo['name']);
	        	$('#removeDepartId').val(departmentInfo['id']);
	        }
	    });
		$('#removeDepartmentID').val($(this).attr('value'));
		$('#removeEntity').slideToggle('3000', "swing");
	});
	$("#removeCancel").click(function(){
		$(".removeClass").slideToggle('3000', "swing");
	   
	});

	

});
