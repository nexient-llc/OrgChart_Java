$(document).ready(function() {
	
	$('#addBtn-container').css('width', $('#t1').width());
	
	$('#addBtn').click(function() {
		$('#addBtn-container').fadeToggle("fast", "linear", function() {
			$('#Entity').fadeToggle("fast", "linear");
		});
	
		$('#departmentForm').submit(function(){
			
			if($('select').val() != '...'){
				
				parentDepartment = $('select').val();
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

	$('.reset').click(function(){
			$('#addBtn-container').show();
			$('#Entity').hide();
			$('#editEntity').hide();
			$('#deleteEntity').hide();
			
	});
	
	$('.deleteBtn').click(function(){
		
		$('#deleteEntity').show();
		
		deptToDeleteId = $(this).attr('value');
		$('#deptDeleteId').attr({
			'value': deptToDeleteId
		});
	});
	
	$('.editBtn').click(function(){
		
		id = $(this).attr('value')
		$('#deptEditId').attr({
			'value': id
		});
		
		$('#editEntity').show();
		
		$('#editDepartmentForm').submit(function(){
			
			var selectEdit = $('#selectEdit').val();
			
			if(selectEdit != '...'){
				editparentDepartment = selectEdit;
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
	
});
