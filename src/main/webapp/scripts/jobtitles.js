$(document).ready(function() {
	$('#addBtn-container').css('width', $('#t1').width());
	$('#editBtn-container').css('width', $('#t1').width());

	$('#addBtn').click(function() {
		$('#addBtn-container').fadeToggle("fast", "linear", function() {
			$('#addEntity').fadeToggle("fast", "linear");
		});
	});
	
	$('#cancelBtn').click(function(evnt) {
		evnt.preventDefault();
		$('#addEntity').fadeToggle("fast", "linear", function() {
			$('#addBtn-container').fadeToggle("fast", "linear");
		});
	});
	
	$('.editBtn').click(function(evnt) {
		var num = $(this).val();
		evnt.preventDefault();
		$('#ViewJobs'+num).fadeToggle("fast", "linear", function() {
			$('#EditJobs'+num).fadeToggle("fast", "linear");
		});
	});
	
	$('.deleteBtn').click(function(evnt){
		var num = $(this).val();
		$.ajax({
			url : "jobs",
			type : "POST",
			data : {
				_method : "delete",
				id : num
			},
			success: function(response){
				window.location.href="jobs";
			},	
		});
	});
	
	$('.cancelEditBtn').click(function(evnt){
		var num = $(this).val();
		$('#EditJobs'+num).fadeToggle("fast", "linear", function(){
			$('#ViewJobs'+num).fadeToggle("fast", "linear");
		});
	});
	
$('.saveBtn').click(function(evnt) {
		
		var num = $(this).val();
		$.ajax({
			url : "jobs",
			type: "POST",
			data : {
				_method : "put",
				id : num ,
				name : $('#jobName'+num).val() ,
				description : $('#jobDescript'+num).val()
			},
			
			success: function(response){
				window.location.href="jobs";
			},	
		});
		
//		$('#EditDepts'+num).fadeToggle("fast", "linear", function(){
//			$('#ViewDepts'+num).fadeToggle("fast", "linear");
//		});
	});
	
});