$(document).ready(function() {
	$('#addBtn-container').css('width', $('#t1').width());

	$('#addBtn').click(function() {
		$('#addBtn-container').fadeToggle("fast", "linear", function() {
			$('#addEntity').fadeToggle("fast", "linear");
		});
	});
	
	$('#cancelBtn').click(function() {
		$('#addBtn-container').fadeToggle("fast", "linear", function() {
			$('#addEntity').fadeToggle("fast", "linear");
		});
	});
	
	$('.click_row').mouseenter(function() {
		$(this).find('.edit_icon').css('background', 'url(../images/editicon.png) no-repeat');
		$(this).css('background', '#EEE')
	}).mouseout(function() {
		$(this).find('.edit_icon').css('background', 'none');
		$(this).css('background', '#FFF')
	});

	$('.click_row').click(function() {
		thisname = $(this).find('.deptname').text();
		$('#dept_input').val(thisname);
		$('#addBtn-container').fadeToggle("fast", "linear", function() {
			$('#editEntity').fadeToggle("fast", "linear");
		});
	});
	
});
