$(document).ready(function() {
	$('#addBtn-container').css('width', $('#t1').width());
	
	//Open Add Department box
	$('#addBtn').click(function() {
		$('#addBtn-container').fadeOut("fast", "linear", function() {
			$('#addEntity').fadeIn("fast", "linear");
		});
		$('.editDepartment').fadeOut("fast", "linear");
	});
	
	//Close Add Department box
	$('#cancelAdd').click(function() {
		$('#addEntity').fadeOut("fast", "linear", function() {
			$('#addBtn-container').fadeIn("fast", "linear");
			$('.editDepartment').fadeIn("fast, linear");
		});	
	});
	
	//Open Edit Department box
	$('.editDepartment').click(function() {
		//Set edit form values to values of selected department
		$('#editEntity input#name').val($(this).closest('tr.deptRow').data('deptName'));
		$('#editEntity input#id').val($(this).closest('tr.deptRow').data('deptId'));
		$('#editEntity input#isInactive').val("false");
		$('#editEntity select').val($(this).closest('tr.deptRow').data('parentDeptId'));

		$('.editDepartment').fadeOut("fast", "linear");
		$('#addBtn-container').fadeOut("fast", "linear", function() {
			$('#editEntity').fadeIn("fast", "linear");
		});
	});
	
	//Close Edit Department box
	$('#cancelEdit').click(function() {
		$('#editEntity').fadeOut("fast", "linear", function() {
			$('#addBtn-container').fadeIn("fast", "linear");
			$('.editDepartment').fadeIn("fast, linear");
		});
	});
	
	//Remove Department - confirmation and sets hidden isInactive variable, then submits form
	$('#removeDept').click(function() {
		var confirmation = window.confirm("Are you sure you would like to remove this department?");
		if (confirmation) {
			$(this).siblings('input#isInactive').val('true');
			$('#editEntity form#department').submit();
		}
	});
	
	//Validates forms before submission
	$('form').submit(function() {
		//Check for existing department with chosen name
		var allDepts = new Array();
		var submittedName = $(this).find('input#name').val();
		$(this).find('option').each(function() {
			allDepts.push($(this).text());
		});
		if ($.inArray(submittedName, allDepts) > -1 && $(this).find('input#isInactive').val() == 'false') {
			alert("Another department exists with that name.");
			return false;
		}
		
		//Checks to see if name contains "(Inactive)"
		if (submittedName.indexOf("(Inactive)") > -1) {
			alert("Cannot create an inactive department");
			return false;
		}
		
		//Name cannot have a length of 0
		if (submittedName.length == 0) {
			alert("Must complete all required fields")
			return false
		}
	});

});
