$(document).ready(function() {
	$('#addBtn-container').css('width', $('#t1').width());

	$('#addBtn').click(function() {
		$('#addBtn-container').fadeToggle("fast", "linear", function() {
			$('#Entity').fadeToggle("fast", "linear");
			$('#Add').show();
			
			$('#formMethod').attr({
				value: "post"
			});
		});
	});
	
	$('.editBtn').click(function(){
		$('#addBtn-container').hide();
		$('#Entity').fadeToggle("fast", "linear");
		$('#Edit').show();
		
		var entityId = $('#indivEdit').val();
		
		$('#editId').attr({
			name: "id",
			value: entityId
		});
		
		$('#formMethod').attr({
			value: "put"
		});
	});
	
	$('.deleteBtn').click(function(){
		
		$('#deleteEntity').show();
		
		var deleteId = $('#indivDelete').val();
		
		$('#deleteInput').attr({
			value: deleteId
		});
		
	});
	
	$('.cancelBtn').click(function(){
		$('#Add').hide();
		$('#Edit').hide();
		$('#Entity').hide();
		$('#deleteEntity').hide();
		$('#deleteBtn').show();
		$('#addBtn-container').fadeToggle("fast", "linear");
		$('#addBtn').show();
		$('#editBtn').show();
	});
	
});