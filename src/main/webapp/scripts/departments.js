$(document).ready(function() {
	
	// CSS Properties
	$('#addBtn-container').css('width', $('#t1').width());
	
	// Add Department Form 
	$('#addBtn').click(function() {
		$('#addBtn').hide();
		$('#Entity').slideToggle('4000', "swing");
		
		// Submit Validation
		$('#departmentForm').submit(function(){
			
			// '...' value of no parent
			if($('select').val() != '...'){
				
				var parentDepartment = $('select').val();
				$('#parentId').attr({
				'value': parentDepartment
				});
			}
			// Change name of name field
			if($('select').val() == '...') {
				
				$('#parentId').attr({
					'name': 'noParent'
				});
			// Form Field Validation
			if(!$('#deptName').val()){
				alert('Please enter a name!')
				return false;
			}
			
			}
		});
		
	});
	
	// Edit Form
	$('.editBtn').click(function(){
		$('#addBtn').hide();
		// Button has Entity Id value
		var id = $(this).attr('value')
		$('#deptEditId').attr({
			'value': id
		});
		
		$('#editEntity').slideToggle('4000', "swing");
		
		// Submit Validation
		// Same Logic as Add Form
		$('#editDepartmentForm').submit(function(){
			
			var selectEdit = $('#selectEdit').val();
			
			if(selectEdit != '...'){
				var editparentDepartment = selectEdit;
				$('#parentEditId').attr({
				'value': editparentDepartment
				});
			}
			
			if(selectEdit == '...') {
				$('#parentEditId').attr({
					'name': 'noParent'
				});
			}
			
			if(!$('#deptEditName').val()){
				alert('Please enter a Name')
				return false;
			}
			
		});
		
	});
	
	// Delete Form
	$('.deleteBtn').click(function(){
		$('#deleteEntity').slideToggle('4000', "swing");
		
		// Entity To Delete
		var deptToDeleteId = $(this).attr('value');
		
		// Request All Parent ID's
		var requestDelete = $.ajax({
			url: 'findAllParentId',
			type: 'GET'
		});
		
		// Add Id to delete to Form
		$('#deptDeleteId').attr({
			'value': deptToDeleteId
		});
		
		// Validation that Entity can properly be removed
		$('#deleteForm').unbind().submit(function(){
			var canDelete = true;
			
			// Check ID against All Parent ID's
			// Cannot delete an ID that matches a parent
			requestDelete.done(function(data){
				parentId = $.parseJSON(data)
				$.each(parentId, function(i,l){
					if(deptToDeleteId == l){
						canDelete = false;
						return false;
					}
				})
			})
			if(!canDelete){
				alert('Cannot Delete a Parent Department')
				$('#deleteEntity').hide();
				return false;
			}
		});
		
	});
	
	// Cancel Button
	$('.reset').click(function(){
			$('#addBtn').show();
			$('#Entity').hide();
			$('#editEntity').hide();
			$('#deleteEntity').hide();
			
	});
	
});
