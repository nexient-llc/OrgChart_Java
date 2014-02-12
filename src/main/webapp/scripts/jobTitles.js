$(document).ready(function() {
	$('#addBtn-container').css('width', $('#t1').width());

	$('#addBtn').click(function() {
		$('#addBtn-container').fadeToggle("fast", "linear", function() {
			$('#editEntity').hide();
			$('#addEntity').fadeToggle("fast", "linear");

		});
	});
	$('.cancelBtn').click(function() {
		$('#addEntity').hide();
		$('#editEntity').hide();
		$('#addBtn-container').fadeToggle("fast", "linear");
	});
	$('.editBtn').click(function() {
		$('#editEntity').fadeToggle("fast", "linear");
		$('#addBtn-container').hide();

		$('#t1 tr').click(function() {
			var jobTitleId = this.id;
			var jobTitleName = $(this).children()[0].innerHTML;
			$('#editJobtitleName').val(jobTitleName);
			$('#editJobTitleId').val(jobTitleId);
			$('#editJobTitleId').hide();

		});

	});
});
