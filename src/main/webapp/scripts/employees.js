$(document).ready(function() {
	
	var checkCondition = function(emailBool, skypeNameBool, firstName, lastName) {
		return emailBool && skypeNameBool && (firstName.length > 0) && (lastName.length >  0);
	};
	
	/* Add function */
	var emailAddBool = false;
	var skypeNameAddBool = false;

	$('#addBtn').click(function() {
		$('#addBtn-container').fadeToggle("fast", "linear", function() {
			$('#addEntity').fadeToggle("fast", "linear");
		});
		$('#editBtn-container').fadeToggle("fast", "linear");
		$('#filterBtn-container').fadeToggle("fast", "linear");
	});
	
	$('#addInputBoxFirstName').on('keyup', function() {
		if(checkCondition(emailAddBool,skypeNameAddBool,$('#addInputBoxFirstName').val(),$('#addInputBoxLastName').val())) {
			$('#saveAddEntity').removeAttr('disabled');
		}
		else {
			$('#saveAddEntity').attr('disabled', true);
		}
	});
	
	$('#addInputBoxLastName').on('keyup', function() {
		if(checkCondition(emailAddBool,skypeNameAddBool,$('#addInputBoxFirstName').val(),$('#addInputBoxLastName').val())) {
			$('#saveAddEntity').removeAttr('disabled');
		}
		else {
			$('#saveAddEntity').attr('disabled', true);
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
					$('#saveAddEntity').removeAttr('disabled');
				}
				else {
					$('#saveAddEntity').attr('disabled', true);
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
					$('#saveAddEntity').removeAttr('disabled');
				}
				else {
					$('#saveAddEntity').attr('disabled', true);
				}
			},
        });
	});
	
	$('#saveAddEntity').click(function() {
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
	
	$('#cancelAddEntity').click(function() {
		$('#addEntity').fadeToggle("fast", "linear");
		$('#addBtn-container').fadeToggle("fast", "linear");
		$('#editBtn-container').fadeToggle("fast", "linear");
		$('#filterBtn-container').fadeToggle("fast", "linear");
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
		$('#filterBtn-container').fadeToggle("fast", "linear");
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
		initialJobTitleIdVal = $('#editSelectBox'+ID+'JobTitleId').val();
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
	
	/* Filter Function */
	var greptest = function(inputWord, compareWord) {
		var array = $.grep(inputWord, function(indexInArray) {
				return compareWord.indexOf(indexInArray) == -1;
		});
		return array.length == 0;
	};
	
	var updateList = function() {
		$('[id*=divRow]').fadeOut("fast", "linear");
		var departmentId = $('#filterSelectBoxDepartmentId').val();
		var jobTitleId = $('#filterSelectBoxJobTitleId').val();
		$.each($('[id*=divRow]'), function() {
			var scheme = $(this);
			var inputWord = $('#filterInputBoxFullName').val().toLowerCase().split("");
			var firstName = scheme.find('.divColumn')[0].innerHTML.toLowerCase() + " ";
			var lastName = scheme.find('.divColumn')[1].innerHTML.toLowerCase();
			var compareWord = firstName.concat(lastName);
			var origDepartmentId = $(scheme.find('.divColumn')[2]).data("value");
			var origJobTitleId = $(scheme.find('.divColumn')[3]).data("value");
			if($('#filterInputBoxFullName').val() == "") {
				if(jobTitleId == "") {
					if(departmentId == origDepartmentId) {
						$('[id*=divRow]').promise().done(function() {
							scheme.fadeIn("fast", "linear");
						});
					}
				}
				else if(departmentId == "") {
					if((jobTitleId == origJobTitleId)) {
						$('[id*=divRow]').promise().done(function() {
							scheme.fadeIn("fast", "linear");
						});
					}
				}
				else {
					if((departmentId == origDepartmentId) && (jobTitleId == origJobTitleId)) {
						$('[id*=divRow]').promise().done(function() {
							scheme.fadeIn("fast", "linear");
						});
					}
				}
			}
			else {
				if((departmentId == "") && (jobTitleId == "")) {
					if(greptest(inputWord,compareWord)) {
						$('[id*=divRow]').promise().done(function() {
							scheme.fadeIn("fast", "linear");
						});
					}
				}
				else if((departmentId == "") && (jobTitleId != "")) {
					if((jobTitleId == origJobTitleId) && greptest(inputWord,compareWord)) {
						$('[id*=divRow]').promise().done(function() {
							scheme.fadeIn("fast", "linear");
						});
					}
				}
				else if((departmentId != "") && (jobTitleId == "")) {
					if((departmentId == origDepartmentId) && greptest(inputWord,compareWord)) {
						$('[id*=divRow]').promise().done(function() {
							scheme.fadeIn("fast", "linear");
						});
					}
				}
				else {
					if((departmentId == origDepartmentId) && (jobTitleId == origJobTitleId) && greptest(inputWord,compareWord)) {
						$('[id*=divRow]').promise().done(function() {
							scheme.fadeIn("fast", "linear");
						});
					}
				}
			}
		});
	};
	
	$('#filterBtn').click(function () {
		$('#filterBtn-container').fadeToggle("fast", "linear", function() {
			$('#filterEntity').fadeToggle("fast", "linear");
		});
		$('#addBtn-container').fadeToggle("fast", "linear");
		$('#editBtn-container').fadeToggle("fast", "linear");
	});
	
	$('#filterInputBoxFullName').on('keyup', function() {
		updateList();
	});
	
	$('#filterSelectBoxDepartmentId').change(function() {
		updateList();
	});
	
	$('#filterSelectBoxJobTitleId').change(function() {
		updateList();
	});
	
	$('#resetFilterEntity').click(function() {
		$.each($('[id*=divRow]'), function() {
			var scheme = $(this);
			scheme.fadeIn("fast", "linear");
		});
		$('#filterInputBoxFullName').val("");
		$('#filterSelectBoxDepartmentId').val("");
		$('#filterSelectBoxJobTitleId').val("");
	});
	
	$('#cancelFilterEntity').click(function() {
		$('#filterEntity').fadeToggle("fast", "linear");
		$('#addBtn-container').fadeToggle("fast", "linear");
		$('#editBtn-container').fadeToggle("fast", "linear");
		$('#filterBtn-container').fadeToggle("fast", "linear");
	});
	
	/* Cancel button */
	$('#cancelBtn').click(function() {
		$('.divHeaderHidden').fadeToggle("fast", "linear");
		$('.divColumnHidden').fadeToggle("fast", "linear");
		$('.divColumnHidden').css("display", "none");
		$('#cancelBtn-container').fadeToggle("fast", "linear", function () {
			$('#addBtn-container').fadeToggle("fast", "linear");
			$('#editBtn-container').fadeToggle("fast", "linear");
			$('#filterBtn-container').fadeToggle("fast", "linear");
		});
	});
});