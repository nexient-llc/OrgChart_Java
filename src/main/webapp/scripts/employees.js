$(document).ready(function() {
	$('#addBtn-container').css('width', $('#t1').width());

	$('#addBtn').click(function() {
		$('#addBtn-container').fadeToggle("fast", "linear", function() {
			$('#addEntity').fadeToggle("fast", "linear");
		});
	});

	$('.editBtn').click(function(evnt) {
		var num = $(this).val();
		$('#ViewEmps' + num).fadeToggle("fast", "linear", function() {
			$('#EditEmps' + num).fadeToggle("fast", "linear");
		});
	});

	$('.filter-container').click(function() {
		$('#filter-container').fadeToggle("fast", "linear", function() {
			$('#search').fadeToggle("fast", "linear");
		});
	});

	$('.filterBtn').click(function(evnt) {
		$.ajax({
			url : "emps",
			type : "GET",
			data : {
				fullName : $('#search #empFullName').val(),
				deptId : $('#search #empDeptId').val(),
				jobId : $('#search #empJobId').val()
			},
			success : function(data) {
				data = jQuery.parseJSON(data);
				$('#t1 .row:not(.header)').fadeOut('fast');
				data.forEach(function(empId) {
					$('#t1 #ViewEmps' + empId).fadeIn('fast');
				});
			},
		});
	});

	$('.deleteBtn').click(function(evnt) {
		var num = $(this).val();
		alert(num);
		$.ajax({
			url : "emps",
			type : "POST",
			data : {
				_method : "delete",
				id : num
			},
			success : function(response) {
				window.location.href = "emps";
			},
		});
	});
	
	$('.saveBtn').click(function(evnt) {
		var num = $(this).val();
		$.ajax({
			url : "emps",
			type : "POST",
			data : {
				_method : "put",
				id : num,
				firstName : $('#empFirstName' + num).val(),
				middleInitial : $('#empMidInitial' + num).val(),
				lastName : $('#empLastName' + num).val(),
				email : $('#empEmail' + num).val(),
				skypeName : $('#empSkypeName' + num).val(),
				departmentId : $('#empdeptId' + num).val(),
				jobTitleId : $('#empjobId' + num).val(),
			},
			success : function(response) {
				window.location.href = "emps";
			},
		});
	});

});