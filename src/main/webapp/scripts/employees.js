$(document).ready(function() {
	$('#addBtn-container').css('width', $('#t1').width());

	$('#addBtn').click(function() {
		$('#addBtn-container').fadeToggle("fast", "linear");
		$('#addEntity').fadeToggle("fast", "linear");
	});

	$('#cancelBtn').click(function() {
		$('#addBtn-container').fadeToggle("fast", "linear");
		$('#addEntity').fadeToggle("fast", "linear");
	});

	$('.editBtnClass').click(function() {
		$('.editBtn-containerClass').fadeToggle("fast", "linear");
		var val = this.id.replace("editBtn","");
		$('#editEntity'+val).fadeToggle("fast", "linear");
	});

	$('.cancelEditBtnClass').click(function() {
		$('.editBtn-containerClass').fadeToggle("fast", "linear");
		var val = this.id.replace("cancelEditBtn","");
		$('#editEntity'+val).fadeToggle("fast", "linear");
	});

	$('.deleteBtnClass').click(function() {
		$('.editBtn-containerClass').fadeToggle("fast", "linear");
		var val = this.id.replace("deleteBtn-","");
		$('#editEntity-'+val).remove();
		$('#tableRow-'+val).remove();
		$.ajax({
			type : 'DELETE',
			url : "emp/delete/" +val,
		})
	});


	$('#filterBtn').click(function() {
		$('#filterBtn-container').fadeToggle("fast", "linear");
		$('#filterEntity').fadeToggle("fast", "linear");
	});

	$('#resetFilterBtn').click(function() {
		$('#filterBtn-container').fadeToggle("fast", "linear");
		$('#filterEntity').fadeToggle("fast", "linear");
	});

	$('#filterBox').focus().autocomplete({
		source: function(request, response) {
			$.ajax({
				dataType: "text",
				type: "GET",
				url: "searchEmployeeName/"+$('#filterBox').val(),
				success: function(data) {
					$('#filterBox').removeClass('ui-autocomplete-loading');
					if (data.length > 0)
						var output = data.split(",");
					response(output);
				}
			});
		}
	});
	
	$('#newEmployee').submit(function() {
		var success = false;
		$.ajax({
			url : "findEmployee",
			data: $(this).serialize(),
			type : 'GET',
			async : false,
			success: function(data) {
				if (data != "Ok")
				{
					alert(data);
					success = false;
				} else {
					success = true;
				}
			}
		})
		return success;
	});
	
	$('.editFormClass').submit(function() {
		var success = false;
//		var val = this.id.value;
//			data : "name="+$('#editName-'+val).val() + "&id="+val,
		return success;
	});

});