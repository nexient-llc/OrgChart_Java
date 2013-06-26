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
		$(this).css('background', '#DDD')
	}).mouseleave(function() {
		$(this).find('.edit_icon').css('background', 'none');
		$(this).css('background', '#EEE')
	});

	$('.click_row').click(function() {
		$('#addEntity').hide();
		thisname = $(this).find('.deptname').text();
		thisparent = $(this).find('.deptparentid').text();
		thisid = $(this).attr('id');
		$('#delete_legend').text('Delete ' + thisname);
		$('#dept_put_id, #dept_del_id').val(thisid);
		$('#dept_put_name, #dept_del_name').val(thisname);
		$('#dept_put_parent_id').val(thisparent);
		$('#crudform, #editEntity').show("fast", "linear");
		$('#toggleCrudBtn').text("Cancel");
	});

});
