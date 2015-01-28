$(document).ready(function() {
	$('#addBtn-container').css('width', $('#t1').width());

	$('#addBtn').click(function() {
		$('#addBtn-container').fadeToggle("fast", "linear", function() {
			$('#addEntity-container').fadeToggle("fast", "linear");
		});
	});
	
	$('#cancelAddButton').click(function() {
		$('#addEntity-container').fadeToggle("fast", "linear", function() {
			$('#addBtn-container').fadeToggle("fast", "linear");
		});
	});
	
	$('.editButton').click(function() {
		
		$.ajax({
			   url : "title/" + $(this).val(),
			   type : "GET"
		}).done(function(data){
			
			var form = $.parseJSON(data);
			
			$('#jobTitleName').val(form.name);
			$('#jobTitleId').val(form.id);
						
			$('#jobTitlesTable-container').fadeToggle("fast", "linear");
			$('#editEntity-container').fadeToggle("fast", "linear");
			
		});
	});
	
	$('#cancelEditButton').click(function() {
		$('#editEntity-container').fadeToggle("fast", "linear", function() {
			$('#jobTitlesTable-container').fadeToggle("fast", "linear");
		});
	});
	
	
	$('#removeButton').click(function() {
		var jobTitleId = $('#jobTitleId').val();
		$.ajax({
			   url : "delete/title/" + jobTitleId,
			   type : "DELETE"
		}).done(function(){
			
			$('#tableRow'+jobTitleId).remove();
			$('#editEntity-container').fadeToggle("fast", "linear", function() {
				$('#jobTitlesTable-container').fadeToggle("fast", "linear");
			});
		});
		
	});
	
});