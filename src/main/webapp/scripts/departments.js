$(document).ready(function() {
	$('#addBtn-container').css('width', $('#t1').width());

	$('#addBtn').click(function() {
		$('#addBtn-container').fadeToggle("fast", "linear", function() {
			$('#addEntity-container').fadeToggle("fast", "linear");
		});
	});
	
	$('#cancelAddButton').click(function() {
		$('#addEntity-container').fadeToggle("fast", "linear", function() {
			$('#addBtn-container').fadeToggle("fast", "linear");
		});
	});
	
	$('.editButton').click(function() {
		
		$.ajax({
			   url : "depts/" + $(this).val(),
			   type : "GET"
		}).done(function(data){
			
			var form = $.parseJSON(data);
			
			$('#departmentName').val(form.name);
			$('#departmentId').val(form.id);
			
			if (form.parentDepartment) {
				$('#parentDepartment').val(form.parentDepartment.id);
			} else {
			    $('#parentDepartment').val("");
			}

			$('#deptTable-container').fadeToggle("fast", "linear");
			$('#editEntity-container').fadeToggle("fast", "linear");
			
		});
	});
	
	$('#cancelEditButton').click(function() {
		$('#editEntity-container').fadeToggle("fast", "linear", function() {
			$('#deptTable-container').fadeToggle("fast", "linear");
		});
	});
	
	
	$('#removeButton').click(function() {
		var deptId = $('#departmentId').val();
		$.ajax({
			   url : "delete/dept/" + deptId,
			   type : "DELETE"
		}).done(function(){
			
			$('#tableRow'+deptId).remove();
			$(".deptDropDown option[value='" +deptId+"']").remove();
			$('#editEntity-container').fadeToggle("fast", "linear", function() {
				$('#deptTable-container').fadeToggle("fast", "linear");
			});
		});
		
	});
	
});
