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
	$('.editBtn').click(function(){
		jobId = $(this).attr('value')
		alert(jobId);
		$('.editBtn').hide();
		$('#editEntity').show();
		$('#jobId').attr({
			"name": jobId
		});
		
		
	});
	
	
	
	// Cancel Entity: Show Button/Hide Form
	$("#cancelEditBtn").click(function(){
		$('#editEntity').hide();
		$('.editBtn').fadeToggle("slow","linear");
			
	});
});