$(document).ready(function() {
	
	$('#addBtn-container').css('width', $('#t1').width());
	
	$('.startBtn').click(function(){
		button = $(this).attr('id');
		
		if(button == 'addBtn'){
			$('#addBtn').hide();
			$('#addEntity').slideToggle('4000', "swing");
			$('#newJob').submit(function(){
				if(!$('input').val()){
					alert("Not Valid; Please enter Name and Description")
					return false;
				}
			});
		}
		
		if(button == 'editBtn'){
			$('#editBtn').hide();
			$('#editEntity').slideToggle('4000', "swing");
			jobId = $(this).attr('value')
			$('#jobId').attr({
				"value": jobId
			});
			
			$('#editJob').submit(function(){
				if($('#editName').attr('value') == ''){
					alert("Not Valid; Please enter Name");
					return false;
				}
				
				if($('#editDescription').attr('value') == ''){
					alert('Not valid; Please enter a Description');
					return false;
				}
			});
			
		}
		
		if(button == 'deleteBtn'){
			$('#deleteBtn').hide();
			$('#deleteEntity').slideToggle('4000', "swing");
			jobId = $(this).attr('value')
			$('#jobIdDelete').attr({
				"value": jobId
			})
		}
			
	});
	
	$('.cancel').click(function(){
		
		if($('#cancelAddBtn')){
			$('#addEntity').hide();
			$('#addBtn').show();
		}
		
		if($('#cancelDeleteBtn')){
			$('#deleteEntity').hide();
			$('.startBtn').show();
		}
		
		if($('#cancelEditBtn')){
			$('#editEntity').hide();
			$('.startBtn').show();
		}
		
	});
});
	