$(document).ready(function() {
	
	var checkCondition = function(emailBool, skypeNameBool, firstName, lastName) {
		return emailBool && skypeNameBool && (firstName.length > 0) && (lastName.length >  0);
	}
	
	/* Add function */
	var emailAddBool = false;
	var skypeNameAddBool = false;

	$('#addBtn').click(function() {
		$('#addBtn-container').fadeToggle("fast", "linear", function() {
			$('#addEntity').fadeToggle("fast", "linear");
		});
		$('#editBtn-container').fadeToggle("fast", "linear");
		$('#deleteBtn-container').fadeToggle("fast", "linear");
	});
	
	$('#addInputBoxFirstName').on('keyup', function() {
		if(checkCondition(emailAddBool,skypeNameAddBool,$('#addInputBoxFirstName').val(),$('#addInputBoxLastName').val())) {
			$('#saveEntity').removeAttr('disabled');
		}
		else {
			$('#saveEntity').attr('disabled', true);
		}
	});
	
	$('#addInputBoxLastName').on('keyup', function() {
		if(checkCondition(emailAddBool,skypeNameAddBool,$('#addInputBoxFirstName').val(),$('#addInputBoxLastName').val())) {
			$('#saveEntity').removeAttr('disabled');
		}
		else {
			$('#saveEntity').attr('disabled', true);
		}
	});
	
	$('#addInputBoxEmail').on('keyup',function() {
		var myLen = $(this).val().length;
        $.ajax({
			type: "GET",
			url: "checkEmpsEmail",
			data: {
				email: $(this).val(),
			},
			success: function(response) {
				if(response == false) {
					if(myLen != 0) {
						$('#funAddInputBoxEmail').attr('src', '/orgchart/images/ok.gif');
						emailAddBool = true;
					}
					else {
						$('#funAddInputBoxEmail').removeAttr('src');
						emailAddBool = false;
					}
				}
				else {
					$('#funAddInputBoxEmail').attr('src', '/orgchart/images/delete.gif');
					emailAddBool = false;
				}
				if(checkCondition(emailAddBool,skypeNameAddBool,$('#addInputBoxFirstName').val(),$('#addInputBoxLastName').val())) {
					$('#saveEntity').removeAttr('disabled');
				}
				else {
					$('#saveEntity').attr('disabled', true);
				}
			},
        });
	});
	
	$('#addInputBoxSkypeName').on('keyup',function() {
		var myLen = $(this).val().length;
        $.ajax({
			type: "GET",
			url: "checkEmpsSkypeName",
			data: {
				skypeName: $(this).val(),
			},
			success: function(response) {
				if(response == false) {
					if(myLen != 0) {
						$('#funAddInputBoxSkypeName').attr('src', '/orgchart/images/ok.gif');
						skypeNameAddBool = true;
					}
					else {
						$('#funAddInputBoxSkypeName').removeAttr('src');
						skypeNameAddBool = false;
					}
				}
				else {
					$('#funAddInputBoxSkypeName').attr('src', '/orgchart/images/delete.gif');
					skypeNameAddBool = false;
				}
				if(checkCondition(emailAddBool,skypeNameAddBool,$('#addInputBoxFirstName').val(),$('#addInputBoxLastName').val())) {
					$('#saveEntity').removeAttr('disabled');
				}
				else {
					$('#saveEntity').attr('disabled', true);
				}
			},
        });
	});
	
	$('#saveEntity').click(function() {
		$.ajax({
			type: "POST",
			url: "emps",
			data: {
				_method: "post",
				firstName: $('#addInputBoxFirstName').val(),
				lastName: $('#addInputBoxLastName').val(),
				middleInitial: $('#addInputBoxMiddleInitial').val(),
				departmentId: $('#addSelectBoxDepartmentId').val(),
				email: $('#addInputBoxEmail').val(),
				skypeName: $('#addInputBoxSkypeName').val(),
				jobTitleId: $('#addSelectBoxJobTitleId').val(),
			},
			success: function() {
				window.location.href="emps";
			},
			error: function() {
				alert('Request failed!');
			}
		});
	});
	
	/* Edit function */
	var initialFirstNameVal;
	var initialLastNameVal;
	var initialMiddleInitialVal;
	var initialDepartmentId;
	var initialEmailVal;
	var initialSkypeNameVal;
	var initialJobTitleIdVal;
	var nowFirstNameVal;
	var nowLastNameVal;
	
	var emailEditBool = true;
	var skypeNameEditBool = true;
	
	$('#editBtn').click(function() {
		$('.divHeaderHidden').fadeToggle("fast", "linear");
		$('.divColumnHidden').fadeToggle("fast", "linear");
		$('#addBtn-container').fadeToggle("fast", "linear");
		$('#editBtn-container').fadeToggle("fast", "linear", function() {
			$('#cancelBtn-container').fadeToggle("fast", "linear");
		});
	});
	
	$('.editColumnBtn').click(function() {
		var ID = $(this).val();
		initialFirstNameVal = $('#editInputBox'+ID+'FirstName').val();
		initialLastNameVal = $('#editInputBox'+ID+'LastName').val();
		initialMiddleInitialVal = $('#editInputBox'+ID+'MiddleInitial').val();
		initialDepartmentId = $('#editSelectBox'+ID+'DepartmentId').val();
		initialEmailVal = $('#editInputBox'+ID+'Email').val();
		initialSkypeNameVal = $('#editInputBox'+ID+'SkypeName').val();
		initialJobTitleId = $('#editSelectBox'+ID+'JobTitleId').val();
		nowFirstNameVal = $('#editInputBox'+ID+'FirstName').val();
		nowLastNameVal =$('#editInputBox'+ID+'LastName').val();
		$('#divRow'+ID).fadeToggle("fast", "linear", function() {
			$('#divEditRow'+ID).fadeToggle("fast", "linear");
		});
		$('.editColumnBtn').fadeToggle("fast", "linear");
		$('#cancelBtn-container').fadeToggle("fast", "linear");
	});
	
	$('[id^=editInputBox][id$=FirstName]').on('keyup', function() {
		nowFirstNameVal = $(this).val();
		if(checkCondition(emailEditBool,skypeNameEditBool,nowFirstNameVal,nowLastNameVal)) {
			$('.submitColumnBtn').removeAttr('disabled');
		}
		else {
			$('.submitColumnBtn').attr('disabled', true);
		}
	});

	$('[id^=editInputBox][id$=LastName]').on('keyup', function() {
		nowLastNameVal = $(this).val();
		if(checkCondition(emailEditBool,skypeNameEditBool,nowFirstNameVal,nowLastNameVal)) {
			$('.submitColumnBtn').removeAttr('disabled');
		}
		else {
			$('.submitColumnBtn').attr('disabled', true);
		}
	});
	
	
	$('[id^=editInputBox][id$=Email]').on('keyup', function() {
		var myLen = $(this).val().length;
		var myStr = $(this).val();
        $.ajax({
			type: "GET",
			url: "checkEmpsEmail",
			data: {
				email: $(this).val(),
			},
			success: function(response) {
				if(response == false) {
					if(myLen != 0) {
						$('.funEditInputBoxEmail').attr('src', '/orgchart/images/ok.gif');
						emailEditBool = true;
					}
					else {
						$('.funEditInputBoxEmail').removeAttr('src');
						emailEditBool = false;
					}
				}
				else {
					if(initialEmailVal == myStr) {
						$('.funEditInputBoxEmail').attr('src', '/orgchart/images/ok.gif');
						emailEditBool = true;
					}
					else {
						$('.funEditInputBoxEmail').attr('src', '/orgchart/images/delete.gif');
						emailEditBool = false;
					}
				}
				if(checkCondition(emailEditBool,skypeNameEditBool,nowFirstNameVal,nowLastNameVal)) {
					$('.submitColumnBtn').removeAttr('disabled');
				}
				else {
					$('.submitColumnBtn').attr('disabled', true);
				}
			},
        });
	});
	
	$('[id^=editInputBox][id$=SkypeName]').on('keyup', function() {
		var myLen = $(this).val().length;
		var myStr = $(this).val();
        $.ajax({
			type: "GET",
			url: "checkEmpsSkypeName",
			data: {
				skypeName: $(this).val(),
			},
			success: function(response) {
				if(response == false) {
					if(myLen != 0) {
						$('.funEditInputBoxSkypeName').attr('src', '/orgchart/images/ok.gif');
						skypeNameEditBool = true;
					}
					else {
						$('.funEditInputBoxSkypeName').removeAttr('src');
						skypeNameEditBool = false;
					}
				}
				else {
					if(initialSkypeNameVal == myStr) {
						$('.funEditInputBoxSkypeName').attr('src', '/orgchart/images/ok.gif');
						skypeNameEditBool = true;
					}
					else {
						$('.funEditInputBoxSkypeName').attr('src', '/orgchart/images/delete.gif');
						skypeNameEditBool = false;
					}
				}
				if(checkCondition(emailEditBool,skypeNameEditBool,nowFirstNameVal,nowLastNameVal)) {
					$('.submitColumnBtn').removeAttr('disabled');
				}
				else {
					$('.submitColumnBtn').attr('disabled', true);
				}
			},
        });
	});
	
	$('.submitColumnBtn').click(function() {
		var ID = $(this).val();
        $.ajax({
			type: "POST",
			url: "emps",
			data: {
				_method: "put",
				employeeId: ID,
				firstName: $('#editInputBox'+ID+'FirstName').val(),
				lastName: $('#editInputBox'+ID+':LastName').val(),
				middleInitial: $('#editInputBox'+ID+'MiddleInitial').val(),
        		departmentId: $('#editSelectBox'+ID+'DepartmentId').val(),
        		email: $('#editInputBox'+ID+'Email').val(),
        		skypeName: $('#editInputBox'+ID+'SkypeName').val(),
        		jobTitleId: $('#editSelectBox'+ID+'JobTitleId').val(),
			},
			success: function() {
				window.location.href="emps";
			}
        });
	});
	
	$('.removeColumnBtn').click(function() {
		var ID = $(this).val();
		$.ajax({
			type: "POST",
			url: "emps",
			data: {
				_method: "delete",
				employeeId: ID,
			},
			success: function() {
				window.location.href="emps";
			}
		});
	});
	
	$('.cancelColumnBtn').click(function() {
		var ID = $(this).val();
		$('[class^=funEditInputBox]').removeAttr('src');
		$('.submitColumnBtn').removeAttr('disabled');
		$('#editInputBox'+ID+'FirstName').val(initialFirstNameVal);
		$('#editInputBox'+ID+'LastName').val(initialLastNameVal);
		$('#editInputBox'+ID+'MiddleInitial').val(initialMiddleInitialVal);
		$('#editSelectBox'+ID+'DepartmentId').val(initialDepartmentId);
		$('#editInputBox'+ID+'Email').val(initialEmailVal);
		$('#editInputBox'+ID+'SkypeName').val(initialSkypeNameVal);
		$('#editSelectBox'+ID+'JobTitleId').val(initialJobTitleIdVal);
		$('#divEditRow'+ID).fadeToggle("fast", "linear", function() {
			$('#divRow'+ID).fadeToggle("fast", "linear");
		});
		$('.editColumnBtn').fadeToggle("fast", "linear");
		$('#cancelBtn-container').fadeToggle("fast", "linear");
	});
	
	/* Cancel button */
	$('#cancelBtn').click(function() {
		$('.divHeaderHidden').fadeToggle("fast", "linear");
		$('.divColumnHidden').fadeToggle("fast", "linear");
		$('#cancelBtn-container').fadeToggle("fast", "linear", function () {
			$('#addBtn-container').fadeToggle("fast", "linear");
			$('#editBtn-container').fadeToggle("fast", "linear");
		});
	});
	
	$('#cancelEntity').click(function() {
		$('#addEntity').fadeToggle("fast", "linear");
		$('#addBtn-container').fadeToggle("fast", "linear");
		$('#editBtn-container').fadeToggle("fast", "linear");
	});
});