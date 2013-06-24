$(document).ready(function() {
			
	$('#addEntity, #editEntity, #crudform').hide();

	$('#toggleCrudBtn').click(function() {
		if ($('#editEntity').is(":visible")) {
			$('#editEntity, #crudform').hide("fast", "linear");
			$('#toggleCrudBtn').text("Add");
		} else if ($('#addEntity').is(":visible")) {
			$('#addEntity, #crudform').hide("fast", "linear");
			$('#toggleCrudBtn').text("Add");
		} else {
			$('#crudform, #addEntity').show('fast', 'linear');
			$('#toggleCrudBtn').text("Cancel");
		}
	});
	
	$('.click_row').mouseenter(function() {
		$(this).find('.edit_icon').css('background', 'url(../images/editicon.png) no-repeat');
		$(this).css('background', '#EEE')
	}).mouseleave(function() {
		$(this).find('.edit_icon').css('background', 'none');
		$(this).css('background', '#FFF')
	});

	$('.click_row').click(function() {
		$('#addBtn-container, #addEntity').hide();
		thisname = $(this).find('.deptname').text();
		$('#delete_legend').text('Delete ' + thisname);
		$('#dept_input').val(thisname);
		$('#crudform, #editEntity').show("fast", "linear");
		$('#toggleCrudBtn').text("Cancel");
	});
	
});
