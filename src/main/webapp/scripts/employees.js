$(document).ready(function() {
	
	$('#addBtn-container').css('width', $('#t1').width());

	$('#addBtn').click(function() {
		$('#addBtn-container').fadeToggle("fast", "linear", function() {
			$('#Entity').fadeToggle("fast", "linear");
			$('#Add').show();
			$('#filterBtn').hide();
			$('#formMethod').attr({
				value: "post"
			});
		});
	});
	
	$('.editBtn').click(function(){
		$('#addBtn-container').hide();
		$('#Entity').fadeToggle("fast", "linear");
		$('#Edit').show();
		
		var entityId =  $(this).attr('value')
		
		$('#editId').attr({
			name: "id",
			value: entityId
		});
		
		$('#formMethod').attr({
			value: "put"
		});
	});
	
	$('.deleteBtn').click(function(){
		
		$('#deleteEntity').show();
		
		var deleteId = $(this).attr('value')
		
		$('#deleteInput').attr({
			value: deleteId
		});
		
	});
	
	$('#resetFilter').click(function(){
		$('#firstNameInput').attr({
			value: ""
		});
		
		$('#lastNameInput').attr({
			value: ""
		});
	});

	$('.cancelBtn').click(function(){
		$('#Add').hide();
		$('#Edit').hide();
		$('#Entity').hide();
		$('#deleteEntity').hide();
		$('#deleteBtn').show();
		$('#addBtn-container').fadeToggle("fast", "linear");
		$('#addBtn').show();
		$('#editBtn').show();
	});
	
	var requestEmployeeList = $.ajax({
		url: 'findAllEmployees',
		type: 'GET',
	});
	
	var employees = requestEmployeeList.done(function(data){
		temp = $.parseJSON(data);
		var firstNameList = new Array();
		var lastNameList = new Array();
		
		$.each(temp, function(i, l){
			
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