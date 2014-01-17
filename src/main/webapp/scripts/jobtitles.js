$(document).ready(function() {
	$('#addBtn-container').css('width', $('#t1').width());

	$('#addBtn').click(function() {
		$('#addBtn-container').fadeToggle("fast", "linear", function() {
			$('#addEntity').fadeToggle("fast", "linear");
		});
	});

	$('.editBtn').click(function(evnt) {
		var num = $(this).val();
		evnt.preventDefault();
		$('#ViewJobs' + num).fadeToggle("fast", "linear", function() {
			$('#EditJobs' + num).fadeToggle("fast", "linear");
		});
	});

	$('.saveBtn').click(function(evnt) {
		var num = $(this).val();
		$.ajax({
			url : "jobs",
			type : "POST",
			data : {
				_method : "put",
				id : num,
				name : $('#jobName' + num).val()
			},
			success : function(response) {
				window.location.href = "jobs";
			},
		});
	});
	
	$('.cancelBtn').click(function(evnt){
		var num = $(this).val();
		evnt.preventDefault();
		$('#EditJobs' + num).fadeToggle("fast", "linear", function() {
			$('#ViewJobs' + num).fadeToggle("fast", "linear");
		});
	});
});
