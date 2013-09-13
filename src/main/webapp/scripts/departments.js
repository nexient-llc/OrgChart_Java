$(document).ready(function() {
	var lastClickedButton;
	var lastEntity;
	
	$('#addBtn-container').css('width', $('#t1').width());

	$('#addBtn').click(function() {
		$('#addBtn-container').fadeToggle("fast", "linear", function() {
			$('#addEntity').fadeToggle("fast", "linear");
		});
	});
	
	$("#CreateEntity").submit(function(){
		var deptParId = $('parent_id_Create').attr('value');
		
		if(deptParId == "..."){
			$('CreateParentId').attr({
				'name': 'No Parent Specified'
			});
		} else {
			$('CreateParentId').attr({
				'value': deptParId
			});
		}
	});

	// controls the edit form and associated edit buttons
	$(".editBtn").click(function() {
		var id = this.val();
		$("#editEntity").fadeToggle("fast", "linear");
		$('#EditId').attr({
			'value': id
		});
	}); // end of editBtn click
	
	$("#EditEntity").submit(function(){
		var deptParId = $("#parent_id_Edit").val();
		
		if(deptParId == "..."){
			$('#EditParentId').attr('value', null);
		} else {
			$('#EditParentId').attr('value', deptParId);
		}
	});

	// controls deleting of item
	$(".removeBtn").click(function() {
		var deptToDeleteId = $(this).val();

		$("#removeDepartmentId").attr('value', deptToDeleteId);
		
		$('#removeEntity').fadeToggle("fast", "linear");
	});
}); //end of document


