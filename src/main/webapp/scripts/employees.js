$(document).ready(function() {
	$('#addBtn-container').css('width', $('#t1').width());

	$('#addBtn').click(function() {
		$('#addBtn-container').fadeToggle("fast", "linear", function() {
			$('#addEntity').slideToggle('3000', "swing");
		});
	});
	$('#searchBtn').click(function() {
		$('#searchBtn-container').fadeToggle("fast", "linear", function() {
		});
	});
	$("#cancel").click(function(){
		$("#addEntity").slideToggle('3000', "swing");;
		$('#addBtn-container').fadeToggle("fast", "linear");
		$("#searchBtn-container").slideToggle('3000', "swing");
	});
	
	$('#searchBtn').click(function() {
		$('#searchBtn-container').fadeToggle("fast", "linear", function() {
			$('#searchEntity').slideToggle('3000', "swing");
		});
	});
	
	$('#searchFullName').autocomplete({
		source:function(request, response){
			$.ajax({
				type:'get',
				url:'autoComplete/' + $('#searchFullName').val(),
				success:function(data){
					
					var array =[];
					var tags = $.parseJSON(data);
					var tag;
                    for(tag in tags)
                       array.push(tags[tag].firstName + " " + tags[tag].lastName);
					response(array);
				}
			});
		}
	});

	$("#newEmployee").submit(function() {
		if($("#depart").val() == "" ||$("#job").val == "" ){
			
			return false;
		}
		else if( $("#job").val() == ""){
			
			return false;
		}
	
			
	});
	
	$('.editButton').click(function() {
		var query = $(this).attr('value');
		
		$('#addBtn-container').slideToggle('3000', "swing");
		$("#searchBtn-container").slideToggle('3000', "swing");
		
		if(query != null){
		$.ajax({
	   
			type: 'get',
	        url: 'emp',
	        data: {id:query},
	        success: function (data) {
	        	
	        	var employees = $.parseJSON(data);
	      
	        	$('#empId').val(employees.id);
	        	$('#fname').val(employees.firstName);
	        	$('#mid').val(employees.middleInitial);
	        	$('#lname').val(employees.lastName);
	        	$('#eEmail').val(employees.email);
	        	$('#empskype').val(employees.skypeName);
	        	$('#depart').val(employees.department.id);
	        	$('#job').val(employees.jobTitle.id);
	        	$('#activeBox').prop("checked",employees.isActive);
	        }
	    });
		} // end of if statement.
		$('#addEntity').slideToggle('3000', "swing");
	});

	$('.removeButton').click(function() {
		
	var query = $(this).attr('value');
	
		$.ajax({
		      type: 'get', 
		        url: 'deleteEmployee',
		        data: {id:query},
		        success: function (data) {
		   
		        	$('#emprow-'+query).remove();
		        	
		        }
		});
	});
	$("#cancelEdit").click(function(){
		$('#addBtn-container').slideToggle('3000', "swing");
		$("#searchBtn-container").slideToggle('3000', "swing");
		$(".editClass").slideToggle('3000', "swing");
	});
	
	$("#searchEdit").click(function(){
		$(".searchClass").slideToggle('3000', "swing");
	});
	
});
