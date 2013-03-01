$(document).ready(
	function() 
	{
		
		$('#addBtn').click(
				function() 
				{
					$('#addBtn-container').toggle("slow");

					$('#addChangeEntity').toggle("slow", 
							function()
							{ 
								$('#empDisplay').css('border-width','1px');
								$('#empDisplay').css('border-style', 'none dashed none none');
								$('#empDisplay').css('border-color', '#cfcece');
								$('#empDisplay').css('height', $('#pageBody').height());
							}
					).css('display', 'inline-block');
				}
		);
		
		$('.editLink').click(
				function()
				{
					//remove the text from the id
					var splitID = this.id.split("/");
					var empID = splitID[0];
					
					//populate the hidden delete form with the passed employee id
					$('#hiddenEditEmpID').val(empID);
					
					//submit the edit employee form to pull up the edit page
					$('#editEmpForm').submit();
				}
		);
		
		$('.deleteLink').click(
				function()
				{
					//remove the text from the id
					var splitID = this.id.split("/");
					var empID = splitID[0];

					//populate the hidden delete form with the passed employee id
					$('#hiddenEmpID2').val(empID);		
					
					//confirm they want to delete this employee
					var message = "Are you sure you want to delete " + $('tr[name='+empID+']').children('td:first').text().trim() + '?';

					//submit the form
					if(confirm(message))
					{
						$('#deleteEmpForm').submit();
					}
				}
		);
		
		$('#cancelBtn').click(
				function()
				{
					$('#addBtn-container').toggle("slow");
					$('#addChangeEntity').toggle("fast", 
							function()
							{ 
								$('#empDisplay').css('border-width','');
								$('#empDisplay').css('border-style', '');
								$('#empDisplay').css('border-color', '');
								$('#empDisplay').css('min-height', '');
							}
					).css('display', 'none');
				}
		);
	}
);