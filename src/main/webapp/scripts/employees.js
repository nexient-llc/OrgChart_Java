$(document).ready(function() {
	$('#addBtn-container').css('width', $('#t1').width());
	
	//Set up autocomplete widget
	var empFullNames = new Array();
	$('.empRow').each(function() {
		empFullNames.push($(this).data('empFullName'));
	});
	$('form#filter #fullName').autocomplete({source:empFullNames});

	//Open Add Employee box
	$('#addBtn').click(function() {
		$('#addBtn-container').fadeOut("fast", "linear", function() {
			$('#addEntity').fadeIn("fast", "linear");
		});
		$('.editEmployee').fadeOut("fast", "linear");
	});
	
	//Close Add Employee box
	$('#cancelAdd').click(function() {
		$('#addEntity').fadeOut("fast", "linear", function() {
			$('#addBtn-container').fadeIn("fast", "linear");
			$('.editEmployee').fadeIn("fast, linear");
		});	
	});
	
	//Open Edit Employee Box
	$('.editEmployee').click(function() {
		//Set edit form values to values of selected employee
		$('#editEntity input#id').val($(this).closest('tr.empRow').data('empId'));
		$('#editEntity input#firstName').val($(this).closest('tr.empRow').data('empFirstName'));
		$('#editEntity input#middleInitial').val($(this).closest('tr.empRow').data('empMiddleInitial'));
		$('#editEntity input#lastName').val($(this).closest('tr.empRow').data('empLastName'));
		$('#editEntity input#email').val($(this).closest('tr.empRow').data('empEmail'));
		$('#editEntity input#skypeName').val($(this).closest('tr.empRow').data('empSkypeName'));
		$('#editEntity select#department\\.id').val($(this).closest('tr.empRow').data('empDepartmentId'));
		$('#editEntity select#jobTitle\\.id').val($(this).closest('tr.empRow').data('empJobTitleId'));

		$('.editEmployee').fadeOut("fast", "linear");
		$('#addBtn-container').fadeOut("fast", "linear", function() {
			$('#editEntity').fadeIn("fast", "linear");
		});
	});
	
	//Close Edit Employee box
	$('#cancelEdit').click(function() {
		$('#editEntity').fadeOut("fast", "linear", function() {
			$('#addBtn-container').fadeIn("fast", "linear");
			$('.editEmployee').fadeIn("fast, linear");
		});
	});
	
	//Validate forms before submission
	$('form').submit(function() {
		var firstName = $(this).find('input#firstName').val();
		var lastName = $(this).find('input#lastName').val();
		var email = $(this).find('input#email').val();
		var skypeName = $(this).find('input#skypeName').val();
		var departmentId = $(this).find('select#department\.id').val();
		var jobTitleId = $(this).find('select#jobTitle\.id').val();
		
		if (firstName.length == 0 ||
				lastName.length == 0 ||
				email.length == 0 ||
				skypeName.length == 0 ||
				departmentId == -1 ||
				jobTitleId == -1) {
			alert("Please complete all required fields");
			return false;
		}
	});
	
	//Open Filter Employees box
	$('#filterButton').click(function() {
		$(this).fadeOut("fast", "linear", function() {
			$('div#filterEmployees').fadeIn("fast", "linear");
		});
	});
	
	//Exexute filter employees command
	$('#executeFilter').click(function() {
		//Checks each employee vs filters
		//If employee doesn't meet the criteria, it is hidden. Otherwise, it is shown.
		var nameFilter = $('form#filter #fullName').val();
		var deptFilter = $('form#filter #deptToFind').val();
		var jobFilter = $('form#filter #jobToFind').val();
		$('.empRow').each(function() {
			var meetsFilterCriteria = true;
			if (nameFilter.length > 0) {
				if ($(this).data('empFullName') != nameFilter) {
					meetsFilterCriteria = false;
				}
			}
			if (deptFilter > -1) {
				if ($(this).data('empDepartmentId') != deptFilter) {
					meetsFilterCriteria = false;
				}
			}
			if (jobFilter > -1) {
				if ($(this).data('empJobTitleId') != jobFilter) {
					meetsFilterCriteria = false;
				}
			}
			if (meetsFilterCriteria) {
				$(this).show();
			} else {
				$(this).hide();
			}
		});
		
	});
	
	//Close Filter Employees box and show all hidden employees
	$('#resetFilter').click(function() {
		$('.empRow').each(function() {
			$(this).show();
		});
		$('div#filterEmployees').fadeOut("fast", "linear", function() {
			$('#filterButton').fadeIn("fast", "linear");
		});
	});

});