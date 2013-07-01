$(document).ready(function() {
	// CSS Properties
	$('#addBtn-container').css('width', $('#t1').width()); 
	
	// Add Entity: Show Form/Hide Button
	$('#addBtn').click(function() {
		$('#addBtn-container').fadeToggle("fast", "linear", function() {
			$('#addEntity').fadeToggle("fast", "linear");
		});
			
	});
	
	// Cancel Entity: Hide From/Show Button
	$("#cancelBtn").click(function(){
		$('#addEntity').hide();
		$('#addBtn-container').fadeToggle("slow","linear");
			
	});
	
	//Edit Entity: Show Form/Hide Button/Set Up Form
	//Need to work on form auto completion
	$('.editBtn').click(function(){
		job = $(this).attr('value')
		$('.editBtn').hide();
		$('#editEntity').show();
		$('#jobId').attr({
			"value": job
		});	
		
	});
	
	$(".deleteBtn").click(function(){
		job = $(this).attr('value')
		$('.deleteBtn').hide();
		$('#deleteEntity').show();
		$('#jobIdDelete').attr({
			"value": job
		});
	});
	
	// Cancel Entity: Show Button/Hide Form
	$(".cancelEditBtn").click(function(){
		$('#deleteEntity').hide();
		$('.deleteBtn').fadeToggle("slow", "linear");
			
	});
	
	$('#cancelEditBtn').click(function(){
		$('#editEntity').hide();
		$('.editBtn').show();
	});
});