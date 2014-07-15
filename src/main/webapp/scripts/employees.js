$(document).ready(function() {
	$('#addBtn-container').css('width', $('#t1').width());

	$('#addBtn').click(function() {
		$('#addBtn-container').fadeToggle("fast", "linear", function() {
			$('#addEntity').slideToggle('3000', "swing");;
		});
	});
	$('#searchBtn').click(function() {
		$('#searchBtn-container').fadeToggle("fast", "linear", function() {
		});
	});
	$("#cancel").click(function(){
		$("#addEntity").slideToggle('3000', "swing");;
		$('#addBtn-container').fadeToggle("fast", "linear");
	});
	
	$('#searchBtn').click(function() {
		$('#searchBtn-container').fadeToggle("fast", "linear", function() {
			$('#searchEntity').slideToggle('3000', "swing");;
		});
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
		
		$.ajax({
	        type: 'get',
	        url: 'emp',
	        data: {id:query},
	        success: function (data) {
	        	
	        	var employees = $.parseJSON(data);
	        	
	        	$('#empId').val(employees['id']);
	        	$('#first-edit').val(employees.firstName);
	        	$('#empMid-edit').val(employees['middleInitial']);
	        	$('#last-edit').val(employees['lastName']);
	        	$('#empEmail-edit').val(employees['email']);
	        	$('#empSkype-edit').val(employees['skypeName']);
	        	$('#depart-edit').val(employees.department['id']);
	        	$('#job-edit').val(employees.jobTitle['id']);
	        
	        }
	    });
		$('#editEntity').slideToggle('3000', "swing");
	});
	$("#cancelEdit").click(function(){
		$(".editClass").slideToggle('3000', "swing");
	});
	$("#searchEdit").click(function(){
		$(".searchClass").slideToggle('3000', "swing");
	});
	
});

