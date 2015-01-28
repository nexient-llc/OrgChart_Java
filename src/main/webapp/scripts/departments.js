$(document).ready(function() {
	$('#addBtn-container').css('width', $('#t1').width());

	$('.editButton').click(function() {
	     dialogEdit.dialog( "open" );
	      
		$.ajax({
			   url : "dept/" + $(this).val(),
			   type : "GET"
		}).done(function(data){
			
			var form = $.parseJSON(data);
			
			$('#departmentName').val(form.name);
			$('#departmentId').val(form.id);
			
			if (form.parentDepartment) {
				$('#parentDepartment').val(form.parentDepartment.id);
			} else {
			    $('#parentDepartment').val("");
			}
			
			select = document.getElementById("parentDepartment");
			parentId = document.getElementById("departmentId").value;
			options = select.options;
			for(var i = 0; i < options.length; i++ ){
				if(options[i].value == parentId){
					options[i].disabled=true;
					break;
				}
			}
		});	
	});
	var select, parentId, options
    var dialogMake, dialogEdit

    
    dialogMake = $( "#dialog-form-make" ).dialog({
      autoOpen: false,
      height: 210,
      width: 420,
      modal: true
      });
 
    dialogEdit = $( "#dialog-form-edit" ).dialog({
        autoOpen: false,
        height:217,
        width: 475,
        modal: true
        });
    
    $( "#addBtn" ).click(function() {
      dialogMake.dialog( "open" );
    });
    
	$('#resetEditBtn').click(function() {
		
		dialogEdit.dialog("close");
		
		for(var i = 0; i < options.length; i++ ){
			if(options[i].value == parentId){
				options[i].disabled=false;
				break;
			}
		}			
	})
	
		$('#resetMakeBtn').click(function() {
		
		dialogMake.dialog("close");			
	})
});
