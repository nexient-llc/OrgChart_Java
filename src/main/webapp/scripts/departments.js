$(document).ready(function() {
	var lastClickedButton;
	$('#addBtn-container').css('width', $('#t1').width());

	$('#addBtn').click(function() {
		$('#addBtn-container').fadeToggle("fast", "linear", function() {
			$('#addEntity').fadeToggle("fast", "linear");
		});
	});

	// controls the edit form and associated edit buttons
	$("[id^=editBtn]").click(function() {

		var id = $(this).attr('value');
		
		
		
		$('#editId').attr({
			'value' : id
		}); // end of EditId

		if (lastClickedButton != null) {
			$(lastClickedButton).fadeToggle("fast", "linear");
		} else {
			$('#editEntity').fadeToggle('2000', "linear");
		} // end of if

		lastClickedButton = this;
		$(this).fadeToggle("fast", "linear");
	}); // end of editBtn click
	
	$('#editDepartmentForm').submit(function() {
		var parentSelect = $('#parent_id_edit').val();

		if(parentSelect != "..."){
			$('#editParentId').attr({
				'value': parentSelect
			}); // end of ParentIdEdit
		}// end of if

		if(parentSelect == "..."){
			$('#editParentId').attr({
				'name': 'noParent'
			}); // end of parentIdEdit
		} // end of if
	});

	
	/*
	 * // Delete Form $('.deleteBtn').click(function(){
	 * $('#deleteEntity').slideToggle('4000', "swing");
	 *  // Entity To Delete var deptToDeleteId = $(this).attr('value');
	 *  // Request All Parent ID's var requestDelete = $.ajax({ url:
	 * 'findAllParentId', type: 'GET' });
	 *  // Add Id to delete to Form $('#deptDeleteId').attr({ 'value':
	 * deptToDeleteId });
	 *  // Validation that Entity can properly be removed
	 * $('#deleteForm').unbind().submit(function(){ var canDelete = true;
	 *  // Check ID against All Parent ID's // Cannot delete an ID that matches
	 * a parent requestDelete.done(function(data){ parentId = $.parseJSON(data)
	 * $.each(parentId, function(i,l){ if(deptToDeleteId == l){ canDelete =
	 * false; return false; } }) }) if(!canDelete){ alert('Cannot Delete a
	 * Parent Department') $('#deleteEntity').hide(); return false; } });
	 * 
	 * });
	 */

	// controls deleting of item
	$('#removeBtn').click(function() {

		$('#removeEntity').fadeToggle("fast", "linear");
		var deptToDeleteId = $(this).attr('value');

		$('#removeDepartmentId').attr({
			'value' : deptToDeleteId
		});
	});

}); //end of document
