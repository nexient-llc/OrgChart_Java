$(document).ready(
	function() 
	{
		
		//find by ID
		$('#findByIDBtn').click(
				function() 
				{		
					//check that an id was entered and that it is numeric
					if( !$.isNumeric($('#findEmpID').val().trim()) )
					{
						alert("No employee id was supplied,  or supplied id was not numeric.");
						$('#findEmpID').val('').focus();
					}
					
					//verify that the id is greater then 0
					else if ( !($('#findEmpID').val().trim() > 0) )
					{
						alert('Please enter a numeric id greater than 0.');
						$('#findEmpID').val('').focus();
					}

					//otherwise submit the form
					else
					{
						$('#findByID').submit();
					}
				}
		);
		
		//find by name
		$('#findByNameBtn').click(
				function()
				{				
					//check if the box is empty and assign a 0 to be passed for the blank var
					if($('#findFirstName').val().trim().length < 1)
					{
						$('#findFirstName').val("{blank}");
					}
					else
					{
						$('#findFirstName').val($('#findFirstName').val().trim());
					}
					
					if($('#findLastName').val().trim().length < 1)
					{
						$('#findLastName').val("{blank}");
					}
					else
					{
						$('#findLastName').val($('#findLastName').val().trim());
					}
					
					if($('#findFirstName').val() == "{blank}" && $('#findLastName').val() == "{blank}" )
					{
						$('#findFirstName').val('');
						$('#findLastName').val('');
						alert('Please enter a full or partial first, or last, name or a combination of first and last name.');
						$('#findFirstName').focus();
					}
					else
					{
						$('#findByName').submit();
					}
				}
		);
		
		//find by email
		$('#findByEmailBtn').click(
				function()
				{
					if( !$('#findEmail').val().trim().length > 0 )
					{
						alert('Please enter a full or partial email address.');
					}
					else
					{
						$('#findByEmail').submit();
					}
				}
		);
		
		//find by department
		$('#findByDeptBtn').click(
				function()
				{
					if($('#deptSelection').val() <= 0)
					{
						alert('Please select a department.');
					}
					else
					{
						$('#findByDept').submit();
					}
				}
		);
		
		//find by title
		$('#findTitleBtn').click(
				function()
				{
					if($('#titleSelection').val() <= 0)
					{
						alert('Please select a job title.');
					}
					else
					{
						$('#findByTitle').submit();
					}
				}
		); 
		
		//find by manager
		$('#findByManagerBtn').click(
				function()
				{
					if($('#mgrSelection').val() <= 0)
					{
						alert('Please select a manager.');
					}
					else
					{
						$('#findByManager').submit();
					}
				}
		);
		
		//edit a record from the list
		$('.editLink').click(
				function()
				{
					//find the ID of the row selected
					var empID = $(this).closest('tr').attr('id');
					
					//populate the by id search form with the passed employee id
					$('#findEmpID').val(empID);
					
					//submit the edit employee form to pull up the edit page
					$('#findByID').submit();
				}
		);
		
		//delete a record
		$('.deleteBtn').click(
				function()
				{
					var empID = 0;
					var empName = "";
					
					//verify which pane is open to determine which set of commands to run
					if($('#empDisplay').is(':visible'))
					{
						//find the ID of the row selected for deletion
						empID = $(this).closest('tr').attr('id');
						
						//retrieve the emp name of that row, cap the first letter
						empName = $('#'+empID).children('td:first').text().trim().replace(/\b[a-z]/g, 
								function(letter) 
								{
							    	return letter.toUpperCase();
								}
						);
					}
					else
					{
						//find the ID of the record for deletion
						empID = $('#editEmpID').val();
						
						//retrieve the first name of that row
						empName = $('#editFirstName').val().trim().replace(/\b[a-z]/g, 
								function(letter) 
								{
							    	return letter.toUpperCase();
								}
						);
						
						//retrieve the last name of that row
						empName += " " + $('#editLastName').val().trim().replace(/\b[a-z]/g, 
								function(letter) 
								{
							    	return letter.toUpperCase();
								}
						);
					}
					
					//create the confirmation message for user
					var message = "Are you sure you want to delete '" + empName + "'?";
					
					if($.isNumeric(empID) && empID > 0)
					{
						//verify user wants to delete this record
						if(confirm(message))
						{
							//populate the hidden form field
							$('#deleteEmpID').val(empID);
							
							//submit the form to delete the record
							$('#deleteEmpForm').submit();
						}
					}
					else
					{
						message = "An error occured processing your request. The page will now refresh. Please try again.";
												
						if(confirm(message))
						{
							location.reload();
						}
					}
				}
		);
		
		//cancel the add or edit actions
		$('.cancelBtn').click(
				function()
				{
					//check which pane is open
					if($('#editEmpEntity').is(':visible'))
					{
						//close the edit pane
						$('#editEmpEntity').toggle();
					}
					else
					{
						//otherwise close the add pane
						$('#addEmpEntity').toggle();
					}
				}
		);
		
		//add new employee, verification
		$('.saveBtn').click(
				function()
				{
					//verify basic fields are not empty
					//first name
					if( !($('#firstName').val().trim().length > 0) )
					{
						alert('Please enter a first name');
						$('#firstName').val('').focus();
					}
					//last name
					else if ( !($('#lastName').val().trim().length > 0) )
					{
						alert('Please enter a last name.');
						$('#lastName').val('').focus();
					}
					//everything else is optional, so submit
					else
					{
						if( !($('#email').val().trim().length > 0) )
						{
							$('#email').val(null);
						}
						
						if( !($('#skypeName').val().trim().length > 0) )
						{
							$('#skypeName').val(null);
						}
						
						$('#newEmp').submit();
					}
				}
		);
		
		//update button, almost identical to adding a new emp
		$('.updateBtn').click(
				function()
				{
					//verify basic fields are not empty
					//first name
					if( !($('#editFirstName').val().trim().length > 0) )
					{
						alert('Please enter a first name');
						$('#editFirstName').val('').focus();
					}
					//last name
					else if ( !($('#editLastName').val().trim().length > 0) )
					{
						alert('Please enter a last name.');
						$('#editLastName').val('').focus();
					}
					//everything else is optional, so submit
					else
					{
						//change blank email to null
						if( !($('#editEmail').val().trim().length > 0) )
						{
							$('#editEmail').val(null);
						}
						
						//change blank skype to null
						if( !($('#editSkypeName').val().trim().length > 0) )
						{
							$('#editSkypeName').val(null);
						}
						
						$('#editEmp').submit();
					}
				}
		);
	}
);