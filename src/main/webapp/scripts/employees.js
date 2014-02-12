$(document).ready(function() {
	$('#addBtn-container').css('width', $('#t1').width());

	$('#addBtn').click(function() {
		
		$('#addBtn-container').fadeToggle("fast", "linear", function() {
			$('#addEntity').fadeToggle("fast", "linear");
		});
	});
	
	$('.cancelBtn').click(function() {
		$('#addEntity').hide();
		$('#editEntity').hide();
		$('#addBtn-container').fadeToggle("fast", "linear");
		
	});
	$('.editBtn').click(function() {
		$('#addEntity').hide();
		$('#editEntity').fadeToggle("fast", "linear");
		$('#addBtn-container').hide();
		$('#editEmployeeId').hide();
		$('#t1 tr').click(function() {
			
			$('#editEmployeeId').val(this.id);
			$('#editFirstName').val($(this).children()[1].innerHTML);
			$('#editLastName').val($(this).children()[2].innerHTML);
			$('#editMiddleInitial').val($(this).children()[3].innerHTML);
			$('#editEmail').val($(this).children()[4].innerHTML);
			$('#editSkype').val($(this).children()[5].innerHTML);
			$('#editIsManager').val($(this).children()[6].innerHTML);
			$('#editJobTitle').val($(this).children()[7].className);
			$('#editDepartment').val($(this).children()[8].className);
			//console.log($(this).children()[7].className);
				
			//$('#editJobTitle').val(1014);
			//alert(employeeFirstName);
			//var jobTitleName = $(this).children()[0].innerHTML;
			/*$('#editJobtitleName').val(jobTitleName);
			$('#editJobTitleId').val(jobTitleId);
			$('#editJobTitleId').hide();*/

		});

	});
	
});
