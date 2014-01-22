$(document).ready(function() {
	$('#addBtn-container').css('width', $('#t1').width());

	$('#addBtn').click(function() {
		$('#addBtn-container').fadeToggle("fast", "linear", function() {
			$('#addEntity').fadeToggle("fast", "linear");
		});
	});

	$('.editBtn').click(function(evnt) {
		var num = $(this).val();
		$('#ViewDepts' + num).fadeToggle("fast", "linear", function() {
			$('#EditDepts' + num).fadeToggle("fast", "linear");
		});
	});

	$('.saveBtn').click(function(evnt) {
		var num = $(this).val();
		$.ajax({
			url : "depts",
			type : "POST",
			data : {
				_method : "put",
				id : num,
				name : $('#deptName' + num).val(),
				parentID : $('#deptParentId' + num).val()
			},
			success : function(response) {
				window.location.href = "depts";
			},
		});
	});
	
	$('.deleteBtn').click(function(evnt) {
		var num = $(this).val();
		alert(num);
		$.ajax({
			url : "depts",
			type : "POST",
			data : {
				_method : "delete",
				id : num
			},
			success : function(response) {
				window.location.href = "depts";
			},
		});
	});
	
	
	$('.cancelBtn').click(function(evnt){
		var num = $(this).val();
		evnt.preventDefault();
		$('#EditDepts' + num).fadeToggle("fast", "linear", function() {
			$('#ViewDepts' + num).fadeToggle("fast", "linear");
		});
	});
});

function editButton(deptId) {
	$('#editEntity entityid').val(deptId);
	$('#editBtn-container' + deptId).fadeToggle("fast", "linear", function() {
		$('#editEntity').fadeToggle("fast", "linear");
	});
}
