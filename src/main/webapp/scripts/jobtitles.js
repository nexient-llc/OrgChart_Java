$(document).ready(function() {
	// CSS Properties
	$('#addBtn-container').css('width', $('#t1').width()); 
	
////////////////////////////////////////Add Entity//////////////////////////////////
	$('#addBtn').click(function() {
		$('#addBtn-container').fadeToggle("fast", "linear", function() {
			$('#addEntity').fadeToggle("fast", "linear");
			
		});
			
	});
	
	$('#addBtn').click(function(){
		$('#newJob').submit(function(){
			if(!$('input').val()){
				alert("Not Valid; Please enter Name and Description")
				return false;
			}
		});
	});
			
////////////////////////////////////////Edit Entity//////////////////////////////////

	$('.editBtn').click(function(){
		job = $(this).attr('value')
		$('.editBtn').hide();
		$('#editEntity').show();
		$('#jobId').attr({
			"value": job
		});
		
		// Edit Form Validation
		$('#editJob').submit(function(){
			if(!$('#jobName').val()){
				alert("Not Valid; Please enter Name and Description")
				return false;
			}
		});
		
	});
	
////////////////////////////////////////Delete Entity/////////////////////////////////////////
	// Delete Entity
	$(".deleteBtn").click(function(){
		job = $(this).attr('value')
		$('.deleteBtn').hide();
		$('#deleteEntity').show();
		$('#jobIdDelete').attr({
			"value": job
		});
	});

	
//////////////////////////////////////// Cancel /////////////////////////////////////////
	
	$('.cancel').click(function(){
		
		if($('#cancelAddBtn')){
			$('#addEntity').hide();
			$('#addBtn-container').show();
		}
		
		if($('#cancelDeleteBtn')){
			$('#deleteEntity').hide();
			$('.deleteBtn').show();
		}
		
		if($('#cancelEditBtn')){
			$('#editEntity').hide();
			$('.editBtn').show();
		}
		
	});
});
	