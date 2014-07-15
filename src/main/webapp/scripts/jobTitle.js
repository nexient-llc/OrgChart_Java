$(document).ready(function() {
	$('#addBtn-container').css('width', $('#t1').width());

	$('#addBtn').click(function() {
		$('#addBtn-container').fadeToggle("fast", "linear", function() {
			$('#addEntity').slideToggle('3000', "swing");;
		});
	});
	$("#cancel").click(function(){

		$('#addEntity').slideToggle('3000', "swing");;
		$('#addBtn-container').fadeToggle("slow", "linear");
	});
	
	$('.editButton').click(function() {
		var jobName = $(this).parents('.jobTitleClass').find('.JobTitleName').text();
		var jobID   = $(this).attr('value');
		$('#job').val(jobName);
		$('#titleId').val(jobID);
		$('#editEntity').slideToggle('3000', "swing");;
	});
	$("#cancelEdit").click(function(){
		$(".editClass").slideToggle('3000', "swing");
	});
});