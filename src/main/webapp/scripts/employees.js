$(document).ready(function() {
	
	$('#addBtn-container').css('width', $('#t1').width());
	
	// Add Employee Form/Buttons
	$('#addBtn').click(function() {
		$('#addBtn-container').fadeToggle("fast", "linear", function() {
			$('#Entity').fadeToggle("fast", "linear");
			$('#Add').show();
			$('#Filter').fadeToggle("fast", "linear");
			$('#formMethod').attr({
				value: "post"
			});
		});
	});
	
	// Edit Employee Form/Buttons
	$('.editBtn').click(function(){
		$('#addBtn-container').hide();
		$('#Entity').fadeToggle("fast", "linear");
		$('#Edit').show();
		$('#Filter').fadeToggle("fast", "linear");
		
		var entityId =  $(this).attr('value')
		
		$('#editId').attr({
			name: "id",
			value: entityId
		});
		
		$('#formMethod').attr({
			value: "put"
		});
	});
	
	// Delete Employee Form/Buttons
	$('.deleteBtn').click(function(){
		$('#addBtn-container').fadeToggle("fast", "linear");
		$('#Filter').fadeToggle("fast", "linear");
		$('#deleteEntity').show();
		
		var deleteId = $(this).attr('value')
		
		$('#deleteInput').attr({
			value: deleteId
		});
		
	});
	
	// Resets Filter Name Values
	$('#resetFilter').click(function(){
		$('#firstNameInput').attr({
			value: ""
		});
		
		$('#lastNameInput').attr({
			value: ""
		});
	});
	
	// Cancel Button
	// Hide/Shows relevant content
	$('.cancelBtn').click(function(){
		$('#Add').hide();
		$('#Edit').hide();
		$('#Entity').hide();
		$('#deleteEntity').hide();
		$('#deleteBtn').show();
		$('#addBtn-container').fadeToggle("fast", "linear");
		$('#addBtn').show();
		$('#editBtn').show();
		$('#Filter').fadeToggle("fast", "linear")
	});
	
	// Filter Auto Complete
	// Sends Request of All Employees in JSON
	var requestEmployeeList = $.ajax({
		url: 'findAllEmployees',
		type: 'GET',
	});
	
	// jQuery Auto complete requires elements to be in Array.
	var employees = requestEmployeeList.done(function(data){
		empData = $.parseJSON(data);
		var firstNameList = new Array();
		var lastNameList = new Array();
		
		$.each(empData, function(i, l){
			
				if(($.inArray(l.firstName, firstNameList)) == -1){
					firstNameList.push(l.firstName);
			
				}
				if(($.inArray(l.lastName, lastNameList)) == -1){
					lastNameList.push(l.lastName);
				}
			});
		
		$('#firstNameInput').autocomplete({
			source: firstNameList
		})
		
		$('#lastNameInput').autocomplete({
			source: lastNameList
		})
	});	
});