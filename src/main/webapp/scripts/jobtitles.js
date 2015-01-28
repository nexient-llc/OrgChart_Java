$(document).ready(function() {
	$('#addBtn-container').css('width', $('#t1').width());

	$('.editButton').click(function() {
	     dialogEdit.dialog( "open" );
	      
		$.ajax({
			   url : "job/" + $(this).val(),
			   type : "GET"
		}).done(function(data){
			
			var form = $.parseJSON(data);
			
			$('#jobTitleName').val(form.name);
			$('#jobTitleId').val(form.id);
		
		});
	});

    var dialogMake, dialogEdit

    
    dialogMake = $( "#dialog-form-make" ).dialog({
      autoOpen: false,
      height: 200,
      width: 400,
      modal: true
      });
 
    dialogEdit = $( "#dialog-form-edit" ).dialog({
        autoOpen: false,
        height: 200,
        width: 400,
        modal: true
        });
    
    $( "#addBtn" ).click(function() {
      dialogMake.dialog( "open" );
    });
    
	$("#resetEditBtn").click(function() {		
		dialogEdit.dialog("close");			
	})
	
	$("#resetMakeBtn").click(function() {		
		dialogMake.dialog("close");			
	})
});