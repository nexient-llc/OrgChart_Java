$(document).ready(function() {

	//find by ID
	$('#ByIDButton').click(
			function()
			{
				//check if an id was entered and is numeric
				if( !$.isNumeric($('#jobTitleID').val().trim()) )
				{
					alert('No job title id was supplied,  or id was not numeric.');
					$('#jobTitleID').focus().select();
				}
				
				//verify that the id is greater then 0
				else if ($('#jobTitleID').val().trim() <= 0)
				{
					alert('Please enter a numeric id greater than 0.');
					$('#jobTitleID').focus().select();
				}

				//otherwise submit the form
				else
				{
					$('#jobByID').submit();
				}	
			}
	);
	
	//find by description
	$('#ByNameButton').click(
			function()
			{
				//verify the user entered text to be searched for
				if($('#jobTitleName').val() == null || $('#jobTitleName').val().trim().length <= 0)
				{
					alert('No job title was entered. Please enter a title and search again.');
					$('#jobTitleName').focus().select();
				}
				
				//submit the form
				else
				{
					$('#jobByName').submit();
				}
			}
	);
	
	//show the add pane
	$('#addBtn').click(
			function() 
			{
				if($('#jobDisplay').is(':visible'))
				{
					$('#jobDisplay').toggle();
				}
				
				if($('#editJobEntity').is(':visible'))
				{
					$('#editJobEntity').toggle();
				}
				
				if($('#errorMsg').is(':visible'))
				{
					$('#errorMsg').toggle();
				}
				
				$('#addJobEntity').toggle();
			}
	);
	
	//cancel button for add or edit panes, closes the open leaving only the search pane
	$('.cancelBtn').click(
			function()
			{
				//determine which pane is open so you know which one to close
				if( $('#addJobEntity').is(':visible') )
				{
					$('#addJobEntity').toggle();
				}
				else
				{
					$('#editJobEntity').toggle();
				}
			}
	);
	
	//update button click, verifies that the 
	$('.updateBtn').click(
		function()
		{
			//verify that the description to be edited is not blank
			if($('#editDesc').val().length <= 0)
			{
				alert('Job Title name must not be blank.');
				$('#editDesc').focus().select();
			}
			
			//if all OK, submit the form
			else
			{
				$('#editJob').submit();
			}
		}
	);
	
	//edit button on list view 'get's the specified dept information and displays the edit view
	$('.editLink').click(
			function()
			{
				//find the ID of the row selected
				var jtID = $(this).closest('tr').attr('id');
				
				//populate the by id search form with the passed employee id
				$('#jobTitleID').val(jtID);
				
				//submit the edit employee form to pull up the edit page
				$('#jobByID').submit();
			}
	);
	
	//delete button on list view, verifies user wants to delete selected record and posts delete request
	$('.deleteLink').click(
			function() 
			{
				var jtID = 0;
				var jtDesc = "";
				
				//verify which pane is open to determine which set of commands to run
				if($('#jobDisplay').is(':visible'))
				{
					//find the ID of the row selected for deletion
					jtID = $(this).closest('tr').attr('id');
					
					//retrieve the dept name of that row
					jtDesc = $('#'+jtID).children('td:first').text().trim().replace(/\b[a-z]/g, 
							function(letter) 
							{
						    	return letter.toUpperCase();
							}
					);
				}
				else
				{
					//find the ID of the record for deletion
					jtID = $('#editJobID').val();
					
					//retrieve the dept name of that row
					jtDesc = $('#editDesc').val().trim().replace(/\b[a-z]/g, 
							function(letter) 
							{
						    	return letter.toUpperCase();
							}
					);
				}
				
				//create the confirmation message for user
				var message = "Are you sure you want to delete '" + jtDesc + "'?";
				
				
				if($.isNumeric(jtID) && jtID > 0)
				{
					//verify user wants to delete this record
					if(confirm(message))
					{
						//populate the hidden form field
						$('#deleteJobID').val(jtID);
						
						//submit the form to delete the record
						$('#deleteJobByID').submit();
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
	
});