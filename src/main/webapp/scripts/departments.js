$(document).ready(
	function() 
	{
		$('#addBtn-container').css('width', $('#t1').width());
	
		$('#addBtn').click(
				function() 
				{
					$('#addBtn-container').toggle("slow");
					$('#addDeptEntity').toggle("slow", 
							function()
							{ 
								$('#deptDisplay').css('border-width','1px');
								$('#deptDisplay').css('border-style', 'none dashed none none');
								$('#deptDisplay').css('border-color', '#cfcece');
								$('#deptDisplay').css('height', $('#pageBody').height());
							}
					).css('display', 'inline-block');
				}
		);
			
		$('#cancelBtn').click(
				function()
				{
					$('#addBtn-container').toggle("slow");
					$('#addDeptEntity').toggle("fast", 
							function()
							{ 
								$('#deptDisplay').css('border-width','');
								$('#deptDisplay').css('border-style', '');
								$('#deptDisplay').css('border-color', '');
								$('#deptDisplay').css('min-height', '');
							}
					).css('display', 'none');
				}
		);
		
		$('.editLink').click(
				function()
				{
					//find the ID of the row selected
					var deptID = $(this).closest('tr').attr('id');
					
//					//populate the hidden delete form with the passed employee id
//					$('#hiddenEditEmpID').val(empID);
//					
//					//submit the edit employee form to pull up the edit page
//					$('#editEmpForm').submit();
				}
		);
		
		$('.deleteLink').click(
				function() 
				{
					//find the ID of the row selected for deletion
					var deptID = $(this).closest('tr').attr('id');
					
					//retrieve the dept name of that row
					var deptName = $('#'+deptID).children('td:first').text().trim();
					
					//create the confirmation message for user
					var message = "Are you sure you want to delete '" + deptName + "'?";
					
					//verify user wants to delete this record
					if(confirm(message))
					{
						//populate the hidden form field
						$('#deleteDeptID').val(deptID);
						
						//submit the form to delete the record
						$('#deleteDeptByID').submit();
					}
					
				}
		);
		
	}
);
