 $(document).ready(function() {
		
	    var dialogMake, dialogEdit

	    
	    dialogMake = $( "#dialog-form-make" ).dialog({
	      autoOpen: false,
	      height: 450,
	      width: 400,
	      modal: true
	      });
	 
	    dialogEdit = $( "#dialog-form-edit" ).dialog({
	        autoOpen: false,
	        height: 450,
	        width: 400,
	        modal: true
	        });
	    
	 
	$('#addBtn-container').css('width', $('#t1').width());

	$('#searchBtn').click(function() {
			$('#form-filter').fadeToggle("fast", "linear");
		});
	
	
	$('.editButton').click(function() {
	     dialogEdit.dialog( "open" );
	      
		$.ajax({
			   url : "emp/" + $(this).val(),
			   type : "GET"
		}).done(function(data){
			
			var form = $.parseJSON(data);
			
			$('#employeeId').val(form.id);
			$('#empFirstName').val(form.firstName);
			$('#empLastName').val(form.lastName);
			$('#empMiddleInitial').val(form.middleInitial);
			$('#empEmail').val(form.email);
			$('#empSkypeName').val(form.skypeName);
			$('#empDepartmentId').val(form.department.id);
			$('#empJobTitleId').val(form.jobTitle.id);			
		});
	});

    $( "#addBtn" ).click(function() {
      dialogMake.dialog( "open" );
    });
	
    $( "#cancelBtn" ).click(function() {
    	$('#form-filter').fadeToggle("fast", "linear");

      });
    
	// This function does an Ajax call to autocomplete the name field
	$("input#autocompleteText").autocomplete({
		source: function(request, response) {
			$.ajax({
				delay: 0,
				type: "GET",
				url: "suggestions/" + $("#autocompleteText").val(),
				dataType: "text",
				success: function(data) {
					var suggestions = data.split(",");
					suggestions.pop();  // remove the empty element at the end
					response(suggestions);
				}
			});
		}
	});
	
		$("#resetEditBtn").click(function() {
		
		dialogEdit.dialog("close");			
	})
	
		$("#resetMakeBtn").click(function() {
		
		dialogMake.dialog("close");			
	})
});