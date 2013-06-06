$(document).ready(function() {
	$('#addEmpBtn-container').css('width', $('#t1').width());
	
	//Open Add Employee form
	$('#addEmpBtn').button().click(function() {
		//Change the form from Edit Employee to Add Employee
		if ($('#newEmpForm').hasClass("editEmp")) {
			$('#newEmpForm').removeClass("editEmp");
			$('#newEmpForm').addClass("addEmp");
			$("#putMethod").remove();
			
			//Change title to Add Employee and clear all the fields
			$("#empFormLegend").text("Add Employee");
			$("#empId").remove();
			$('#firstName').val("");
			$('#lastName').val("");
			$('#email').val("");
			$('#skypeName').val("");
			$('#department\\.departmentId').val("");
			$('#jobTitle\\.id').val("");
		}
		
		//Open the actual form and get rid of the Add button on the page
		$('#addEmpBtn-container').fadeToggle("fast", "linear", function() {
			$('#addEmpEntity').dialog({
				minWidth : 381
			});
		});
		
		//validate the form
		validateEmpForm();
	});
	
	//When the form closes, the Add button reappears on the page
	$('#addEmpEntity').bind('dialogclose', function(event) {
		$('#addEmpBtn-container').fadeToggle("fast", "linear");
	});
	
	//Cancel button on the form clears out all the fields and closes the form
	$('#cancelEmpBtn').button().click(function() {
		$('#addEmpEntity').dialog("close");
		$('#firstName').val("");
		$('#lastName').val("");
		$('#email').val("");
		$('#skypeName').val("");
		$('#department\\.departmentId').val("");
		$('#jobTitle\\.id').val("");
	});
	
	//Edit employee form
	$('.editEmp').button().click(function() {
		//change Add Employee form to Edit Employee
		if ($('#newEmpForm').hasClass("addEmp")) {
			$('#newEmpForm').removeClass("addEmp");
			$('#newEmpForm').addClass("editEmp");
			$('#newEmpForm').prepend("<input type='hidden' name='_method' value='put' id='putMethod'/>");
		}
		//Change title of form to Edit Employee and add hidden input field which has the id of the emp
		$("#empFormLegend").text("Edit Employee");
		$("#empFormInputs").prepend("<input type='hidden' name='id' id='id' />");
		
		//prepopulate the form with the employee info from an ajax call
		$.ajax({
			url : "emps/" + $(this).val(),
			type : "GET"
		}).done(function(data) {
			var form = $.parseJSON(data);
			$('#id').val(form.id);
			$('#firstName').val(form.firstName);
			$('#lastName').val(form.lastName);
			$('#email').val(form.email);
			$('#skypeName').val(form.skypeName);
			$('#department\\.departmentId').val(form.department.departmentId);
			$('#jobTitle\\.id').val(form.jobTitle.id);
			if (form.isManager) {
				$('#isManager').attr('defaultChecked', true);
			}
			$('#addEmpBtn-container').fadeToggle("fast", "linear");
			
			$('#addEmpEntity').dialog({
				minWidth : 381
			});
			
			//validate the form
			validateEmpForm();
		});
	});
	
	$('#submitFilterEmpBtn').button();
	$('#resetEmpBtn').button();
	
	//validate the form
	validateEmpForm = function() {
		$('#newEmpForm').validate({
			rules : {
				firstName : {
					required : true
				},
				lastName : {
					required : true
				},
				email : {
					required : true,
					email : true
				},
				skypeName : {
					required : true
				},
				"department.departmentId" : {
					required : true
				},
				"jobTitle.id" : {
					required : true
				}
			}
		});
	}
	
	$("#filterEmpBtn").button().click(function(){
		$.ajax({
			url: "empsFilterPage",
			type: "GET"
		}).done(function(data){
			var incomingJsonData = $.parseJSON(data);
			var firstNames = [];
			var lastNames = [];
			
			for (var i = 0; i < incomingJsonData.length; i++) {
				firstNames[i] = incomingJsonData[i].firstName;
				lastNames[i] = incomingJsonData[i].lastName;
			}
			
			$("#filterFirstName").autocomplete({
				source: firstNames,
				appendTo: '#filterEmpEntity'
			});
			$("#filterLastName").autocomplete({
				source: lastNames,
				appendTo: '#filterEmpEntity'
			});
			
			$('#filterEmpEntity').dialog({
				width: 381
			});
		});
	});
	
	$('#cancelFilterEmpBtn').button().click(function(){
		$('#filterEmpEntity').dialog("close");
	});

});