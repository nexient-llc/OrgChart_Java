$(document).ready(function() {
	$('#addBtn-container').css('width', $('#t1').width());

	
	$('#addBtn').click(function() {
			$('#addEntity').slideToggle('3000', "swing");
		
	});
	
	$('#addCancelBtn').click(function() {
			$('.addClass').slideToggle('3000', "swing");
			
	});
	
	$('.editButton').click(function() {
		
		var query = $(this).attr('value');
    	
    	$.ajax({
	        type: 'get',
	        url: 'depart',
	        data: {id:query},
	        success: function (data) {
	        	var department = $.parseJSON(data);
	        	$('#departId').val(department.id);
	        	$('#department').val(department.name);
	        	$('#parentId').val(department.parentDepartment.id);
	        }
	    });
		$('#addEntity').slideToggle('3000', "swing");
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

});
