$(document).ready(function() {
	$('#addBtn-container').css('width', $('#t1').width());

	$('#addBtn').click(function() {
		$('#addBtn-container').fadeToggle("fast", "linear", function() {
			$('#Entity').fadeToggle("fast", "linear");
			$('#Add').show();
			$('#filterBtn').hide();
			$('#formMethod').attr({
				value: "post"
			});
		});
	});
	
	$('.editBtn').click(function(){
		$('#addBtn-container').hide();
		$('#Entity').fadeToggle("fast", "linear");
		$('#Edit').show();
		
		var entityId =  $(this).attr('value')
		
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
		
		var deleteId = $(this).attr('value')
		
		$('#deleteInput').attr({
			value: deleteId
		});
		
	});
	
	
	$("#filterBtn").click(function(){
		$('#Filter').show();
		$('#addBtn-container').hide();
		$('#filterBtn').hide();
	});
	
	
	
	$('#resetFilter').click(function(){
		$('#firstNameInput').attr({
			value: ""
		});
		
		$('#lastNameInput').attr({
			value: ""
		});
	});
	
	
	// Could most likely just separate buttons/forms in divs and just show hide divs... 
	// Should have thought of that sooner...
	$('.cancelBtn').click(function(){
		$('#Add').hide();
		$('#Edit').hide();
		$('#Entity').hide();
		$('#deleteEntity').hide();
		$('#Filter').hide();
		$('#filterBtn').show();
		$('#deleteBtn').show();
		$('#addBtn-container').fadeToggle("fast", "linear");
		$('#addBtn').show();
		$('#editBtn').show();
	});
	
});