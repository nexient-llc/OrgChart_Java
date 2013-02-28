$(document).ready(
	function() 
	{
		//Find department by ID click
		$('#ByIDButton').click(
			function()
			{	
				//check if an id was entered and is numeric
				if( !$.isNumeric($('#deptID').val().trim()) )
				{
					alert('No department id was supplied,  or supplied id was not numeric.');
					$('#deptID').focus().select();
				}
				
				//verify that the id is greater then 0
				else if ($('#deptID').val().trim() <= 0)
				{
					alert('Please enter a numeric id greater than 0.');
					$('#deptID').focus().select();
				}

				//otherwise submit the form
				else
				{
					$('#deptByID').submit();
				}	
			}
		);
		
		//Find department by name click
		$('#ByNameButton').click(
			function()
			{
				//verify the user entered text to be searched for
				if($('#deptName').val() == null || $('#deptName').val().trim().length <= 0)
				{
					alert('No department name was entered. Please enter a name and search again.');
					$('#deptName').focus().select();
				}
				
				//submit the form
				else
				{
					$('#deptByName').submit();
				}
				
			}
		);
		
		//cancel button for add or edit panes, closes the open leaving only the search pane
		$('.cancelBtn').click(
				function()
				{
					//determine which pane is open so you know which one to close
					if( $('#addDeptEntity').is(':visible') )
					{
						$('#addDeptEntity').toggle();
					}
					else
					{
						$('#editDeptEntity').toggle();
					}
				}
		);
		
		//update button click, verifies that the 
		$('.updateBtn').click(
			function()
			{
				//verify that the name to be edited is not blank
				if($('#editName').val().length <= 0)
				{
					alert('Department name must not be blank.');
					$('#editName').focus().select();
				}
				
				//if all OK, submit the form
				else
				{
					$('#editDept').submit();
				}
			}
		);
		
		//edit button on list view 'get's the specified dept information and displays the edit view
		$('.editLink').click(
				function()
				{
					//find the ID of the row selected
					var deptID = $(this).closest('tr').attr('id');
					
					//populate the by id search form with the passed employee id
					$('#deptID').val(deptID);
					
					//submit the edit employee form to pull up the edit page
					$('#deptByID').submit();
				}
		);
		
		//delete button on list view, verifies user wants to delete selected record and posts delete request
		$('.deleteLink').click(
				function() 
				{
					var deptID = 0;
					var deptName = "";
					
					//verify which pane is open to determine which set of commands to run
					if($('#deptDisplay').is(':visible'))
					{
						//find the ID of the row selected for deletion
						deptID = $(this).closest('tr').attr('id');
						
						//retrieve the dept name of that row
						deptName = $('#'+deptID).children('td:first').text().trim().replace(/\b[a-z]/g, 
								function(letter) 
								{
							    	return letter.toUpperCase();
								}
						);
					}
					else
					{
						//find the ID of the record for deletion
						deptID = $('#editDeptID').val();
						
						//retrieve the dept name of that row
						deptName = $('#editName').val().trim().replace(/\b[a-z]/g, 
								function(letter) 
								{
							    	return letter.toUpperCase();
								}
						);
					}
					
					//create the confirmation message for user
					var message = "Are you sure you want to delete '" + deptName + "'?";
					
					if($.isNumeric(deptID) && deptID > 0)
					{
						//verify user wants to delete this record
						if(confirm(message))
						{
							//populate the hidden form field
							$('#deleteDeptID').val(deptID);
							
							//submit the form to delete the record
							$('#deleteDeptByID').submit();
						}
					}
					else
					{
						message = "An error occured processing your request. The page will now refresh. Please try again.";
						//verify user wants to delete this record
						if(confirm(message))
						{
							location.reload();
						}
					}
				}
		);
	}
);
