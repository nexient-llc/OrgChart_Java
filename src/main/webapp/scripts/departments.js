$(document).ready(function() {
	
	// CSS Properties
	$('#addBtn-container').css('width', $('#t1').width());
	
	// Add Department Form 
	$('#addBtn').click(function() {
		$('#addBtn-container').fadeToggle("fast", "linear", function() {
			$('#Entity').fadeToggle("fast", "linear");
		});
		
		// Submit Validation
		// IF option ... No Parent
			// Change attribute name so Parent not submitted.
		$('#departmentForm').submit(function(){
			
			if($('select').val() != '...'){
				
				 var parentDepartment = $('select').val();
				$('#parentId').attr({
				'value': parentDepartment
				});
			}
			
			if($('select').val() == '...') {
				
				$('#parentId').attr({
					'name': 'noParent'
				});
				
			if(!$('#deptName').val()){
				alert('Please enter a name!')
				return false;
			}
			
			}
		});
		
	});
	
	// Edit Form
	$('.editBtn').click(function(){
		
		var id = $(this).attr('value')
		$('#deptEditId').attr({
			'value': id
		});
		
		$('#editEntity').show();
		
		// Submit Validation
		// IF option ... No Parent
			// Change attribute name so Parent not submitted.
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
		$('#deleteEntity').show();
		
		var deptToDeleteId = $(this).attr('value');
		
		$('#deptDeleteId').attr({
			'value': deptToDeleteId
		});
		
		$('#deleteForm').submit(function(){
			
			// IF( dept id == a parent id)
			// Don't submit
			
		})
	});
	
	// Cancel Button
	$('.reset').click(function(){
			$('#addBtn-container').show();
			$('#Entity').hide();
			$('#editEntity').hide();
			$('#deleteEntity').hide();
			
	});
	
});
