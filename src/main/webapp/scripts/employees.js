$(document).ready(function() {
	$('#cancelFilterBtn').click(function() {
		$('#filter-container').slideToggle("fast", "linear");
	});

	$('#filterBtn').click(function() {
		$('#filter-container').slideToggle("fast", "linear");
	});
	
	$('#filterName').autocomplete({
		source:function(request, response){
			$.ajax({
				type:'get',
				url:'emp/autocomplete/' + $('#filterName').val(),
				success:function(data){
					
					var nameArray =[];
					var tags = $.parseJSON(data);
					var tag;
                    for(tag in tags)
                    	nameArray.push(tags[tag].firstName + " " + tags[tag].lastName);
					response(nameArray);
				}
			});
		}
	});

	$('#addBtn-container').css('width', $('#t1').width());

	$('#addBtn').click(function() {
		$('#addBtn-container').fadeToggle("fast", "linear", function() {
			$('#addEntity-container').fadeToggle("fast", "linear");
		});
	});

	$('#cancelAddButton').click(function() {
		$('#addEntity-container').fadeToggle("fast", "linear", function() {
			$('#addBtn-container').fadeToggle("fast", "linear");
		});
	});

	$('.editButton').click(function() {
		$.ajax({
			url : "emp/" + $(this).val(),
			type : "GET"
		}).done(function(data) {
			var form = $.parseJSON(data);

			$('#employeeId').val(form.id);
			$('#empFirstName').val(form.firstName);
			$('#empLastName').val(form.lastName);
			$('#empMiddleInitial').val(form.middleInitial);
			$('#empEmail').val(form.email);
			$('#empSkypeName').val(form.skypeName);
			$('#empDepartmentId').val(form.department.id);
			$('#empJobTitleId').val(form.jobTitle.id);

			$('#employeesTable-container').fadeToggle("fast", "linear");
			$('#editEntity-container').fadeToggle("fast", "linear");

		});
	});

	$('#cancelEditButton').click(function() {
		$('#editEntity-container').fadeToggle("fast", "linear", function() {
			$('#employeesTable-container').fadeToggle("fast", "linear");
		});
	});

	$('#removeButton').click(function() {
		var empId = $('#employeeId').val();
		$.ajax({
			url : "delete/emp/" + empId,
			type : "DELETE"
		}).done(function() {

			$('#tableRow' + empId).remove();
			$('#editEntity-container').fadeToggle("fast", "linear", function() {
				$('#employeesTable-container').fadeToggle("fast", "linear");
			});
		});

	});

});