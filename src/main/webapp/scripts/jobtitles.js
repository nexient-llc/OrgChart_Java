$(document).ready(function() {
	$('#addBtn-container').css('width', $('#t1').width());

	$('#addBtn').click(function() {
		$('#addBtn-container').fadeToggle("fast", "linear", function() {
			$('#addEntity').fadeToggle("fast", "linear");
		});
			
	});
	
	$(".cancelBtn").click(function(){
		$('#addEntity').hide();
		$('#addBtn-container').fadeToggle("slow","linear");
			
	});
	
	$('.editBtn').click(function(){
		$('#editBtn').hide();
		$('#editEntity').show();
	});
	
	$("#cancelEditBtn").click(function(){
		$('#editEntity').hide();
		$('#editBtn').fadeToggle("slow","linear");
			
	});
});