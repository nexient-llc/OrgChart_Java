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
		$('.parentSelect-'+val).remove();
		$.ajax({
			type : 'DELETE',
			url : "depart/delete/" +val,
		})
	});
	
	$('#newDepartment').submit(function() {
		var success = false;
		$.ajax({
			dataType : "text",
			type : 'GET',
			url : "findDepart",
			data : "name="+$('#newDeptName').val(),
			async : false,
			success: function(data) {
				if (data=="true")
				{
					alert("That name already exists in the database.");
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
		var val = this.id.value;
		$.ajax({
			dataType : "text",
			type : 'GET',
			url : "findDepart",
			data : "name="+$('#editName-'+val).val() + "&id="+val,
			async : false,
			success: function(data) {
				if (data=="true")
				{
					alert("'" + $('#editName-'+val).val() + "' already exists in the database.");
					success = false;
				} else {
					success = true;
				}
			}
		})
		return false;
	});
	
});